function del(obj,acc){
	if(confirm('你确定要删除'+acc+'吗？')){
		window.location.href = ctx+"/sys/app/del?id="+obj;
	}
};

function searchByPara(){
	var account = $("#httpUrl").val();
	window.location.href = ctx+"/sys/app/list?httpUrl="+escape(escape(account));
}