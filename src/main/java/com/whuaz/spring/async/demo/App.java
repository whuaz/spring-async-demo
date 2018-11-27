package com.whuaz.spring.async.demo;

import com.whuaz.spring.async.demo.service.EmailService;
import com.whuaz.spring.async.demo.service.OtherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步方法demo启动类
 * @author grez
 * @since 18-11-27
 **/
@SpringBootApplication
@EnableAsync
public class App {

    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
