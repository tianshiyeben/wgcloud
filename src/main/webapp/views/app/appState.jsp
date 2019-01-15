<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用进程监控详情</title>
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
        <li class='title'>应用进程监控详情</li>
        <li><a href="#">详细信息</a></li>
      </ul>
    </section>
    <!-- Content -->
    <div id='content'>
        <div class='panel panel-default grid'>
          <div class='panel-heading'>
            <i class='icon-table icon-large'></i>
            	HOST：${appInfo.hostname }，进程ID：${appInfo.appPid }，进程名称：${appInfo.appName }
           	<div class='panel-tools'>
             <div class='btn-group'>
               <a class='btn' onclick="history.back()">返回</a>
           </div>
          </div>
          </div>
          <div class='panel-body filters'>
          </div>
       
	       <div align="left" style="margin-bottom: 12px;margin-left: 40px;">
			    <c:forEach items="${dateList}" var="datestr">
			      <c:choose>
			      	<c:when test="${datestr == date}"><small   class="label label-danger"><i class="fa fa-clock-o"></i> ${datestr}</small></c:when>
			      	<c:otherwise><small style="cursor:pointer" onclick="search('${datestr}')" class="label label-primary"><i class="fa fa-clock-o"></i> ${datestr}</small></c:otherwise>
			      </c:choose>
			    </c:forEach>
			    <input type="hidden" id="dateStr" value="${date}" />
		    </div>
			 <div align="left" style="margin-bottom: 30px;margin-left: 40px;">
			      <c:if test="${am eq 'am1'}">
			      	<small   class="label label-danger"><i class="fa fa-clock-o"></i>00:00至05:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am2')" class="label label-primary"><i class="fa fa-clock-o"></i>06:00至11:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am3')" class="label label-primary"><i class="fa fa-clock-o"></i>12:00至17:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am4')" class="label label-primary"><i class="fa fa-clock-o"></i>18:00至23:59</small>
		      	</c:if>
		      	<c:if test="${am eq 'am2'}">
			      	<small style="cursor:pointer" onclick="searchAm('am1')" class="label label-primary"><i class="fa fa-clock-o"></i>00:00至05:59</small>&nbsp;
			      	<small   class="label label-danger"><i class="fa fa-clock-o"></i> 06:00至11:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am3')" class="label label-primary"><i class="fa fa-clock-o"></i>12:00至17:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am4')" class="label label-primary"><i class="fa fa-clock-o"></i>18:00至23:59</small>
		      	</c:if>
		      	<c:if test="${am eq 'am3'}">
			      	<small style="cursor:pointer" onclick="searchAm('am1')" class="label label-primary"><i class="fa fa-clock-o"></i>00:00至05:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am2')" class="label label-primary"><i class="fa fa-clock-o"></i>06:00至11:59</small>&nbsp;
			      	<small   class="label label-danger"><i class="fa fa-clock-o"></i> 12:00至17:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am4')" class="label label-primary"><i class="fa fa-clock-o"></i>18:00至23:59</small>
		      	</c:if>
		      	<c:if test="${am eq 'am4'}">
			      	<small style="cursor:pointer" onclick="searchAm('am1')" class="label label-primary"><i class="fa fa-clock-o"></i>00:00至05:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am2')" class="label label-primary"><i class="fa fa-clock-o"></i>06:00至11:59</small>&nbsp;
			      	<small style="cursor:pointer" onclick="searchAm('am3')" class="label label-primary"><i class="fa fa-clock-o"></i>12:00至17:59</small>&nbsp;
			      	<small   class="label label-danger"><i class="fa fa-clock-o"></i> 18:00至23:59</small>
		      	</c:if>
		    </div>
    
    	<div id="http" style="height:400px${chartWidth}"></div>
			      
        </div>
      </div>
  </div>
  <!-- Footer -->
  <jsp:include page="/common/footer.jsp"></jsp:include>
  <script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-2.2.3.min.js"></script>
  <script type="text/javascript">
  	// 路径配置
	require.config({
	    paths: {
	        echarts: '${ctx}/static/echarts-2.2.7/doc/example/www/js'
	    }
	});
	
	// 使用
	require(
	    [
	        'echarts',
	        '${ctx}/static/echarts-2.2.7/doc/example/theme/macarons',
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
            'echarts/chart/k',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/force',
            'echarts/chart/chord',
            'echarts/chart/gauge',
            'echarts/chart/funnel',
            'echarts/chart/eventRiver',
            'echarts/chart/venn',
            'echarts/chart/treemap',
            'echarts/chart/tree',
            'echarts/chart/wordCloud',
            'echarts/chart/heatmap',
	    ],
	    function (ec,theme) {
	       // 基于准备好的dom，初始化echarts图表
	        var cpuChart = ec.init(document.getElementById('http'),theme); 
	        var cpuOption = ${appStateChart};
	         
	        // 为echarts对象加载数据 
	        cpuChart.setOption(cpuOption);
	        
	    }
	);
	
	function search(obj){
	    var ho = '${appInfo.id }';
		window.location.href="${ctx}/sys/app/viewChart?id="+ho+"&date="+obj+"&am=am1";
	}
	
	function searchAm(obj){
	    var ho = '${appInfo.id }';
		window.location.href="${ctx}/sys/app/viewChart?id="+ho+"&date="+$("#dateStr").val()+"&am="+obj;
	}
	
  </script>
</body>
</html>
