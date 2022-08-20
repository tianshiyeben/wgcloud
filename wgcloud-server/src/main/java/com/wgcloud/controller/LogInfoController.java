package com.wgcloud.controller;

import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.LogInfo;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.CodeUtil;
import com.wgcloud.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:LogInfoController.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: LogInfoController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/log")
public class LogInfoController {


    private static final Logger logger = LoggerFactory.getLogger(LogInfoController.class);

    @Resource
    private LogInfoService logInfoService;

    /**
     * 根据条件查询日志信息列表
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    public String LogInfoList(LogInfo logInfo, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            StringBuffer url = new StringBuffer();
            String hostname = null;
            if (!StringUtils.isEmpty(logInfo.getHostname())) {
                hostname = CodeUtil.unescape(logInfo.getHostname());
                params.put("hostname", hostname.trim());
                url.append("&hostname=").append(CodeUtil.escape(hostname));
            }
            PageInfo pageInfo = logInfoService.selectByParams(params, logInfo.getPage(), logInfo.getPageSize());
            PageUtil.initPageNumber(pageInfo, model);

            model.addAttribute("pageUrl", "/log/list?1=1" + url.toString());
            model.addAttribute("page", pageInfo);
            model.addAttribute("logInfo", logInfo);
        } catch (Exception e) {
            logger.error("查询日志错误", e);
        }
        return "log/list";
    }

    /**
     * 查看日志信息
     *
     * @param LogInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "view")
    public String viewLogInfo(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        LogInfo logInfo;
        try {
            logInfo = logInfoService.selectById(id);
            model.addAttribute("logInfo", logInfo);
        } catch (Exception e) {
            logger.error("查看日志信息：", e);
        }
        return "log/view";
    }

}
