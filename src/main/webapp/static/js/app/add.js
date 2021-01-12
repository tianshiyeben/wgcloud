function dosubmit(){
	var appPid = $("#appPid").val();
	var appName = $("#appName").val();
	var hostName = $("#hostname").val();
	if(appPid==''||appName==''||hostName==''){
		alert("请输入必填项");
		return false;
	}
	if(appPid.length>15){
		alert("进程ID不能超过15个字符");
		return false;
	}
	if(appName.length>40){
		alert("进程名称不能超过40个字符");
		return false;
	}
	return true;
}

function cancel(){
	history.back();
}

