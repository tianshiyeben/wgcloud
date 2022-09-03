package com.wgcloud;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @version V2.3
 * @ClassName:RestUtil.java
 * @author: wgcloud
 * @date: 2019年11月16日
 * @Description: RestUtil.java
 * @Copyright: 2017-2022 www.wgstart.com. All rights reserved.
 */
@Component
public class RestUtil {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CommonConfig commonConfig;

    public String post(String url, JSONObject jsonObject) {
        if (null != jsonObject) {
            jsonObject.put("wgToken", MD5Utils.GetMD5Code(commonConfig.getWgToken()));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.parse(jsonObject).toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        return responseEntity.getBody();
    }

    public JSONObject post(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        return JSONUtil.parseObj(responseEntity.getBody());
    }

    public JSONObject get(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return JSONUtil.parseObj(responseEntity.getBody());
    }

}
