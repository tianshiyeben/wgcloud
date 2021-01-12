package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.common.Page;
import com.entity.app.AppInfo;
import com.entity.host.SystemInfo;
import com.entity.user.AccountInfo;
import com.service.app.AppInfoService;
import com.service.app.AppStateService;
import com.service.dash.DashboardService;
import com.service.host.SystemInfoService;
import com.service.log.LogInfoService;
import com.util.DateUtil;
import com.util.staticvar.HostKeys;
import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:AppInfoController.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: AppInfoController.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Controller
@RequestMapping("/sys/app")
public class AppInfoController {
	
	 
	private static final Logger logger = LoggerFactory.getLogger(AppInfoController.class);
	
	private String MENU_MARK = "app";//菜单标识
	
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
	DashboardService dashboardService;
    

	    
	/**
     * 根据条件查询app应用信息列表
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="list")
	public String AppInfoList(Model model,HttpServletRequest request) {
		String curPage = request.getParameter(StaticKeys.CUR_PAGE);
		if(StringUtils.isEmpty(curPage)){
			curPage = "1";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer url = new StringBuffer();
		Page page = null;
		try {
			page = appInfoService.selectByParams(params, Integer.valueOf(curPage), StaticKeys.PAGE_SIZE);
			model.addAttribute("pageUrl", "/sys/app/list?1=1"+ url.toString());
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("查询出错",e);
		}
		model.addAttribute(MENU_MARK, StaticKeys.MENU_ACTIVE);
		return "app/list";
	}
	
	 /**
     * 转到添加页面
     * @param AppInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="add")
    public String addAppInfo( Model model,HttpServletRequest request) {
    	 model.addAttribute(MENU_MARK, StaticKeys.MENU_ACTIVE);
    	 try {
    		 AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
    		 List<SystemInfo> sysInfoList = systemInfoService.selectByAccountId(accountInfo.getId());
    		 model.addAttribute("sysInfoList", sysInfoList);
		} catch (Exception e) {
			logger.error("转到添加页面错误：",e);
		}
    	 return "app/add";
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
    		 AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
    		 Map<String, Object> params = new HashMap<String, Object>();
			 params.put("account", accountInfo.getAccount());
    		 AppInfo.setAccount(accountInfo.getAccount());
    		 AppInfo.setAccountId(accountInfo.getId());
    		 appInfoService.save(AppInfo);
		} catch (Exception e) {
			logger.error("保存APP应用信息错误：",e);
		}
    	 return "redirect:/sys/app/list";
    }
	
    
    /**
     * 查看APP应用信息
     * @param AppInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="view")
    public String viewAppInfo(Model model,HttpServletRequest request) {
    	 String errorMsg = "转到查看APP应用页面错误：";
    	 String id = request.getParameter("id");
    	 AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
    	 model.addAttribute(MENU_MARK, StaticKeys.MENU_ACTIVE);
    	 AppInfo AppInfo;
		try {
			AppInfo = appInfoService.selectById(id);
			model.addAttribute("appInfo", AppInfo);
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(accountInfo.getAccount(),"",errorMsg+e,StaticKeys.LOG_ERROR);
		}
    	return "app/view";
    }
    
    /**
     * 修改APP应用信息
     * @param AppInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="edit")
    public String editAppInfo(Model model,HttpServletRequest request) {
    	String errorMsg = "转到修改APP应用页面错误：";
    	String id = request.getParameter("id");
    	AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
    	AppInfo AppInfo;
		try {
			AppInfo = appInfoService.selectById(id);
			model.addAttribute("appInfo", AppInfo);
			model.addAttribute(MENU_MARK, StaticKeys.MENU_ACTIVE);
	   		List<SystemInfo> sysInfoList = systemInfoService.selectByAccountId(accountInfo.getId());
	   		model.addAttribute("sysInfoList", sysInfoList);
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(accountInfo.getAccount(),"",errorMsg+e,StaticKeys.LOG_ERROR);
		}
		return "app/edit";
    }
    
    
    /**
     * 查看该应用统计图
     * @param AppInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="viewChart")
    public String viewChart(Model model,HttpServletRequest request) {
    	String errorMsg = "查看该应用统计图错误：";
    	String id = request.getParameter("id");
    	String date = request.getParameter("date");
    	String am = request.getParameter("am");
    	AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
    	AppInfo AppInfo;
    	Page page = null;
		try {
			AppInfo = appInfoService.selectById(id);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("appInfoId", id);
			if(StringUtils.isEmpty(date)){
				date = DateUtil.getCurrentDate();
				am = dashboardService.getDefaultAm();
			}
			dashboardService.setDateParam(am, date, params);
			//组装日期查询字符串集合
			model.addAttribute("dateList", dashBoardService.getDateList());
			model.addAttribute("date", date);
			model.addAttribute("am", am);
			page =  appStateService.selectByParams(params, 1, HostKeys.CHART_DATA_SIZE);
			String appStateChart = dashBoardService.appStateChar(page.getObjects());
			//echart图长度计算
			if(page.getObjects().size()>HostKeys.BAR_SIZE){
				model.addAttribute("chartWidth", ";width:"+page.getObjects().size()*HostKeys.BAR_WIDTH);
			}
			model.addAttribute("appInfo", AppInfo);
			model.addAttribute("appStateChart", appStateChart);
			model.addAttribute(MENU_MARK, StaticKeys.MENU_ACTIVE);
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(accountInfo.getAccount(),"",errorMsg+e,StaticKeys.LOG_ERROR);
		}
		return "app/appState";
    }
    
    /**
     * 修改APP应用信息
     * @param AppInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="update")
    public String updateAppInfo(AppInfo AppInfo, Model model,HttpServletRequest request) {
    	String errorMsg = "修改APP应用信息错误：";
    	AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
    	 try {
    		 appInfoService.updateById(AppInfo);
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(accountInfo.getAccount(),"",errorMsg+e,StaticKeys.LOG_ERROR);
		}
    	 return "redirect:/sys/app/list";
    }
    
    /**
     * 删除APP应用信息
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="del")
    public String delete(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
    	String errorMsg = "删除app信息错误：";
    	AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
		try {
			if(!StringUtils.isEmpty(request.getParameter("id"))){
				appInfoService.deleteById(request.getParameter("id").split(","));
			}
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(accountInfo.getAccount(),"",errorMsg+e,StaticKeys.LOG_ERROR);
		}
		
        return "redirect:/sys/app/list";
    }   
    

}
