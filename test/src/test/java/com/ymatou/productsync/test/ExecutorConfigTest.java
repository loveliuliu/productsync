package com.ymatou.productsync.test;

import com.ymatou.messagebus.client.MessageBusException;
import com.ymatou.productsync.domain.executor.CommandExecutor;
import com.ymatou.productsync.domain.executor.commandconfig.*;
import com.ymatou.productsync.domain.model.mongo.MongoData;
import com.ymatou.productsync.facade.model.req.SyncByCommandReq;
import com.ymatou.productsync.web.ProductSyncApplication;
import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 场景业务指令器test
 * Created by chenpengxuan on 2017/1/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSyncApplication.class)// 指定我们SpringBoot工程的Application启动类
public class ExecutorConfigTest {
    @Autowired
    private SetOnTopExecutorConfig setOnTopExecutorConfig;

    @Autowired
    private AddActivityExecutorConfig addActivityExecutorConfig;

    @Autowired
    private ConfirmActivityExecutorConfig confirmActivityExecutorConfig;

    @Autowired
    private CreateActivityExecutorConfig createActivityExecutorConfig;

    @Autowired
    private ModifyBrandAndCategoryExecutorConfig modifyBrandAndCategoryExecutorConfig;

    @Autowired
    private AddProductExecutorConfig addProductExecutorConfig;

    @Autowired
    private AddProductPicsExecutorConfig addProductPicsExecutorConfig;

    @Autowired
    private DeleteProductPicsExecutorConfig deleteProductPicsExecutorConfig;

    @Autowired
    private SuspendSaleExecutorConfig suspendSaleExecutorConfig;

    @Autowired
    private AutoOnShelfProductExecutorConfig autoOnShelfProductExecutorConfig;

    @Autowired
    private CatalogStockChangeExecutorConfig catalogStockChangeExecutorConfig;

    @Autowired
    private  ModifyActivityExecutorConfig modifyActivityExecutorConfig ;

    @Autowired
    private  SetOffTopExecutorConfig setOffTopExecutorConfig ;

    @Autowired
    private  DeleteProductExecutorConfig deleteProductExecutorConfig ;

    @Autowired
    private  ProductPutoutExecutorConfig productPutoutExecutorConfig ;

    @Autowired
    private  ProductStockChangeExecutorConfig productStockChangeExecutorConfig ;


    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private SyncActivityProductExecutorConfig syncActivityProductExecutorConfig;

    @Autowired
    private BatchSetOnShelfExecutorConfig batchSetOnShelfExecutorConfig;

    @Autowired
    private SetOnShelfUpdateStockNumExecutorConfig setOnShelfUpdateStockNumExecutorConfig;

    @Autowired
    private ModifyActivityPriceExecutorConfig modifyActivityPriceExecutorConfig;

    @Test
    public void testSetOnTopExecutorConfig() {
        String productId = "992b3749-4379-4260-b05b-24e734423f9f";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        commandExecutor.executeCommand(req, setOnTopExecutorConfig);
    }

    @Test
    public void testAddActivity() {
        long activityId = 157242;
        SyncByCommandReq req = new SyncByCommandReq();
        req.setActivityId(activityId);
        commandExecutor.executeCommand(req, addActivityExecutorConfig);
    }

    @Test
    public void testConfirmActivity() {
        long activityId = 157242;
        SyncByCommandReq req = new SyncByCommandReq();
        req.setActivityId(activityId);
        commandExecutor.executeCommand(req, confirmActivityExecutorConfig);
    }

    @Test
    public void testCreateActivity() {
        long activityId = 157242;
        SyncByCommandReq req = new SyncByCommandReq();
        req.setActivityId(activityId);
        commandExecutor.executeCommand(req, createActivityExecutorConfig);
    }

    @Test
    public void testAddProduct(){
        String productId = "7577884f-8606-4571-ba52-4881e89e660c";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        commandExecutor.executeCommand(req, addProductExecutorConfig);
    }

    /*
        验证商品主图同步 - AddProductPics
     */
    @Test
    public void testAddProductPics() {
        String productId = "992b3749-4379-4260-b05b-24e734423f9f";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        commandExecutor.executeCommand(req, addProductPicsExecutorConfig);
    }

    @Test
    public void testDeleteProductPics() {
        String productId = "992b3749-4379-4260-b05b-24e734423f9f";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        commandExecutor.executeCommand(req, deleteProductPicsExecutorConfig);
    }

