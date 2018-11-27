package com.whuaz.spring.async.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author grez
 * @since 18-11-27
 **/
@Service
public class OtherService {

    private final static Logger logger = LoggerFactory.getLogger(OtherService.class);

    public String doSomething() {
        return "something was done";
    }
}
