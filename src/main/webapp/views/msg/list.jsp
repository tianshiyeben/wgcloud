<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息发送记录列表</title>
<link href="${ctx}/static/hierapolis/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/hierapolis/assets/stylesheets/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/hierapolis/assets/images/favicon.png"/>

</head>
<body class='main page'>
  <!-- Navbar -->
  <jsp:include page="/common/header.jsp"></jsp:include>
  <div id='wrapper'>
    <!-- Sidebar -->
     <jsp:include page="/common/menu.jsp"></jsp:include>
    <!-- Tools -->
    <section id='tools'>
      <ul class='breadcrumb' id='breadcrumb'>
        <li class='title'>信息发送管理</li>
        <li><a href="#">查询列表</a></li>
      </ul>
    </section>
    <!-- Content -->
    <div id='content'>
        <div class='panel panel-default grid'>
          <div class='panel-heading'>
            <i class='icon-table icon-large'></i>
            	查询列表
           	<div class='panel-tools'>
             <div class='btn-group'></div>
          </div>
          </div>
          <div class="panel-body filters">
            <div class="row">
             <div class="col-md-9">
             </div>
              <div class="col-md-3">
              </div>
            </div>
          </div>
          <table class='table'>
            <thead style="font-size:14px">
              <tr>
                <th>序号</th>
                <th>邮箱</th>
                <th>标题</th>
                <th>内容</th>
                <th>创建时间</th>
                <th class='actions'>
                  Actions
                </th>
              </tr>
            </thead>
            <tbody>
             <c:set var="index" value="0"></c:set>
             <c:forEach items="${page.objects}" var="msgInfoBean">
              	  <c:set var="index" value="${index+1}" />
             	  <tr style="font-size:12px">
	                <td>${index}</td>
	                <td>${msgInfoBean.acceptInfo}</td>
	                <td>${msgInfoBean.msgTitle}</td>
	                <td>
	                	<c:choose>
						     <c:when test="${fn:length(msgInfoBean.infoContent)>40}">
						     	<a title="${msgInfoBean.infoContent}" href="${ctx}/sys/msg/view?id=${msgInfoBean.id}">
						     	<c:out value="${fn:substring(msgInfoBean.infoContent, 0, 40)}..."></c:out>
						     	</a>
						     </c:when>
						     <c:otherwise>
						     	<a title="${msgInfoBean.infoContent}" href="${ctx}/sys/msg/view?id=${msgInfoBean.id}">
						     	<c:out value="${msgInfoBean.infoContent}"></c:out>
						     	</a>
						     </c:otherwise>
				        </c:choose>
	                </td>
	                <td><fmt:formatDate value="${msgInfoBean.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td class='action'>
		              <a class="btn btn-success" data-toggle="tooltip" href='${ctx }/sys/msg/view?id=${msgInfoBean.id}' title="查看">
	                    <i class="icon-zoom-in"></i>
	                  </a>
	                </td>
	              </tr>
             </c:forEach>
            </tbody>
          </table>
          
          <jsp:include page="/common/page.jsp"></jsp:include>
          
        </div>
      </div>
  </div>
  <!-- Footer -->
  <jsp:include page="/common/footer.jsp"></jsp:include>
  <script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-2.2.3.min.js"></script>
  <script type="text/javascript" src="${ctx}/static/js/msg/list.js"></script>
</body>
</html>