    @Test
    public void testSuspendSale() {
        String productId = "992b3749-4379-4260-b05b-24e734423f9f";
        long activityId = 334567;
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        req.setActivityId(activityId);
        commandExecutor.executeCommand(req, suspendSaleExecutorConfig);
    }

    @Test
    public void testModifyBrandAndCategory(){
        String productId = "acf23898-c735-4f70-adc2-f8e09e60d19f";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        commandExecutor.executeCommand(req, modifyBrandAndCategoryExecutorConfig);
    }

    /**
     * 同步活动商品数据
     *
     */
    @Test
    public void testSyncActivityProduct() {
        long productInActivityId = 286006;
        SyncByCommandReq req = new SyncByCommandReq();
        req.setActivityId(productInActivityId);

        commandExecutor.executeCommand(req, syncActivityProductExecutorConfig);
    }

    @Test
    public void testAutoRefreshProduct() {
        String productId = "acf23898-c735-4f70-adc2-f8e09e60d19f";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        commandExecutor.executeCommand(req, autoOnShelfProductExecutorConfig);
    }

    public void testCatalogStockChange() {
        String productId = "acf23898-c735-4f70-adc2-f8e09e60d19f";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        commandExecutor.executeCommand(req, catalogStockChangeExecutorConfig);
    }


    @Test
    public void testModifyActivity(){
        long activityId = 157242;
        String productId = "7577884f-8606-4571-ba52-4881e89e660c";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        req.setActivityId(activityId);
        boolean isOk = commandExecutor.executeCommand(req, modifyActivityExecutorConfig);
        Asserts.check(isOk, "");

        //无效的直播Id
        long activityId2 = 0;
        SyncByCommandReq req2 = new SyncByCommandReq();
        req2.setActivityId(activityId2);
        boolean isOk2 = commandExecutor.executeCommand(req2, modifyActivityExecutorConfig);
        Asserts.check(isOk2, "");
    }

    /**
     *
     */
    @Test
    public void testSetOffTop(){
        long activityId = 157242;
        String productId = "7577884f-8606-4571-ba52-4881e89e660c";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        req.setActivityId(activityId);
        commandExecutor.executeCommand(req, setOffTopExecutorConfig);
    }

    /**
     *
     */
    @Test
    public void testDeleteProduct() throws MessageBusException {
        long activityId = 157242;
        String productId = "7577884f-8606-4571-ba52-4881e89e660c";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        req.setActivityId(activityId);
        commandExecutor.executeCommand(req, deleteProductExecutorConfig);
    }


    /**
     * 有错误 Error
     */
    @Test
    public void testProductPutout() throws MessageBusException {
        long activityId = 157242;
        String productId = "7577884f-8606-4571-ba52-4881e89e660c";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        req.setActivityId(activityId);
        List<MongoData> update= productPutoutExecutorConfig.loadSourceData(0,productId);
        commandExecutor.executeCommand(req, productPutoutExecutorConfig);
    }


    @Test
    public void testProductStockChange() throws MessageBusException {
        long activityId = 157242;
        String productId = "7577884f-8606-4571-ba52-4881e89e660c";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        req.setActivityId(activityId);
        List<MongoData> update= productStockChangeExecutorConfig.loadSourceData(activityId,productId);
        commandExecutor.executeCommand(req, productPutoutExecutorConfig);
    }

    @Test
    public void testBatchSetOnShelf() {
        long activityId = 157242;
        String productId = "7577884f-8606-4571-ba52-4881e89e660c";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setActivityId(activityId);
        req.setProductId(productId);
        commandExecutor.executeCommand(req, batchSetOnShelfExecutorConfig);
    }

    @Test
    public void testSetOnShelfUpdateStockNum() {
        long activityId = 157242;
        String productId = "7577884f-8606-4571-ba52-4881e89e660c";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setActivityId(activityId);
        req.setProductId(productId);
        commandExecutor.executeCommand(req, setOnShelfUpdateStockNumExecutorConfig);
    }

    @Test
    public void testModifyActivityPrice() {
        String productId = "edc21ac6-5fc9-494c-9f36-110b841f75a0";
        long activityId = 18946;
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        req.setActivityId(activityId);
        commandExecutor.executeCommand(req, modifyActivityPriceExecutorConfig);
    }
}
