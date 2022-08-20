package com.wgcloud.controller;

import com.wgcloud.entity.SystemInfo;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.service.SystemInfoService;
import com.wgcloud.util.staticvar.StaticKeys;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @version v2.3
 * @ClassName:HostInfoController.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 主机备注信息
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/host")
public class HostInfoController {


    private static final Logger logger = LoggerFactory.getLogger(HostInfoController.class);


    @Resource
    private SystemInfoService systemInfoService;
    @Resource
    private LogInfoService logInfoService;


    /**
     * 保存主机备注信息
     *
     * @param SystemInfo
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "save")
    public String saveHostInfo(SystemInfo SystemInfo, Model model, HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(SystemInfo.getId())) {
                systemInfoService.save(SystemInfo);
            } else {
                SystemInfo ho = systemInfoService.selectById(SystemInfo.getId());
                ho.setRemark(SystemInfo.getRemark());
                systemInfoService.updateById(ho);
            }

        } catch (Exception e) {
            logger.error("保存主机备注信息错误：", e);
            logInfoService.save(SystemInfo.getHostname(), "保存主机备注信息错误：" + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/dash/systemInfoList";
    }


}
