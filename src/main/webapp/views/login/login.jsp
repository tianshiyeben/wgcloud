<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html class="login-page" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>WGClOUD登录页面</title>

<link type="text/css" rel="stylesheet" href="${ctx}/static/logincss/components.css.xhtml?ln=primefaces&amp;v=6.0" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/logincss/fix.css.xhtml?ln=suribootstrap&amp;v=2.0.10" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/logincss/suri-bootstrap.css.xhtml?ln=suribootstrap&amp;v=2.0.10" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/logincss/bootstrap.min.css.xhtml?ln=suribootstrap&amp;v=2.0.10" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/logincss/font-awesome.min.css.xhtml?ln=suribootstrap&amp;v=2.0.10" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/logincss/ionicons.min.css.xhtml?ln=suribootstrap&amp;v=2.0.10" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/logincss/atmosfero.css.xhtml?ln=atmosfero&amp;v=2.14.0" />

<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/hierapolis/assets/images/favicon.png"/>
		
</head>
<body class="login-page">

<div class="login-box">
		<div class="login-box-content">
			<div class="login-logo">
				<a href="http://www.wgstart.com/" target="_blank"><img src="${ctx}/static/hierapolis/assets/images/logo-atmosfero-w.gif"></a>
	  		</div>
			<div class="login-box-body">
			<p class="login-box-msg"><label style="color:red;font-weight:bold;"><c:out value="${error}" /></label></p>
				
		<form id="form1" action="${ctx }/sys/login/login"  method="post" onsubmit="return login()" class="form-horizontal">
	
          	<div class="form-group">
           	<div class="col-sm-12">
              		<input id="userName" name="userName" placeholder="输入帐号" maxlength="50" autocomplete="off" class="form-control" type="text">
            	</div>
          	</div>
          	
          	<div class="form-group">
           	<div class="col-sm-12">
              		<input placeholder='输入密码' id="passwd" name="passwd" maxlength="20" class="form-control" autocomplete="off" type="password">
              		<input type="hidden" id="md5pwd" name="md5pwd" value=""/>
            	</div>
          	</div>
           
        	<div class="form-group">
           	<div class="col-sm-12">
           	<table><tr><td><input id="code" style="width:255px" name="valcode" placeholder="请填写验证码" maxlength="80" class="form-control" autocomplete="off" type="text"></td>
              <td><a href="#" title="看不清？点击换一张" onclick="changeImg()" ><img src="${ctx }/sys/code/get" id="imgObj"  width="70" height="32" /></a></td></tr></table>
            	</div>
          	</div>
             	
         	<div class="form-group">
           	<div class="col-sm-12">
           		<input value="登录" class="btn btn-primary btn-block" type="submit">
            	</div>
          	</div>
             	
          	<div class="form-group">
          	</div>
		</form>
		</div>
		</div>
	</div>
		
		
		
    <jsp:include page="/common/footer.jsp"></jsp:include>
    <script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-2.2.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/common/md5.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/login/login.js"></script>
</body>
</html>
