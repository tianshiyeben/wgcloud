package com.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:LoginInterceptor.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: LoginInterceptor.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	String[] static_resource = {"login/toLogin","login/login","sys/code",".css",".js",".jpg",".png",".ico",".gif","font"};
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        for (String ss : static_resource) {
            if (uri.indexOf(ss) != -1) {
            	 return true;
            }
        }
        Object user = request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
        if (user != null) {
            return true;
        }
        response.sendRedirect("/wgcloud/sys/login/toLogin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}