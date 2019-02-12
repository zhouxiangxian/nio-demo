<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>BSM开发模块</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <jsp:include page="/pages/header_import.jsp"/>
</head>
<body>

</body>
</html>
