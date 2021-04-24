window.oldpage = 1;
function initOldUserInfoPage(flag){
    isAdmin = flag;
    $(".tag-inner").empty();
    innerOldUserInfoDataHead();
}

function showOldUserInfoData(list){
    $("#oldUserInfoManeger").empty();
    var html = "";
    if (list.length < 1){
        html = "<h2>暂无更多数据</h2>"
    }else {
        for (var i = 0 ; i < list.length ; i++){
            var userPower = list[i].userPower;
            var optionAdmin;
            var optionHtml;
            switch (parseInt(userPower)) {
                case (0):
                    userPower = "普通用户";
                    optionAdmin = "授予管理员";
                    optionHtml = "upAdmin";
                break;
                case (1):
                    userPower = "管理员";
                    optionAdmin = "降为用户";
                    optionHtml = "downUser";
                break;
                case (3):userPower = "顶级管理员";break;
            }
            html += "<tr>\n" +
                "<td><a href='show_user_info.do/"+list[i].userId+"'>"+list[i].userName+"</a></td><td>"+list[i].userClass+"</td><td>"+list[i].userStid+"</td>\n" +
                "<td>"+list[i].userPh+"</td><td>"+userPower+"</td><td><div class='layui-btn-group'>\n" +
                "<button type='button' class='layui-btn layui-btn-sm resetPass' num = "+list[i].userId+">重置密码</button>\n" +
                "<button type='button' num = "+list[i].userId+" class='layui-btn layui-btn-sm "+optionHtml+"'>"+optionAdmin+"</button>\n" +
                "<button type='button' class='layui-btn layui-btn-sm modityUser' num="+list[i].userStid+"><i class='layui-icon'></i></button>\n" +
                "<button type='button'class='layui-btn layui-btn-sm deleteUser' num="+list[i].userId+"><i\n" +
                "class='layui-icon'> </i></button></div></td></tr>";
        }
    }
    $("#oldUserInfoManeger").append(html);

    bindOldUserInfoButton();
}


function bindOldUserInfoButton() {

    $(".modityUser").click(function () {
        var num = $(this).attr("num");
        layer.open({
            type: 2,
            title: ['修改用户', 'font-size:18px;'],
            content: ['/modity/user/info/'+num, 'no'],
            area: ['425px', '440px'],
        });
        return false;
    });

    $(".resetPass").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认重置该用户的密码吗(默认密码12345678)？</h2>'
            , btn: ['确认', '取消']
            , yes: function (index, layero) {
                resetPass(num);
                return false;
            }
        });
    });

    $(".downUser").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认降低成普通用户吗？</h2>'
            , btn: ['确认', '取消']
            , yes: function (index, layero) {
                downUser(num);
                return false;
            }
        });
    });

    $(".upAdmin").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认设置为管理员吗？</h2>'
            , btn: ['确认', '取消']
            , yes: function (index, layero) {
                upAdmin(num);
                return false;
            }
        });
    });

    $(".modityUser").click(function () {
       var num = $(this).attr("num");
       //打开xxx网站进行修改！
       //  window.open("/modity/new/info/"+num);
        //调用弹窗进行修改;
    });

    $(".deleteUser").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认删除该用户吗？</h2>'
            , btn: ['确认删除', '取消']
            , yes: function (index, layero) {
                deleteUserInfoById(num);
                return false;
            }
        });
    });
}

function resetPass(num){
    $.get("/re/set/pass/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerOldUserInfoData();
    })
}



function upAdmin(num){
    $.get("/set/user/info/to/admin/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerOldUserInfoData();
    })
}

function downUser(num){
    $.get("/set/admin/info/to/user/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerOldUserInfoData();
    })
}

function deleteUserInfoById(num) {
    $.get("/delete/user/info/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerOldUserInfoData();
    })
}


function innerOldUserInfoDataHead() {
    //初始化数据页，也就是我们的个人动态
    var html = "<div class='layui-tab'><ul class='layui-tab-title'>\n" +
        "<li class='layui-this'>用户管理</li></ul>\n" +
        "<div class='layui-tab-content'><div class='layui-tab-itemlayui-show'>\n" +
        "<div class='layui-form'><table class='layui-table'style='text-align:center'>\n" +
        "<colgroup>\n" +
        "<col width='125'><col width='160'><col width='100'><col width='100'><col width='150'>\n" +
        "<col></colgroup><thead style='text-align:center'>\n" +
        "<tr>\n" +
        "<th style='text-align:center'>用户名称</th>\n" +
        "<th style='text-align:center'>用户班级</th>\n" +
        "<th style='text-align:center'>用户学号</th>\n" +
        "<th style='text-align:center'>用户电话</th>\n" +
        "<th style='text-align:center'>是否管理员</th>\n" +
        "<th style='text-align:center'>操作</th></tr></thead><tbody id='oldUserInfoManeger'></tbody><tfoot id='oldUserInfoManegerTfoot'></tfoot></tbody>\n" +
        "</table>\n" +
        "</div></div>\n";
    $(".tag-inner").append(html);
    var PagingHtml = "<tr><td colspan='6' align='center'>\n" +
        "<div id='oldInfoPagination' class='pagination' style='float:right'></div></td></tr>";
    $("#oldUserInfoManegerTfoot").append(PagingHtml);
    innerOldUserInfoData();

}

function innerOldUserInfoData() {
    //正常使用的用户
    $.get("/get/user/info/vo/prohibit/"+oldpage,function (res) {
        showOldUserInfoData( res.data.list );
        showOldUserInfoPagination(res.data);
    })
}

function showOldUserInfoPagination(pageInfo) {
    // 获取分页总记录数
    var totalRecordNo = pageInfo.total;

    // 创建分页
    $("#oldInfoPagination").pagination(totalRecordNo, {
        num_edge_entries: 3, 				//边缘页数
        num_display_entries: 4, 			//主体页数
        callback: oldUserInfopageSelectCallback,		//点击具体页码时调用这个函数
        items_per_page: pageInfo.pageSize, 	//每页显示1项
        current_page: (pageInfo.pageNum - 1),	//当前页索引，需要将pageInfo.pageNum-1才是合适的值
        prev_text: "上一页",					//上一页按钮的文本
        next_text: "下一页",					//下一页按钮的文本
    });
}

function oldUserInfopageSelectCallback(page_index, jq) {
    //page_index+1等于要前往的页面的页码
    window.oldpage = page_index + 1;
    //调用分页函数
    innerOldUserInfoData();
    return false;
}