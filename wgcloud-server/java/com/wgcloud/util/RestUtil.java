package com.wgcloud.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wgcloud.task.ScheduledTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @ClassName:RestUtil.java
 * @version V1.0
 * @author: wgcloud
 * @date: 2019年11月16日
 * @Description: RestUtil.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Component
public class RestUtil {

    private Logger logger  = LoggerFactory.getLogger(RestUtil.class);

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject post(String url, JSONObject jsonObject){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.parse(jsonObject).toString(), headers);
        ResponseEntity<String> responseEntity  =  restTemplate.postForEntity(url,httpEntity, String.class);
        return JSONUtil.parseObj(responseEntity.getBody());
    }

    public JSONObject post(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity  =  restTemplate.postForEntity(url,httpEntity, String.class);
        return JSONUtil.parseObj(responseEntity.getBody());
    }

    public int get(String url){
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            return responseEntity.getStatusCodeValue();
        }catch (HttpClientErrorException e){
            logger.error("服务接口检测任务错误",e);
            return e.getRawStatusCode();
        }catch (Exception e){
            logger.error("服务接口检测任务错误",e);
            return 500;
        }
    }


}
