window.studyInfopage = 1;
window.userInfo = {};
//存放分类的信息，进行信息的匹配
window.studyClassfication = [];
window.isAdmin = false;

function initStudyInfoPage(flag){
    isAdmin = flag;
    $(".tag-inner").empty();
    innerStudyInfoDataHead();
}

function showStudyInfoData(list){
    $(".study-info-body").empty();
    var html = "";
    if (list.length < 1){
        html = "<h2>暂无更多数据</h2>"
    }else {
        for (var i = 0 ; i < list.length ; i++){
            var studyTypeId = list[i].typeId;
            var studyTypeName = "暂无分类";
            $.each(resultClassfication,function (index,value) {
                if (value.typeId == studyTypeId){
                    studyTypeName = value.typeName;
                    // 等价于break;
                    return false;
                }
            })
            html += "<tr><td><a target='_blank' href='"+list[i].studyLink+"'>"+list[i].studyName+"</a></td><td>"+list[i].studyTime+"</td>\n" +
                "<td>"+studyTypeName+"</td><td>"+list[i].userInfo.userName+"</td><td>\n" +
                "<div class='layui-btn-group'>\n" +
                "<button type='button' class='layui-btn layui-btn-sm modity-study-info' num = "+list[i].studyId+"><i class='layui-icon'></i></button>\n" +
                "<button type='button' class='layui-btn layui-btn-sm delete-study-info' num = "+list[i].studyId+"><i class='layui-icon'></i></button></div></td></tr>";
        }
    }
    $(".study-info-body").append(html);

    bindStudyInfoButton();
}

function bindStudyInfoButton() {
    $(".modity-study-info").click(function () {
       var num = $(this).attr("num");
        layer.open({
            type: 2,
            title: ['修改资源', 'font-size:18px;'],
            content: ['/modity/study/'+num, 'no'],
            area: ['420px', '630px'],
        });
    });
    $(".delete-study-info").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认删除资源吗？</h2>'
            , btn: ['确认删除', '取消']
            , yes: function (index, layero) {
                //按钮【按钮一】的回调，调用函数进行删除
                deleteStudyById(num);
                return false;
            }
        });
    });
}

function deleteStudyById(num) {
    $.get("/delete/study/info/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerStudyInfoData();
    })
}


function innerStudyInfoDataHead() {
    //初始化数据页，也就是我们的个人动态
    var html = "<div class='layui-form'><table class='layui-table' style='text-align:center'><colgroup><col>\n" +
        "<col width='150'><col width='150'><col width='150'><col width='150'></colgroup><thead style='text-align:center'><tr>\n" +
        "<th style='text-align:center'>资源名称</th><th style='text-align:center'>发表时间</th>\n" +
        "<th style='text-align:center'>资源分类</th><th style='text-align:center'>发布人</th>\n" +
        "<th style='text-align:center'>操作</th></tr></thead><tbody class=study-info-body></tbody><tfoot class='study-info-dfoot'></tfoot></table></div>";

    $(".tag-inner").append(html);

    var PagingHtml = "<tr><td colspan='5' align='center'>\n" +
        "<div id='studyInfoPagination' class='pagination' style='float:right'></div></td></tr>";
    $(".study-info-dfoot").append(PagingHtml);

    innerStudyInfoData();

}

function innerStudyInfoData() {

    if (isAdmin){
        // 我们这里暂时写死，没得办法
        $.get("/get/study/info/vo/"+studyInfopage+"/all",function (resItem) {
            $.get("/show/all/study/type",function (res) {
                resultClassfication = res.data;
                showStudyInfoData( resItem.data.list );
                showStudyInfoPagination(resItem.data);
            })
        })
    }else {
        // 我们这里暂时写死，没得办法
        $.get("/get/study/info/vo/"+studyInfopage+"/"+window.userStid,function (resItem) {
            $.get("/show/all/study/type",function (res) {
                resultClassfication = res.data;
                showStudyInfoData( resItem.data.list );
                showStudyInfoPagination(resItem.data);
            })
        })
    }
}

function showStudyInfoPagination(pageInfo) {
    // 获取分页总记录数
    var totalRecordNo = pageInfo.total;

    // 创建分页
    $("#studyInfoPagination").pagination(totalRecordNo, {
        num_edge_entries: 3, 				//边缘页数
        num_display_entries: 4, 			//主体页数
        callback: studyInfopageSelectCallback,		//点击具体页码时调用这个函数
        items_per_page: pageInfo.pageSize, 	//每页显示1项
        current_page: (pageInfo.pageNum - 1),	//当前页索引，需要将pageInfo.pageNum-1才是合适的值
        prev_text: "上一页",					//上一页按钮的文本
        next_text: "下一页",					//下一页按钮的文本
    });
}

function studyInfopageSelectCallback(page_index, jq) {
    //page_index+1等于要前往的页面的页码
    window.studyInfopage = page_index + 1;
    //调用分页函数
    innerStudyInfoData();
    return false;
}