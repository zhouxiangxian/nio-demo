$(function() {
    loadData() ;
    $("#myform").validate({
        debug: true ,
        submitHandler: function(form){   // 表单验证成功
            var nid = $("#snid").text() ;
            var title = $("#title").val() ;
            var content = $("#content").val() ;
            var item = $("#item").val() ;
            $.post("pages/news/news_update.action",{"nid":nid,"title":title,"content":content,"item.iid":item},function(obj) {
                if (obj.trim() == "true") {
                    $("#alertDiv").attr("class","alert alert-success") ;
                    $("#alertText").text("新闻数据修改成功！") ;
                    $("#title-" + nid).text(title) ;
                    $("#content-" + nid).text(content) ;
                } else {
                    $("#alertDiv").attr("class","alert alert-danger") ;
                    $("#alertText").text("新闻数据修改失败！") ;
                }
                $("#newsInfo").modal("hide") ;
                $("#alertDiv").fadeIn(3000,function(){
                    $("#alertDiv").fadeOut(2000) ;
                }) ;
            },"text") ;
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
                    url: "pages/news/news_checkTitleForUpdate.action",     //后台处理程序
                    type: "post",               //数据发送方式
                    dataType: "html",           //接受数据格式
                    data: {                     //要传递的数据
                        title: function() {
                            return $("#title").val();
                        } ,
                        nid : function() {
                            return $("#snid").text() ;
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
function loadData() {	
	
	
	function checkIP2New(sIPAddress)
	{
		//IP过滤的表达式 1.0.0.1  ~  255.255.255.255
    	var ipPatrn=/^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([0-9]|([0-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/;
    	//域名匹配的表达式
    	//var urlPatrn=/^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$/;
    	var urlPatrn =/^([a-zA-Z]|[0-9])[-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$/;
    	var dataPatrn=/^\d+\.\d+\.\d+\.\d+$/;
    	if(""==sIPAddress){
    		return false;
    	}
    	if(!(urlPatrn.exec(sIPAddress) || ipPatrn.exec(sIPAddress))){
    		return false;
    	}
    	if(sIPAddress.match(dataPatrn)!=null){
    		var tempArray=sIPAddress.split("\.");
    		for(var x=0;x<tempArray.length;x++){
    			if(tempArray[x]>250||tempArray[x]<0){
    				return false;
    			}
    		}
    	}
    	return true;
	}
	// 读取服务器端返回的数据
    $.post("pages/news/news_list.action",{"cp":jsCommonCp,"ls":jsCommonLs},function(obj){
        $("#newsTable tr:gt(0)").remove() ;
        // 填充表格数据
        for (var x = 0 ; x < obj.allNewses.length ; x ++) {
            addRow(obj.allNewses[x].nid,obj.allNewses[x].title,obj.allNewses[x].pubdate,obj.allNewses[x].content,obj.allNewses[x].item.iid) ;
        }
        createSplitBar(obj.allRecorders) ;	// 创建分页控制项
        var id="123";
    	checkIP2New(id);
        setSelectAll($("#selall"),$("input[id='nid']")) ;  // 页面加载完成之后进行全选设置
        setDelete($("#deleteBtn"),$("input[id='nid']"),"pages/news/news_delete.action") ;
    },"json") ;
}
function addRow(nid,title,pubdate,content,iid) {
    var str = "<tr><td class='text-center'><input type='checkbox' name='nid' id='nid' value='"+nid+"'></td>" +
            "<td id='title-"+nid+"' class='text-center'>"+title+"</td>" +
            "<td id='pubdate-"+nid+"' class='text-center'>"+pubdate+"</td>" +
            "<td id='content-"+nid+"' class='text-center'>"+content+"</td>" +
            "<td id='btn-"+nid+"' class='text-center'><button class='btn btn-info btn-xs' data-toggle='modal' data-target='#newsInfo' id='" + nid + "-" + iid + "'>修改</button></td>" +
        "</tr>" ;
    $("#newsTable").append($(str)) ;
    $("#" + nid + "-" + iid).on("click",function() {
        $("#snid").text(nid) ;
        $("#title").val(title) ;
        $("#content").val(content) ;
        loadItem(iid) ; // 读取下拉菜单内容
    }) ;
}

function loadItem(iid) {
    $.post("pages/item/item_list.action", {}, function (obj) {
        $("#item option:gt(0)").remove();
        for (var x = 0; x < obj.allItems.length; x++) {
            if (obj.allItems[x].iid == iid) {
                $("#item").append($("<option value='" + obj.allItems[x].iid + "' selected>" + obj.allItems[x].title + "</option>"));
            } else {
                $("#item").append($("<option value='" + obj.allItems[x].iid + "'>" + obj.allItems[x].title + "</option>"));
            }
        }
    }, "json");
}