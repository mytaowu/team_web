window.dypage = 1;
window.userInfo = {};
window.isAdmin = false;


function initDyNamePage(flag){
    isAdmin = flag;
    $(".tag-inner").empty();

    layui.use('layer', function () {
        layer = layui.layer;
    });

    layui.use('element', function () {
        var $ = layui.jquery
            , element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        $('.site-demo-active').on('click', function () {
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });
    });

    var childrenP = $(".inner-text-article-inner").children("p");
    childrenP.each(function (index, value) {
        var imgs = $(value).children("img");

        if (imgs[0] != undefined) {
            $(value).css({"text-align": "center"});
        }
    })

    $(".ui-head-img").mouseenter(function () {
        $(".user-head-img").css("-webkit-filter", "brightness(50%)");
        $(".user-head-img").css("filter", "brightness(50%)");
        $(".e-hover-code").show();
    });

    $(".ui-head-img").mouseleave(function () {
        $(".user-head-img").css("-webkit-filter", "none");
        $(".user-head-img").css("filter", "none");
        $(".e-hover-code").hide();
    });

    $(".tab-group > ul > li").click(function () {
        $(this).addClass("active-tab")
        var siblingLi = $(this).siblings("li");
        $.each(siblingLi, function (index, value) {
            $(value).removeClass("active-tab");
        })
    });

    innerDyNameDataHead();
}

function showDyNameData(list){
    $(".dyname-body").empty();
    var html = "";
    if (list.length < 1){
        html = "<h2>暂无更多数据</h2>"
    }else {
        for (var i = 0 ; i < list.length ; i++){
            html += "<tr>\n" +
                "<td><a target='_blank' href=/show_dy_info.do/"+list[i].dyNamId+">"+list[i].dyNamTitle+"</a></td>\n" +
                "<td>"+list[i].userInfo.userName+"</td>\n" +
                "<td>\n" +
                "<div class='layui-btn-group'>\n" +
                "<button num = "+list[i].dyNamId+" type='button' class='layui-btn layui-btn-sm modity-dyname'><i class='layui-icon'></i></button>\n" +
                "<button num = "+list[i].dyNamId+" type='button' class='layui-btn layui-btn-sm delete-dyname'><i class='layui-icon '></i></button>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>";
        }
    }
    $(".dyname-body").append(html);

    binddyNameButton();
}

function binddyNameButton() {
    $(".modity-dyname").click(function () {
       var num = $(this).attr("num");
       window.open("/modity/dyname/"+num);
    });
    $(".delete-dyname").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认删除动态吗？</h2>'
            , btn: ['确认删除', '取消']
            , yes: function (index, layero) {
                //按钮【按钮一】的回调，调用函数进行删除
                deleteDyNameById(num);
                return false;
            }
        });
    });
}

function deleteDyNameById(num) {
    $.get("/delete/dy/nam/by/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerDyNameData();
    })
}


function innerDyNameDataHead() {
    //初始化数据页，也就是我们的个人动态
    var html = "<div class=\"layui-form\"><table class=\"layui-table\" style=\"text-align:center\"><colgroup><col><col width=\"150\"><col width=\"200\"></colgroup><thead style=\"text-align:center\"><tr><th style=\"text-align:center\">动态名称</th>\n" +
        "<th style=\"text-align:center\">发表人</th><th style=\"text-align:center\">操作</th></tr></thead><tbody class=dyname-body>\n" +
        "</tbody><tfoot class='dy-dfoot'></tfoot></table></div>\n";

    $(".tag-inner").append(html);

    var PagingHtml = "<tr><td colspan='3' align='center'>\n" +
        "<div id='Pagination' class='pagination' style='float:right'></div></td></tr>";
    $(".dy-dfoot").append(PagingHtml);

    innerDyNameData();

}

function innerDyNameData() {
    if (isAdmin){
        $.get("/get/dy/nam/info/vo/"+dypage+"/"+"all",function (res) {
            showDyNameData( res.data.list );
            showDyNamePagination(res.data);
        })
    }else {
        $.get("/get/dy/nam/info/vo/"+dypage+"/"+window.userStid,function (res) {
            showDyNameData( res.data.list );
            showDyNamePagination(res.data);
        })
    }

}

function showDyNamePagination(pageInfo) {
    // 获取分页总记录数
    var totalRecordNo = pageInfo.total;

    // 创建分页
    $(".pagination").pagination(totalRecordNo, {
        num_edge_entries: 3, 				//边缘页数
        num_display_entries: 4, 			//主体页数
        callback: dypageSelectCallback,		//点击具体页码时调用这个函数
        items_per_page: pageInfo.pageSize, 	//每页显示1项
        current_page: (pageInfo.pageNum - 1),	//当前页索引，需要将pageInfo.pageNum-1才是合适的值
        prev_text: "上一页",					//上一页按钮的文本
        next_text: "下一页",					//下一页按钮的文本
    });
}

function dypageSelectCallback(page_index, jq) {
    //page_index+1等于要前往的页面的页码
    window.dypage = page_index + 1;
    //调用分页函数
    innerDyNameData();
    return false;
}