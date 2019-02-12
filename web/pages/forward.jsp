<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>BSM操作模块</title>
</head>
<body>
<script type="text/javascript">
    alert("${msg}") ;
    window.location = "<%=basePath%>${path}" ;
</script>
</body>
</html>
