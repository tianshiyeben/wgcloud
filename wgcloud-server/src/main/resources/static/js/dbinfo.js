function view(id) {
    window.location.href = "/wgcloud/dbInfo/edit?id=" + id;
}


function add() {
    window.location.href = "/wgcloud/dbInfo/edit";
}

function del(id) {
    if (confirm('你确定要删除吗？同时也将删除数据源对应的数据表')) {
        window.location.href = "/wgcloud/dbInfo/del?id=" + id;
    }
}

function cancel() {
    history.back();
}
