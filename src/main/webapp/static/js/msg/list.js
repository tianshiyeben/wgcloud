
function searchByPara(){
	var account = $("#account").val();
	var acceptInfo = $("#acceptInfo").val();
	window.location.href = ctx+"/sys/msg/list?account="+escape(escape(account))+"&acceptInfo="+acceptInfo;
}