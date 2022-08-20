package com.wgcloud.filter;


import com.wgcloud.config.CommonConfig;
import com.wgcloud.entity.AccountInfo;
import com.wgcloud.util.staticvar.StaticKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version v2.3
 * @ClassName:AuthRestFilter.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: http请求过滤器，拦截不是从路由过来的请求
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@WebFilter(filterName = "authRestFilter", urlPatterns = {"/*"})
public class AuthRestFilter implements Filter {

    static Logger log = LoggerFactory.getLogger(AuthRestFilter.class);

    @Autowired
    CommonConfig commonConfig;

    String[] static_resource = {"/agent/minTask", "/login/toLogin", "/login/login", "/appInfo/agentList", "/static/"};

    String[] dash_views = {"/dash/main", "/dash/systemInfoList", "/dash/detail", "/dash/chart"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpSession session = request.getSession();
        AccountInfo accountInfo = (AccountInfo) session.getAttribute(StaticKeys.LOGIN_KEY);
        String uri = request.getRequestURI();
        log.debug("uri----" + uri);
        String servletPath = request.getServletPath();
        log.debug("servletPath----" + servletPath);
        menuActive(session, uri);
        for (String ss : static_resource) {
            if (servletPath.startsWith(ss)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        if (accountInfo == null) {
            for (String ss : dash_views) {
                if (servletPath.startsWith(ss) && "true".equals(commonConfig.getDashView()) && request.getParameter(StaticKeys.DASH_VIEW_ACCOUNT) != null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        if (accountInfo == null) {
            response.sendRedirect("/wgcloud/login/toLogin");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     * 添加菜单标识
     *
     * @param session
     * @param uri
     */
    public void menuActive(HttpSession session, String uri) {
        if (uri.indexOf("/log/") > -1) {
            session.setAttribute("menuActive", "21");
            return;
        }
        if (uri.indexOf("/dash/main") > -1) {
            session.setAttribute("menuActive", "11");
            return;
        }
        if (uri.indexOf("/dash/systemInfoList") > -1 || uri.indexOf("/dash/detail") > -1 || uri.indexOf("/dash/chart") > -1) {
            session.setAttribute("menuActive", "12");
            return;
        }
        if (uri.indexOf("/appInfo") > -1) {
            session.setAttribute("menuActive", "13");
            return;
        }
        if (uri.indexOf("/mailset") > -1) {
            session.setAttribute("menuActive", "31");
            return;
        }
        if (uri.indexOf("/dbInfo") > -1) {
            session.setAttribute("menuActive", "41");
            return;
        }
        if (uri.indexOf("/dbTable") > -1) {
            session.setAttribute("menuActive", "42");
            return;
        }
        if (uri.indexOf("/heathMonitor") > -1) {
            session.setAttribute("menuActive", "51");
            return;
        }
        session.setAttribute("menuActive", "11");
        return;

    }

}
