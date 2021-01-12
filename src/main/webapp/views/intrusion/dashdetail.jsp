<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HOST主机入侵检测信息详情</title>
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

<link href="${ctx}/static/common/code.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/echarts-2.2.7/doc/example/www/js/echarts.js"></script>
    
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
        <li class='title'>HOST入侵检测信息详情(${info.hostname })</li>
      </ul>
    </section>
    <!-- Content -->
    <div id='content'>
        <div class='panel panel-default grid'>
          <div class='panel-heading'>
            <i class='icon-time icon-large'></i>
            	检测时间：<fmt:formatDate value="${info.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
           	<div class='panel-tools'>
             <div class='btn-group'>
               <a class='btn' onclick="history.back()">返回</a>
           </div>
          </div>
          </div>
          
		<div class="content body">
			<section id="download">
		  	<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">RPC开放状态<br># rpcinfo -p<br> ${info.rpcinfo }
		    </code></pre>
		    
		    <pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">检查网络是否是promisc模式，正常网卡不该在promisc模式<br># ip link | grep promisc<br>${info.promisc }
		    </code></pre>
		    
		    <pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">检查passwd文件修改时间<br># ls -l /etc/passwd<br>${info.passwdInfo }
		    </code></pre>
		    
		    <pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">检查系统内核模块<br># lsmod<br>${info.lsmod }
		    </code></pre>
		    
		    <pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">检查系统计划任务<br># cat /etc/crontab<br>${info.crontab }
		    </code></pre>
			</section>
		
		</div>	    
    
			      
        </div>
      </div>
  </div>
  <!-- Footer -->
  <jsp:include page="/common/footer.jsp"></jsp:include>
  <script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-2.2.3.min.js"></script>
</body>
</html>
