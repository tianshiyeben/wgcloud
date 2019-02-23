<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HOST主机内存使用率列表</title>
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/hierapolis/assets/images/favicon.png"/>


<link rel="stylesheet" href="${ctx}/static/AdminLTE/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${ctx}/static/AdminLTE/bootstrap/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="${ctx}/static/AdminLTE/bootstrap/css/ionicons.min.css">
<link rel="stylesheet" href="${ctx}/static/AdminLTE/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${ctx}/static/AdminLTE/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${ctx}/static/AdminLTE/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet" href="${ctx}/static/AdminLTE/plugins/morris/morris.css">

<link href="${ctx}/static/hierapolis/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/hierapolis/assets/stylesheets/font-awesome.min.css" rel="stylesheet" type="text/css" />
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
        <li class='title'>HOST主机内存使用率列表</li>
      </ul>
    </section>
    <!-- Content -->
    <div id='content'>
        <div class='panel panel-default grid'>
          <div class='panel-heading'>
            <i class='icon-time icon-large'></i>
            	<c:if test="${date != '' && date != null}">最后更新时间：<c:out value="${date}" /></c:if>
           	<div class='panel-tools'>
             <div class='btn-group'>
           </div>
          </div>
          </div>
          <div class='panel-body filters'>
          </div>
       
        <section class="content">
       <div class="row">
       <c:if test="${list== null || fn:length(list) == 0}">
       		&nbsp;&nbsp;&nbsp;&nbsp;暂时没有数据，如果已部署成功，请耐心等待30分钟
       </c:if>
       <c:forEach items="${list}"  var="dash" >
      			<div class="col-lg-3 col-xs-6">
      			    <c:if test="${dash.version == 'connerror'}"><div class="small-box bg-gray">
	      			    <div class="inner">
			              <p>${dash.hostname}</p>
			              <p>连接失败,请检查服务器是否存活或密码是否正确</p>
			            </div>
      			    </c:if>
      			    
      			    <c:if test="${dash.version != 'connerror'}">
      				<c:if test="${dash.memPer <= 65}"><div class="small-box bg-green"></c:if>
      				<c:if test="${dash.memPer > 65 && dash.memPer <= 75}"><div class="small-box bg-aqua"></c:if>
      				<c:if test="${dash.memPer > 75 && dash.memPer <= 88}"><div class="small-box bg-yellow"></c:if>
      				<c:if test="${dash.memPer > 88}"><div class="small-box bg-red"></c:if>
	      				<div class="inner">
			              <h3>${dash.memPer}%</h3>
			              <p>${dash.hostname}</p>
			            </div>
      				</c:if>
      				
		            <div class="icon">
		              <i class="ion ion-stats-bars"></i>
		            </div>
		            <a href="${ctx}/sys/dash/detail?hostname=${dash.hostname}" class="small-box-footer">更多信息 <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
				</div>
		</c:forEach>
      </div>
      </section>
      
        </div>
      </div>
  </div>
  <!-- Footer -->
  <jsp:include page="/common/footer.jsp"></jsp:include>
  <script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-2.2.3.min.js"></script>
</body>
</html>
