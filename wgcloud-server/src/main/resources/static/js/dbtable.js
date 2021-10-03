function view(id) {
    window.location.href = "/wgcloud/dbTable/edit?id=" + id;
}


function add() {
    window.location.href = "/wgcloud/dbTable/edit";
}

function del(id) {
    if (confirm('你确定要删除吗？')) {
        window.location.href = "/wgcloud/dbTable/del?id=" + id;
    }
}

function viewChart(id) {
    window.location.href = "/wgcloud/dbTable/viewChart?id=" + id;
}

function cancel() {
    history.back();
}
