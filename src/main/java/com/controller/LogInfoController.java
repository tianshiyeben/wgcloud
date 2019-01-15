package com.controller;

import java.util.HashMap;
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
import com.entity.log.LogInfo;
import com.service.log.LogInfoService;
import com.util.CodeUtil;
import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:LogInfoController.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: LogInfoController.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Controller
@RequestMapping("/sys/log")
public class LogInfoController {
	
	 
	private static final Logger logger = LoggerFactory.getLogger(LogInfoController.class);
	
	private String MENU_MARK = "logActive";//菜单标识
	
    @Resource
    private LogInfoService logInfoService;
	    
	/**
     * 根据条件查询日志信息列表
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="list")
	public String LogInfoList(Model model,HttpServletRequest request) {
		String curPage = request.getParameter(StaticKeys.CUR_PAGE);
		if(StringUtils.isEmpty(curPage)){
			curPage = "1";
		}
		String account = request.getParameter("account");
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer url = new StringBuffer();
		if(!StringUtils.isEmpty(account)) {
			account = CodeUtil.unescape(account);
			params.put("account", account);
			url.append("&account=").append(CodeUtil.escape(account));
			model.addAttribute("account", account);
		}
		Page page = null;
		try {
			page = logInfoService.selectByParams(params, Integer.valueOf(curPage), StaticKeys.PAGE_SIZE);
			model.addAttribute("pageUrl", "/sys/log/list?1=1"+ url.toString());
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("查询出错",e);
		}
		model.addAttribute(MENU_MARK, StaticKeys.MENU_ACTIVE);
		return "log/list";
	}
	
    /**
     * 查看日志信息
     * @param LogInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="view")
    public String viewLogInfo(Model model,HttpServletRequest request) {
    	 model.addAttribute(MENU_MARK, StaticKeys.MENU_ACTIVE);
    	 String id = request.getParameter("id");
    	 LogInfo logInfo;
		try {
			logInfo = logInfoService.selectById(id);
			model.addAttribute("logInfo", logInfo);
		} catch (Exception e) {
			logger.error("查看日志信息：",e);
		}
    	return "log/view";
    }
    
    
    /**
     * 删除
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="del")
    public String delete(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
    	Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(request.getParameter("id"))){
			try {
				logInfoService.deleteById(request.getParameter("id").split(","));
			} catch (Exception e) {
				logger.error("删除日志异常：",e);
			}
		}
        return "redirect:/sys/log/list";
    }   
    
}
