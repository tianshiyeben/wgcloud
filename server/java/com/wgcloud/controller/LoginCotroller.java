package com.wgcloud.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wgcloud.config.CommonConfig;
import com.wgcloud.service.AppInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wgcloud.entity.AccountInfo;
import com.wgcloud.util.shorturl.MD5;
import com.wgcloud.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:LoginCotroller.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: LoginCotroller.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Controller
@RequestMapping(value="/login")
public class LoginCotroller {
    
	private static final Logger logger = LoggerFactory.getLogger(LoginCotroller.class);

	@Resource
	private CommonConfig commonConfig;

	 /**
     * 转向到登录页面
     * @param model
     * @param request
     * @return
     */
	@RequestMapping("toLogin")
    public String toLogin(Model model,HttpServletRequest request) {
    	return "login/login";
    }
    
	/**
	 * 登出系统
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("loginOut")
	public String loginOut(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login/toLogin";
	}
	
   /**
    * 管理员登录验证
    * @param model
    * @param request
    * @return
    */
    @RequestMapping(value="login")
    public String login(Model model,HttpServletRequest request) {
    	String userName = request.getParameter("userName");
    	String passwd = request.getParameter("md5pwd");
    	String code = request.getParameter(StaticKeys.SESSION_CODE);
    	HttpSession session = request.getSession();
    	try {
	    	if(!StringUtils.isEmpty(userName )&& !StringUtils.isEmpty(passwd) && !StringUtils.isEmpty(code)){
	    		if(!code.equals(session.getAttribute(StaticKeys.SESSION_CODE))){
	    			model.addAttribute("error", "验证码错误");
        	    	return "login/login";
	    		}
				AccountInfo accountInfo = new AccountInfo();
				if(MD5.GetMD5Code(commonConfig.getAdmindPwd()).equals(passwd) && StaticKeys.ADMIN_ACCOUNT.equals(userName)){
					accountInfo.setAccount(StaticKeys.ADMIN_ACCOUNT);
					accountInfo.setId(StaticKeys.ADMIN_ACCOUNT);
					request.getSession().setAttribute(StaticKeys.LOGIN_KEY, accountInfo);
					return "redirect:/dash/main";
				}
	    	}
    	} catch (Exception e) {
			logger.error("登录异常：",e);
		}
    	model.addAttribute("error", "帐号或者密码错误");
    	return "login/login";
    }
    
    
}