package com.wgcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.*;
import com.wgcloud.service.*;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wgcloud.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:AppInfoController.java     
 * @version v2.1
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: AppInfoController.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
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
    

	    
	/**
     * 根据条件查询进程列表
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="list")
	public String AppInfoList(AppInfo appInfo, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			PageInfo pageInfo  = appInfoService.selectByParams(params, appInfo.getPage(), appInfo.getPageSize());
			PageUtil.initPageNumber(pageInfo,model);
			model.addAttribute("pageUrl", "/appInfo/list?1=1");
			model.addAttribute("page", pageInfo);
		} catch (Exception e) {
			logger.error("查询进程信息错误",e);
			logInfoService.save("查询进程信息错误",e.toString(),StaticKeys.LOG_ERROR);

		}
		return "app/list";
	}
	

    /**
     * 保存应用监控信息
     * @param AppInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="save")
    public String saveAppInfo(AppInfo AppInfo, Model model,HttpServletRequest request) {
    	 try {
    		 appInfoService.save(AppInfo);
		} catch (Exception e) {
			logger.error("保存进程错误：",e);
			logInfoService.save(AppInfo.getHostname(),"保存进程错误："+e.toString(),StaticKeys.LOG_ERROR);
		}
    	 return "redirect:/appInfo/list";
    }


    /**
     * 查看该应用统计图
     * @param AppInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="view")
    public String viewChart(Model model,HttpServletRequest request) {
    	String errorMsg = "查看进程统计图错误：";
    	String id = request.getParameter("id");
		String date = request.getParameter("date");
    	AppInfo appInfo = new AppInfo();
		try {
			appInfo = appInfoService.selectById(id);
			Map<String, Object> params = new HashMap<String, Object>();
			if(StringUtils.isEmpty(date)){
				date = DateUtil.getCurrentDate();
			}
			dashboardService.setDateParam(date, params);
			model.addAttribute("datenow", date);
			model.addAttribute("dateList", dashboardService.getDateList());
			params.put("appInfoId", appInfo.getHostname()+"-"+appInfo.getAppPid());
			model.addAttribute("appInfo", appInfo);
			List<AppState> appStateList = appStateService.selectAllByParams(params);
			model.addAttribute("appStateList", JSONUtil.parseArray(appStateList));
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(appInfo.getHostname()+":"+appInfo.getAppPid(),errorMsg+e.toString(),StaticKeys.LOG_ERROR);
		}
		return "app/view";
    }
    

    /**
     * 删除进程
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="del")
    public String delete(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
    	String errorMsg = "删除进程信息错误：";
    	AppInfo appInfo = new AppInfo();
		try {
			if(!StringUtils.isEmpty(request.getParameter("id"))){
				appInfo = appInfoService.selectById(request.getParameter("id"));
				appInfoService.deleteById(request.getParameter("id").split(","));
			}
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(appInfo.getHostname()+":"+appInfo.getAppPid(),errorMsg+e.toString(),StaticKeys.LOG_ERROR);
		}
		
        return "redirect:/appInfo/list";
    }   
    

}
