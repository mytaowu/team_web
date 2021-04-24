layui.use('layer', function () {
    var layer = layui.layer;
});
$(function () {

    $("#login-btn").click(function () {
        layer.open({
            type: 2,
            title: ['用户登录', 'font-size:18px;'],
            content: ['/loginpage', 'no'],
            area: ['420px', '400px'],
        });
        return false;
    });

    $("#join").click(function () {
        layer.close(layer.index);
        layer.open({
            type: 2,
            title: ['用户注册', 'font-size:18px;'],
            content: ['/registerpage', 'no'],
            area: ['427px', '520px'],
        });
        return false;
    });

    $("#register-btn").click(function () {
        layer.open({
            type: 2,
            title: ['用户注册', 'font-size:18px;'],
            content: ['/registerpage', 'no'],
            area: ['427px', '520px'],
        });
        return false;
    });
    

    $(".headLogin-a").toggle(
        function () {
            $(".float-ul").show();
        },
        function () {
            $(".float-ul").hide();
        }
    );

    $(".add-btn").click(function () {
        var num = parseInt($(this).attr("num"));
        switch (num) {
            case 1:
                window.open("/add_dyname");break;
            case 2:
                window.open("/add_newinfo");break;
            case 3:
                layer.open({
                    type: 2,
                    title: ['添加成果', 'font-size:18px;'],
                    content: ['/add_resultPage', 'no'],
                    area: ['420px', '630px'],
                });
                break;
            case 4:
                layer.open({
                    type: 2,
                    title: ['添加资源', 'font-size:18px;'],
                    content: ['/add_studyPage', 'no'],
                    area: ['420px', '630px'],
                });
                break;
        }
        return false;
    });
});

function showloading(t) {
    if (t) {//如果是true则显示loading
        // console.log(t);
        loading = layer.load(1, {
            shade: [0.3, '#000'], //0.1透明度的白色背景
            content:'<p style="position: relative;left: -50px;">加载中,请耐心等待...</p>',
            success: function (layerContentStyle) {
                layerContentStyle.find('.layui-layer-content').css({
                    'padding-top': '50px',
                    'text-align': 'left',
                    'line-height':'30px',
                    'color':'#fff',
                    'width': '300px'
                })
            }
        });
    }else{//如果是false则关闭loading
        // console.log("关闭loading层:" + t);
        layer.closeAll('loading');
    }
}

function progressStart() {
    $(".progress").show("show");
}

function progressEnd() {
    $(".progress").hide("show");
}