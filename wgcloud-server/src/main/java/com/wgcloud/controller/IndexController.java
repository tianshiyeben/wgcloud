package com.wgcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version v2.3
 * @ClassName:IndexController.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: AppInfoController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Configuration
public class IndexController implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
