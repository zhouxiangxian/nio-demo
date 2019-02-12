<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String insertUrl = basePath + "pages/news/news_insert.action" ;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>BSM开发模块</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <jsp:include page="/pages/header_import.jsp"/>
    <script type="text/javascript" src="js/news_insert.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <strong>增加新闻</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" method="post" action="<%=insertUrl%>" id="myform">
                        <fieldset>
                            <legend><label>新闻发布</label></legend>
                            <div class="form-group" id="titleDiv">
                                <label class="col-md-2 control-label" for="title">标题：</label>
                                <div class="col-md-6">
                                    <input type="text" id="title" name="title" class="form-control" placeholder="请输入新闻标题">
                                </div>
                                <div class="col-md-4" id="titleSpan"></div>
                            </div>
                            <div class="form-group" id="item.iidDiv">
                                <label class="col-md-2 control-label" for="item.iid">类型：</label>
                                <div class="col-md-6">
                                    <select id="item.iid" name="item.iid" class="form-control">
                                        <option value="">====== 请新闻类型 ======</option>
                                    </select>
                                </div>
                                <div class="col-md-4" id="item.iidSpan"></div>
                            </div>
                            <div class="form-group" id="contentDiv">
                                <label class="col-md-2 control-label" for="content">内容：</label>
                                <div class="col-md-6">
                                    <textarea id="content" name="content" class="form-control" placeholder="请输入新闻内容"></textarea>
                                </div>
                                <div class="col-md-4" id="contentSpan"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-5 col-md-offset-3">
                                    <button type="submit" class="btn btn-primary btn-sm">提交</button>
                                    <button type="reset" class="btn btn-warning btn-sm">重置</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="panel-footer">
                    www.mldn.cn
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
