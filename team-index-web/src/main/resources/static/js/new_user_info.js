window.newpage = 1;

function initNewUserInfoPage(flag){
    isAdmin = flag;
    $(".tag-inner").empty();
    innerNewUserInfoDataHead();
}

function showNewUserInfoData(list){
    $("#newUserInfoManeger").empty();
    var html = "";
    if (list.length < 1){
        html = "<h2>暂无更多数据</h2>"
    }else {
        for (var i = 0 ; i < list.length ; i++){
            html += "<tr>\n" +
                "<td><a href='#'>"+list[i].userName+"</a></td><td>"+list[i].userClass+"</td>\n" +
                "<td>"+list[i].userStid+"</td><td>"+list[i].userPh+"</td><td>\n" +
                "<div class='layui-btn-group'>\n" +
                "<button type='button' class='layui-btn layui-btn-sm agreeApplication' num="+list[i].userId+">同意申请</button>\n" +
                "<button type='button' class='layui-btn layui-btn-sm refuseApplication' num="+list[i].userId+"><i\n" +
                "class='layui-icon'></i></button></div></td></tr>";
        }
    }
    $("#newUserInfoManeger").append(html);
    bindNewUserInfoButton();
}

function bindNewUserInfoButton() {
    $(".agreeApplication").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认同意该申请吗？</h2>'
            , btn: ['确认', '取消']
            , yes: function (index, layero) {
                agreeApplication(num);
                return false;
            }
        });
    });

    $(".refuseApplication").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认拒绝该申请吗？</h2>'
            , btn: ['确认', '取消']
            , yes: function (index, layero) {
                deleteUserInfoById(num);
                return false;
            }
        });
    });
}

function agreeApplication(num) {
    $.get("/pass/validation/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerNewUserInfoData();
    })
}


function deleteUserInfoById(num) {
    $.get("/delete/user/info/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerNewUserInfoData();
    })
}


function innerNewUserInfoDataHead() {
    //初始化数据页，也就是我们的个人动态
    var html = "<div class='layui-tab'><ul class='layui-tab-title'>\n" +
        "<li class='layui-this'>新用户审核</li></ul>\n" +
        "<div class='layui-tab-content'>\n" +
        "</div></div>\n" +
        "<div class='layui-tab-item layui-show'><div class='layui-form'>\n" +
        "<table class='layui-table'style='text-align:center'>\n" +
        "<colgroup>\n" +
        "<col width='200'><col width='200'>\n" +
        "<col width='150'><col width='150'></colgroup>\n" +
        "<thead style='text-align:center'><tr>\n" +
        "<th style='text-align:center'>用户名称</th>\n" +
        "<th style='text-align:center'>用户班级</th>\n" +
        "<th style='text-align:center'>用户学号</th>\n" +
        "<th style='text-align:center'>用户电话</th>\n" +
        "<th style='text-align:center'>操作</th>\n" +
        "</tr></thead><tbody id='newUserInfoManeger'></tbody><tfoot id='oldUserInfoManegerTfoot'></tfoot></table></div></div></div></div>";

    $(".tag-inner").append(html);

    var PagingHtml = "<tr><td colspan='6' align='center'>\n" +
        "<div id='oldInfoPagination' class='pagination' style='float:right'></div></td></tr>";
    $("#oldUserInfoManegerTfoot").append(PagingHtml);
    innerNewUserInfoData();

}

function innerNewUserInfoData() {

    //待审核的用户
    $.get("/get/all/user/info/vo/page/"+newpage,function (res) {
        showNewUserInfoData( res.data.list );
        showNewUserInfoPagination(res.data);
    })
}

function showNewUserInfoPagination(pageInfo) {
    // 获取分页总记录数
    var totalRecordNo = pageInfo.total;

    // 创建分页
    $("#newInfoPagination").pagination(totalRecordNo, {
        num_edge_entries: 3, 				//边缘页数
        num_display_entries: 4, 			//主体页数
        callback: newUserInfopageSelectCallback,		//点击具体页码时调用这个函数
        items_per_page: pageInfo.pageSize, 	//每页显示1项
        current_page: (pageInfo.pageNum - 1),	//当前页索引，需要将pageInfo.pageNum-1才是合适的值
        prev_text: "上一页",					//上一页按钮的文本
        next_text: "下一页",					//下一页按钮的文本
    });
}


function newUserInfopageSelectCallback(page_index, jq){
    //page_index+1等于要前往的页面的页码
    window.newInfopage = page_index + 1;
    //调用分页函数
    innerUserInfoData();
    return false;
}