package com.whuaz.spring.async.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author grez
 * @since 18-11-27
 **/
@Service
public class EmailService {

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    /**
     * 无返回值的异步任务
     */
    @Async("methodAsync")
    public void sendEmailAsync() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("loading......");
        logger.info("email is send");
    }

    @Async("methodAsync")
    public Future<String> sendEmailAsync(String email) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(String.format("发送邮件地址：{%s}", email));
    }
}
