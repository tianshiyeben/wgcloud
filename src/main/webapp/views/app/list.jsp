<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用进程监控列表</title>
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
        <li class='title'>应用进程监控管理</li>
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
             <div class='btn-group'>
             <a class='btn' href='${ctx }/sys/app/add'><i class='icon-laptop'></i>添加监控进程</a>
             </div>
          </div>
          </div>
          
          <div class="panel-body filters">
            <div class="row">
               <div class="col-md-9"></div>
              <div class="col-md-3">
                <div class="input-group">
                </div>
              </div>
            </div>
          </div>
          
          
          
          <table class='table'>
            <thead style="font-size:14px">
              <tr>
                <th>序号</th>
                <th>HOST</th>
                <th>进程ID</th>
                <th>进程名称</th>
                <th>创建时间</th>
                <th class='actions'>
                  Actions
                </th>
              </tr>
            </thead>
            <tbody>
             <c:set var="index" value="0"></c:set>
             <c:forEach items="${page.objects}" var="appInfoBean">
              	  <c:set var="index" value="${index+1}" />
             	  <tr style="font-size:12px">
	                <td>${index}</td>
	                <td>${appInfoBean.hostname}</td>
	                <td>${appInfoBean.appPid}</td>
	                <td>
	                	<c:choose>
						     <c:when test="${fn:length(appInfoBean.appName)>30}">
						     	<span tile="${appInfoBean.appName }">${fn:substring(appInfoBean.appName, 0, 30)}...</span>
						     </c:when>
						     <c:otherwise>${appInfoBean.appName}</c:otherwise>
				        </c:choose>
	                </td>
	                <td><fmt:formatDate value="${appInfoBean.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td class='action'>
		              <a class="btn btn-success" data-toggle="tooltip" href='${ctx }/sys/app/viewChart?id=${appInfoBean.id}' title="查看监控">
	                    <i class="icon-zoom-in"></i>
	                  </a>
	                  <a class='btn btn-info' href='${ctx }/sys/app/edit?id=${appInfoBean.id}'>
	                    <i class='icon-edit'></i>
	                  </a>
	                  <a class='btn btn-danger' href="javascript:del('${appInfoBean.id}','${appInfoBean.appName}')">
	                    <i class='icon-trash'></i>
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
  <script type="text/javascript" src="${ctx}/static/js/app/list.js"></script>
</body>
</html>
