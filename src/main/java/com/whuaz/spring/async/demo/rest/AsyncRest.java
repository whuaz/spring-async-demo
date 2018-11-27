package com.whuaz.spring.async.demo.rest;

import com.whuaz.spring.async.demo.service.EmailService;
import com.whuaz.spring.async.demo.service.OtherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author grez
 * @since 18-11-27
 **/
@RestController
public class AsyncRest {

    private final static Logger logger = LoggerFactory.getLogger(AsyncRest.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtherService otherService;

    /**
     * 异步任务，不带返回值
     * @return
     */
    @GetMapping("/async-test")
    public Map<String, Object> asyncTest() {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        String other = otherService.doSomething();
        if (!StringUtils.isEmpty(other)) {
            map.put("other", other);
            // 异步发送邮件
            emailService.sendEmailAsync();
            // 邮件发送之前返回结果
            logger.info("Execute before sending mail！");
            map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - start));
            return map;
        }
        return null;
    }

    /**
     * 异步任务，带返回值
     * @return
     */
    @GetMapping("/async-test-return")
    public Map<String, Object> asyncTestReturn() {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();

        List<Future<String>> futures = new ArrayList<>();


        String other = otherService.doSomething();

        if (!StringUtils.isEmpty(other)) {
            map.put("other", other);
            // 异步发送多个邮件
            Future<String> future1 = emailService.sendEmailAsync("18610941573@163.com");
            Future<String> future2 = emailService.sendEmailAsync("grez0709@aliyun.com");
            Future<String> future3 = emailService.sendEmailAsync("potter.zhu@embracesource.com");
            futures.add(future1);
            futures.add(future2);
            futures.add(future3);

            List<String> data = new ArrayList<>();
            for(Future future : futures) {
                String s = null;
                try {
                    s = (String) future.get();
                    data.add(s);
                } catch (Exception e) {
                    logger.error("执行异步任务异常");
                }
            }
            map.put("data", data);
            // 邮件发送之前返回结果
            logger.info("Execute before sending mail！");
            map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - start));
            return map;
        }
        return null;
    }
}
