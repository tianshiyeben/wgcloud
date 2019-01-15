<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改应用进程监控信息</title>
<link href="${ctx}/static/hierapolis/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/hierapolis/assets/stylesheets/font-awesome.min.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/common/My97DatePicker/4.8/WdatePicker.js" type="text/javascript"></script>
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
        <li class='title'>应用进程监控信息管理</li>
        <li><a href="#">修改应用进程监控</a></li>
      </ul>
      <div id='toolbar'></div>
    </section>
    <!-- Content -->
          <div id='content'>
        <div class='panel panel-default'>
          <div class='panel-heading'>
            <i class='icon-edit icon-large'></i>
            请先在HOST服务器查看进程ID后再添加。PS：每次应用重启后进程ID都可能变化，所以需要在这进行同步修改
          </div>
          <div class='panel-body'>
            <form class='form-horizontal' action="${ctx }/sys/app/update" onsubmit="return dosubmit()" method="post">
              <input name="id" type="hidden"  value="${appInfo.id}" />
              <fieldset>
              	<div class='form-group'>
                  <label class='col-lg-2 control-label'>HOST<font color="red">*</font></label>
                  <div class='col-lg-10'>
                    <select id="hostname" name="hostname" value="" class='form-control'>
                    	<option value="">请选择</option>
	                    <c:forEach items="${sysInfoList}" var="sysInfoBean">
	                    <option value="${sysInfoBean.hostname}">${sysInfoBean.hostname}</option>
	                    </c:forEach>
                    </select>
                  </div>
                </div>
                <div class='form-group'>
                  <label class='col-lg-2 control-label'>进程ID</label>
                  <div class='col-lg-10'>
                    <input  id="appPid" name="appPid" value="${appInfo.appPid }" autocomplete="off" maxlength="12" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" class='form-control' placeholder='如26365' type='text'/>
                  </div>
                </div>
                <div class='form-group'>
                  <label class='col-lg-2 control-label'>进程名称</label>
                  <div class='col-lg-10'>
                    <input  id="appName" name="appName" value="${appInfo.appName }" autocomplete="off" maxlength="30" class='form-control' placeholder='如网站后台服务' type='text'/>
                  </div>
                </div>
              </fieldset>
              <div class='form-actions'>
                <button class='btn btn-default' type='submit'>提交</button>
                <a class='btn' href='javascript:cancel()'>取消</a>
              </div>
            </form>
          </div>
        </div>
      </div>
  </div>
  <!-- Footer -->
  <jsp:include page="/common/footer.jsp"></jsp:include>
  <script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-2.2.3.min.js"></script>
  <script type="text/javascript" src="${ctx}/static/js/app/add.js"></script>
  <script type="text/javascript">
  	var hostname = "${appInfo.hostname }";
  	$("#hostname").val(hostname);
  
  </script>
</body>
</html>
