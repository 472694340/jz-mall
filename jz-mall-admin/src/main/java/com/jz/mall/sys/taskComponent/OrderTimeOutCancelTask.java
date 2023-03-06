package com.jz.mall.sys.taskComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  扫描订单信息,自动取消超时任务
 *        业务背景是,通过扫描下订单时间,超过60分钟后取消
 */
@Component
public class OrderTimeOutCancelTask {
    private static final Logger log = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * 每十分钟扫描一次
     */
//    @Scheduled(cron = "0 0/10 * * ?")
    private void cancelTimeOutTask(){
        //这里执行取消订单操作
        log.info("取消超时订单,并根据sku释放锁定库存");
    }
}
