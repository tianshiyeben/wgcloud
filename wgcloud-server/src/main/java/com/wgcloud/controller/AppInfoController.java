package com.wgcloud.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.AppInfo;
import com.wgcloud.entity.AppState;
import com.wgcloud.entity.SystemInfo;
import com.wgcloud.service.*;
import com.wgcloud.util.CodeUtil;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.PageUtil;
import com.wgcloud.util.TokenUtils;
import com.wgcloud.util.staticvar.StaticKeys;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:AppInfoController.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: AppInfoController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/appInfo")
public class AppInfoController {


    private static final Logger logger = LoggerFactory.getLogger(AppInfoController.class);

    @Resource
    private AppInfoService appInfoService;

    @Resource
    private AppStateService appStateService;

    @Resource
    private LogInfoService logInfoService;

    @Resource
    private DashboardService dashBoardService;

    @Resource
    private SystemInfoService systemInfoService;

    @Resource
    private HostInfoService hostInfoService;

    @Resource
    private DashboardService dashboardService;

    @Autowired
    private TokenUtils tokenUtils;


    /**
     * agent查询进程列表
     *
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "agentList")
    public String agentList(@RequestBody String paramBean) {
        JSONObject agentJsonObject = (JSONObject) JSONUtil.parse(paramBean);
        if (!tokenUtils.checkAgentToken(agentJsonObject)) {
            logger.error("token is invalidate");
            return "error：token is invalidate";
        }
        Map<String, Object> params = new HashMap<String, Object>();
        if (null == agentJsonObject.get("hostname") || StringUtils.isEmpty(agentJsonObject.get("hostname").toString())) {
            return "";
        }
        params.put("hostname", agentJsonObject.get("hostname").toString());
        try {
            List<AppInfo> appInfoList = appInfoService.selectAllByParams(params);
            return JSONUtil.toJsonStr(appInfoList);
        } catch (Exception e) {
            logger.error("agent获取进程信息错误", e);
            logInfoService.save("agent获取进程信息错误", e.toString(), StaticKeys.LOG_ERROR);

        }
        return "";
    }

    /**
     * 根据条件查询进程列表
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    public String AppInfoList(AppInfo appInfo, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            StringBuffer url = new StringBuffer();
            String hostname = null;
            if (!StringUtils.isEmpty(appInfo.getHostname())) {
                hostname = CodeUtil.unescape(appInfo.getHostname());
                params.put("hostname", hostname.trim());
                url.append("&hostname=").append(CodeUtil.escape(hostname));
            }
            PageInfo pageInfo = appInfoService.selectByParams(params, appInfo.getPage(), appInfo.getPageSize());
            PageUtil.initPageNumber(pageInfo, model);
            model.addAttribute("pageUrl", "/appInfo/list?1=1" + url.toString());
            model.addAttribute("page", pageInfo);
            model.addAttribute("appInfo", appInfo);
        } catch (Exception e) {
            logger.error("查询进程信息错误", e);
            logInfoService.save("查询进程信息错误", e.toString(), StaticKeys.LOG_ERROR);

        }
        return "app/list";
    }


    /**
     * 保存应用监控信息
     *
     * @param AppInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "save")
    public String saveAppInfo(AppInfo AppInfo, Model model, HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(AppInfo.getId())) {
                appInfoService.save(AppInfo);
            } else {
                appInfoService.updateById(AppInfo);
            }
        } catch (Exception e) {
            logger.error("保存进程错误：", e);
            logInfoService.save(AppInfo.getHostname(), "保存进程错误：" + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/appInfo/list";
    }

    /**
     * 添加
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "edit")
    public String edit(Model model, HttpServletRequest request) {
        String errorMsg = "添加进程：";
        String id = request.getParameter("id");
        AppInfo appInfo = new AppInfo();
        try {
            List<SystemInfo> systemInfoList = systemInfoService.selectAllByParams(new HashMap<>());
            model.addAttribute("systemInfoList", systemInfoList);
            if (StringUtils.isEmpty(id)) {
                model.addAttribute("appInfo", appInfo);
                return "app/add";
            }
            appInfo = appInfoService.selectById(id);
            model.addAttribute("appInfo", appInfo);
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(appInfo.getAppPid(), errorMsg + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "app/add";
    }


    /**
     * 查看该应用统计图
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "view")
    public String viewChart(Model model, HttpServletRequest request) {
        String errorMsg = "查看进程统计图错误：";
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        AppInfo appInfo = new AppInfo();
        try {
            appInfo = appInfoService.selectById(id);
            Map<String, Object> params = new HashMap<String, Object>();
            if (StringUtils.isEmpty(date)) {
                date = DateUtil.getCurrentDate();
            }
            dashboardService.setDateParam(date, params);
            model.addAttribute("datenow", date);
            model.addAttribute("dateList", dashboardService.getDateList());
            params.put("appInfoId", appInfo.getId());
            model.addAttribute("appInfo", appInfo);
            List<AppState> appStateList = appStateService.selectAllByParams(params);
            model.addAttribute("appStateList", JSONUtil.parseArray(appStateList));
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(appInfo.getHostname() + ":" + appInfo.getAppPid(), errorMsg + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "app/view";
    }


    /**
     * 删除进程
     *
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "del")
    public String delete(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String errorMsg = "删除进程信息错误：";
        AppInfo appInfo = new AppInfo();
        try {
            if (!StringUtils.isEmpty(request.getParameter("id"))) {
                appInfo = appInfoService.selectById(request.getParameter("id"));
                logInfoService.save("删除进程：" + appInfo.getHostname(), "删除进程：" + appInfo.getHostname() + "：" + appInfo.getAppPid(), StaticKeys.LOG_ERROR);
                appInfoService.deleteById(request.getParameter("id").split(","));
            }
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(appInfo.getHostname() + ":" + appInfo.getAppPid(), errorMsg + e.toString(), StaticKeys.LOG_ERROR);
        }

        return "redirect:/appInfo/list";
    }


}
