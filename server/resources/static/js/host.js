function searchByPara(){
	var account = $("#account").val();
	window.location.href = "/wgcloud/log/list?account="+escape(escape(account));
}

function view(id) {
	window.location.href = "/wgcloud/dash/detail?id="+id;
}
function viewChart(id) {
	window.location.href = "/wgcloud/dash/chart?id="+id;
}

function del(id) {
	if(confirm('你确定要删除吗？')) {
		window.location.href = "/wgcloud/dash/del?id=" + id;
	}
}


function viewDate(id,dates){
	window.location.href = "/wgcloud/dash/chart?id="+id+"&date="+dates;
}

function cancel(){
	history.back();
}
