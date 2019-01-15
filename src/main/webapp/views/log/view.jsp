<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看日志信息详情</title>
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
        <li class='title'>日志管理</li>
        <li><a href="#">查看日志</a></li>
      </ul>
      <div id='toolbar'></div>
    </section>
    <!-- Content -->
          <div id='content'>
        <div class='panel panel-default'>
          <div class='panel-heading'>
            <i class='icon-edit icon-large'></i>
          </div>
          <div class='panel-body'>
              <fieldset>
                <legend>用户帐号：${logInfo.account }</legend>
                <div class='form-group'>
                  <label class='col-lg-2 control-label'>状态</label>
                  	<div> 
						<c:if test="${logInfo.state == 1}"><font color="red">失败</font></c:if>
		                <c:if test="${logInfo.state == 0}"><font color="green">成功</font></c:if>
					</div>
                </div>
                <div class='form-group'>
                  <label class='col-lg-2 control-label'>主机名称</label>
                  <div>${logInfo.hostname}</div>
                </div>
                <div class='form-group'>
                  <label class='col-lg-2 control-label'>创建时间</label>
                  <div><fmt:formatDate value="${logInfo.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                </div>
                <div class='form-group'>
                  <label class='col-lg-2 control-label'>描述</label>
                  <div>${logInfo.infoContent}</div>
                </div>
              </fieldset>
              <div class='form-actions'>
                <a class='btn' href='javascript:cancel()'>返回</a>
              </div>
          </div>
        </div>
      </div>
  </div>
  <!-- Footer -->
  <jsp:include page="/common/footer.jsp"></jsp:include>
  <script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-2.2.3.min.js"></script>
  <script type="text/javascript" src="${ctx}/static/js/log/add.js"></script>
</body>
</html>
