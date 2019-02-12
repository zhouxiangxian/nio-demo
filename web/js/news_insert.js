$(function () {
    loadItem();
    $("#myform").validate({
        debug: true ,
        submitHandler: function(form){   // 表单验证成功
            form.submit();   //提交表单
        },
        errorPlacement: function(error, element) {
            $("div[id='" + $(element).attr("id")+"Span']").append(error) ;
        } ,
        invalidHandler: function(form, validator) {	// 未通过验证
        } ,
        success: function(label) {	//
            // label.html("&nbsp;") ;	// 清空已有内容
            // label.html("数据填写正确！").attr("class","text-success");
        } ,
        highlight: function(element, errorClass) {
            $(element).fadeOut(1,function() {
                $(element).fadeIn(1) ;
                // $(element).addClass("has-error") ;
                $("div[id='" + $(element).attr("id")+"Div']").attr("class","form-group has-error") ;
            })
        } ,
        unhighlight: function(element, errorClass) {
            $(element).fadeOut(1,function() {
                $(element).fadeIn(1) ;
                // $(element).addClass("has-error") ;
                $("div[id='" + $(element).attr("id")+"Div']").attr("class","form-group has-success") ;
            })
        } ,
        errorClass: "text-danger" ,
        validClass: "text-success" ,
        wrapper: "" ,
        rules:{
            title:{
                required:true ,
                remote: {
                    url: "pages/news/news_checkTitle.action",     //后台处理程序
                    type: "post",               //数据发送方式
                    dataType: "html",           //接受数据格式
                    data: {                     //要传递的数据
                        title: function() {
                            return $("#title").val();
                        }
                    },
                    dataFilter: function(data, type) {
                        if (data.trim() == "true")
                            return true;
                        else
                            return false;
                    }
                }
            },
            content:{
                required:true
            },
            "item.iid":{
                required:true
            }
        }
    }) ;
})
function loadItem() {
    $.post("pages/item/item_list.action", {}, function (obj) {
        $("#item\\.iid option:gt(0)").remove();
        for (var x = 0; x < obj.allItems.length; x++) {
            $("#item\\.iid").append($("<option value='" + obj.allItems[x].iid + "'>" + obj.allItems[x].title + "</option>"));
        }
    }, "json");
}