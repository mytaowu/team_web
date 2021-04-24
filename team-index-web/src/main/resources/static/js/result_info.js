window.resultInfopage = 1;
window.userInfo = {};
//存放分类的信息，进行信息的匹配
window.resultClassfication = [];
window.isAdmin = false;

function initResultInfoPage(flag){
    isAdmin = flag;
    $(".tag-inner").empty();
    innerResultInfoDataHead();
}

function showResultInfoData(list){
    $(".result-info-body").empty();
    var html = "";
    if (list.length < 1){
        html = "<h2>暂无更多数据</h2>"
    }else {
        for (var i = 0 ; i < list.length ; i++){
            var resultTypeId = list[i].typeId;
            var resultTypeName = "暂无分类";
            $.each(resultClassfication,function (index,value) {
                if (value.typeId == resultTypeId){
                    resultTypeName = value.typeName;
                    // 等价于break;
                    return false;
                }
            })
            html += "<tr><td><a target='_blank' href='"+list[i].resultLink+"'>"+list[i].resultName+"</a></td><td>"+list[i].resultTime+"</td>\n" +
                "<td>"+resultTypeName+"</td><td>"+list[i].userInfo.userName+"</td><td>\n" +
                "<div class='layui-btn-group'>\n" +
                "<button type='button' class='layui-btn layui-btn-sm modity-result-info' num = "+list[i].resultId+"><i class='layui-icon'></i></button>\n" +
                "<button type='button' class='layui-btn layui-btn-sm delete-result-info' num = "+list[i].resultId+"><i class='layui-icon'></i></button></div></td></tr>";
        }
    }
    $(".result-info-body").append(html);

    bindResultInfoButton();
}

function bindResultInfoButton() {
    $(".modity-result-info").click(function () {
       var num = $(this).attr("num");
        layer.open({
            type: 2,
            title: ['修改成果', 'font-size:18px;'],
            content: ['/modity/result/'+num, 'no'],
            area: ['420px', '630px'],
        });
    });
    $(".delete-result-info").click(function () {
        var num = $(this).attr("num");
        layer.open({
            content: '<h2 style="text-align: center; font-family: 楷体">确认删除成果吗？</h2>'
            , btn: ['确认删除', '取消']
            , yes: function (index, layero) {
                //按钮【按钮一】的回调，调用函数进行删除
                deleteResultById(num);
                return false;
            }
        });
    });
}

function deleteResultById(num) {
    $.get("/delete/result/info/"+num, function (response) {
        layer.msg(response.message);
        //重新加载数据
        innerResultInfoData();
    })
}


function innerResultInfoDataHead() {
    //初始化数据页，也就是我们的个人动态
    var html = "<div class='layui-form'><table class='layui-table' style='text-align:center'><colgroup><col>\n" +
        "<col width='150'><col width='150'><col width='150'><col width='150'></colgroup><thead style='text-align:center'><tr>\n" +
        "<th style='text-align:center'>成果名称</th><th style='text-align:center'>发表时间</th>\n" +
        "<th style='text-align:center'>成果分类</th><th style='text-align:center'>发布人</th>\n" +
        "<th style='text-align:center'>操作</th></tr></thead><tbody class=result-info-body></tbody><tfoot class='result-info-dfoot'></tfoot></table></div>";

    $(".tag-inner").append(html);

    var PagingHtml = "<tr><td colspan='5' align='center'>\n" +
        "<div id='resultInfoPagination' class='pagination' style='float:right'></div></td></tr>";
    $(".result-info-dfoot").append(PagingHtml);

    innerResultInfoData();

}

function innerResultInfoData() {

    if (isAdmin){
        // 我们这里暂时写死，没得办法
        $.get("/get/resule/info/vo/"+newInfopage+"/all",function (resItem) {
            $.get("/show/all/resule/type",function (res) {
                resultClassfication = res.data;
                showResultInfoData( resItem.data.list );
                showResultInfoPagination(resItem.data);
            })
        })
    }else {
        // 我们这里暂时写死，没得办法
        $.get("/get/resule/info/vo/"+newInfopage+"/"+window.userStid,function (resItem) {
            $.get("/show/all/resule/type",function (res) {
                resultClassfication = res.data;
                showResultInfoData( resItem.data.list );
                showResultInfoPagination(resItem.data);
            })
        })
    }
}

function showResultInfoPagination(pageInfo) {
    // 获取分页总记录数
    var totalRecordNo = pageInfo.total;

    // 创建分页
    $("#resultInfoPagination").pagination(totalRecordNo, {
        num_edge_entries: 3, 				//边缘页数
        num_display_entries: 4, 			//主体页数
        callback: resultInfopageSelectCallback,		//点击具体页码时调用这个函数
        items_per_page: pageInfo.pageSize, 	//每页显示1项
        current_page: (pageInfo.pageNum - 1),	//当前页索引，需要将pageInfo.pageNum-1才是合适的值
        prev_text: "上一页",					//上一页按钮的文本
        next_text: "下一页",					//下一页按钮的文本
    });
}

function resultInfopageSelectCallback(page_index, jq) {
    //page_index+1等于要前往的页面的页码
    window.resultInfopage = page_index + 1;
    //调用分页函数
    innerResultInfoData()
    return false;
}