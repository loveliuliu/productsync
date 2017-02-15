package com.ymatou.productsync.domain.mongorepo;

import com.mongodb.DuplicateKeyException;
import com.ymatou.productsync.domain.model.mongo.MongoData;
import com.ymatou.productsync.infrastructure.config.datasource.DynamicDataSourceAspect;
import com.ymatou.productsync.infrastructure.util.MapUtil;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * mongo 仓储操作相关
 * Created by chenpengxuan on 2017/2/6.
 */
@Component
public class MongoRepository {
    @Autowired
    private Jongo jongoClient;

    private final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * mongo excutor
     *
     * @param mongoDataList
     * @return
     */
    public boolean excuteMongo(List<MongoData> mongoDataList) throws IllegalArgumentException {
        if (mongoDataList == null || mongoDataList.isEmpty())
            throw new IllegalArgumentException("mongoDataList 不能为空");
        List<Boolean> resultList = new ArrayList();
        //TODO 单笔异常会引起整个列表失败，需要一层异常处理
        mongoDataList.stream().forEach(x -> resultList.add(processMongoData(x)));
        return !resultList.contains(false);
    }

    /**
     * mongodata 实际操作
     *
     * @param mongoData
     * @return
     */
    private boolean processMongoData(MongoData mongoData) throws IllegalArgumentException {
        logger.info(mongoData.toString());
        logger.debug("Data:"+mongoData.getUpdateData().toString());
        if (mongoData.getTableName().isEmpty())
            throw new IllegalArgumentException("mongo table name 不能为空");
        MongoCollection collection = jongoClient.getCollection(mongoData.getTableName());
        //TODO 需要把操作类型，条件和参数数据打个debug日志
        switch (mongoData.getOperationType()) {
            case CREATE:
                try {
                    return collection.insert(MapUtil.makeObjFromMap(mongoData.getUpdateData())).wasAcknowledged();
                }catch (DuplicateKeyException ex){
                    logger.info("{}mongo插入操作发生重复键异常",mongoData.getUpdateData());
                }
            case UPDATE:
                return collection.update(MapUtil.makeJsonStringFromMap(mongoData.getMatchCondition()))
                        .multi()
                        .with(MapUtil.makeObjFromMap(mongoData.getUpdateData().parallelStream().findFirst().orElse(Collections.emptyMap())))
                        .getN() > 0;
            case UPSERT:
                return collection.update(MapUtil.makeJsonStringFromMap(mongoData.getMatchCondition()))
                        .upsert()
                        .with(MapUtil.makeObjFromMap(mongoData.getUpdateData().parallelStream().findFirst().orElse(Collections.emptyMap())))
                        .getN() > 0;
            case DELETE:
                return collection.remove(MapUtil.makeJsonStringFromMap(mongoData.getMatchCondition())).getN() > 0;
            default:
                throw new IllegalArgumentException("mongo 操作类型不正确");
        }
    }
}
