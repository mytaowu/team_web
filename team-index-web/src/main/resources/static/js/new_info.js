window.newInfopage = 1;
window.userInfo = {};
window.isAdmin = false;


function initNewInfoPage(flag){
    isAdmin = flag;
    $(".tag-inner").empty();
    innerNewInfoDataHead();
}

function showNewInfoData(list){
    $(".new-info-body").empty();
    var html = "";
    if (list.length < 1){
        html = "<h2>暂无更多数据</h2>"
    }else {
        for (var i = 0 ; i < list.length ; i++){
            html += "<tr><td><a target='_blank' href='/show_new_info.do/"+list[i].newId+"'>"+list[i].newTitle+"</a></td><td>"+list[i].newTimes+"</td><td>"+list[i].newTime+"</td>\n" +
                "<td>"+list[i].userInfo.userName+"</td><td><div class='layui-btn-group'>\n" +
                "<button type='button' num = "+list[i].newId+" class='layui-btn layui-btn-sm modity-new-info'><i class='layui-icon'></i></button>\n" +
                "<button type='button' num = "+list[i].newId+" class='layui-btn layui-btn-sm delete-new-info'><i class='layui-icon'></i></button></div></td></tr>";
        }
    }
    $(".new-info-body").append(html);

    bindNewInfoButton();
}

function bindNewInfoButton() {
    $(".modity-new-info").click(function () {
       var num = $(this).attr("num");
       //打开xxx网站进行修改！
        window.open("/modity/new/info/"+num);
    });
    $(".delete-new-info").click(function () {
        var num = $(this).attr("num");
        console.log(num);
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认删除新闻吗？</h2>'
            , btn: ['确认删除', '取消']
            , yes: function (index, layero) {
                //按钮【按钮一】的回调，调用函数进行删除
                deleteNewInfoById(num);
                return false;
            }
        });
    });
}

function deleteNewInfoById(num) {
    $.get("/delete/new/info/by/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerNewInfoData();
    })
}


function innerNewInfoDataHead() {
    //初始化数据页，也就是我们的个人动态
    var html = "<div class='layui-form'>\n" +
        "<table class='layui-table' style='text-align:center'><colgroup><col><col width='150'>\n" +
        "<col width='150'><col width='150'><col width='150'></colgroup>\n" +
        "<thead style='text-align:center'><tr>\n" +
        "<th style='text-align:center'>新闻名称</th>\n" +
        "<th style='text-align:center'>阅读量</th>\n" +
        "<th style='text-align:center'>发表时间</th>\n" +
        "<th style='text-align:center'>发布人</th>\n" +
        "<th style='text-align:center'>操作</th></tr>\n" +
        "</thead><tbody class=new-info-body></tbody><tfoot class='new-info-dfoot'></tfoot></table></div>";

    $(".tag-inner").append(html);

    var PagingHtml = "<tr><td colspan='5' align='center'>\n" +
        "<div id='newInfoPagination' class='pagination' style='float:right'></div></td></tr>";
    $(".new-info-dfoot").append(PagingHtml);

    innerNewInfoData();

}

function innerNewInfoData() {
    // 我们这里暂时写死，没得办法
    if (isAdmin){
        $.get("/get/new/info/vo/"+newInfopage+"/all",function (res) {
            showNewInfoData( res.data.list );
            showNewInfoPagination(res.data);
        })
    }else {
        $.get("/get/new/info/vo/"+newInfopage+"/"+window.userStid,function (res) {
            showNewInfoData( res.data.list );
            showNewInfoPagination(res.data);
        })
    }

}

function showNewInfoPagination(pageInfo) {
    // 获取分页总记录数
    var totalRecordNo = pageInfo.total;
    console.log(totalRecordNo);

    // 创建分页
    $("#newInfoPagination").pagination(totalRecordNo, {
        num_edge_entries: 3, 				//边缘页数
        num_display_entries: 4, 			//主体页数
        callback: newInfopageSelectCallback,		//点击具体页码时调用这个函数
        items_per_page: pageInfo.pageSize, 	//每页显示1项
        current_page: (pageInfo.pageNum - 1),	//当前页索引，需要将pageInfo.pageNum-1才是合适的值
        prev_text: "上一页",					//上一页按钮的文本
        next_text: "下一页",					//下一页按钮的文本
    });
}

function newInfopageSelectCallback(page_index, jq) {
    //page_index+1等于要前往的页面的页码
    window.newInfopage = page_index + 1;
    //调用分页函数
    innerNewInfoData();
    return false;
}