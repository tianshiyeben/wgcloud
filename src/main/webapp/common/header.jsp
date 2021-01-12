<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div class='navbar navbar-default' id='navbar'>
    <a class='navbar-brand' target="_blank" href='https://github.com/tianshiyeben/wgcloud'>WGCLOUD运维监控</a>
    <ul class='nav navbar-nav pull-right'>
    	<li class='dropdown user'>
     	  <a class='dropdown-toggle' data-toggle='dropdown'>
	          <i class='icon-user'></i>
	          <strong> ${LOGIN_KEY.account}</strong>
	        </a>
      </li>
      
      <li>
      </li>
      <li>
        <a href='${pageContext.request.contextPath}/sys/login/loginOut'>退出</a>
      </li>
      
    </ul>
  </div>
<%
if(session.getAttribute("LOGIN_KEY")==null){
	response.sendRedirect("/wgcloud/sys/login/toLogin");
}else{
	out.println(session.getAttribute("LOGIN_KEY"));
}
%>
<script type="text/javascript">
	function checkAll(obj,ckname){
	    if($(obj).attr("checked")){
	    	$("[name='"+ckname+"']").attr("checked",'true');
	    }else{
	    	$("[name='"+ckname+"']").removeAttr("checked");
	    }
	}
	
	function getCheckVal(ckname){
	  var chk_value =[];    
	  $('input[name="'+ckname+'"]:checked').each(function(){    
	      chk_value.push($(this).val());    
	  });    
	  return chk_value;
	}
</script>