function searchByPara(){
	var account = $("#account").val();
	window.location.href = ctx+"/sys/log/list?account="+escape(escape(account));
}