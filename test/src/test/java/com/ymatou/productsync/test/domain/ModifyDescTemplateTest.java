package com.ymatou.productsync.test.domain;

import com.ymatou.productsync.domain.executor.CommandExecutor;
import com.ymatou.productsync.domain.executor.commandconfig.ModifyDescTemplateExecutorConfig;
import com.ymatou.productsync.facade.model.req.SyncByCommandReq;
import com.ymatou.productsync.web.ProductSyncApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangyong on 2017/3/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSyncApplication.class)// 指定我们SpringBoot工程的Application启动类
public class ModifyDescTemplateTest {
    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private ModifyDescTemplateExecutorConfig modifyDescTemplateExecutorConfig;

    @Test
    public void testModifyDescTemplate() {
        String productId = "db58891e-0e84-4707-b00b-84750f9f5aad";
        SyncByCommandReq req = new SyncByCommandReq();
        req.setProductId(productId);
        commandExecutor.executeCommand(req, modifyDescTemplateExecutorConfig);
    }
}
