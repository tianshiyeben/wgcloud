<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id='sidebar'>
  <i class='icon-align-justify icon-large' id='toggle'></i>
  <ul id='dock'>
  
    <li class='${dashboard}launcher'>
      <i class='icon-dashboard'></i>
      <a href="${pageContext.request.contextPath}/sys/dash/main">监控仪表盘</a>
    </li>
     <li class='${app}launcher'>
	      <i class='icon-coffee'></i>
	      <a href="${pageContext.request.contextPath}/sys/app/list">应用进程监控</a>
	    </li>
  <li class='${intrusion}launcher'>
      <i class='icon-fire'></i>
      <a href="${pageContext.request.contextPath}/sys/dash/intrusionMain">入侵检测</a>
    </li>
	<li class='${logActive}launcher'>
      <i class='icon-cloud'></i>
      <a href="${pageContext.request.contextPath}/sys/log/list">系统日志信息</a>
    </li>
    <li class='${msgActive}launcher'>
      <i class='icon-comments'></i>
      <a href="${pageContext.request.contextPath}/sys/msg/list">信息发送日志</a>
    </li>
 
	
  </ul>
  <div data-toggle='tooltip' id='beaker' title='Made by lab2023'></div>
</section>