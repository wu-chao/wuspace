<%@ page import="com.wuspace.commons.Test" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = request.getParameter("name");
    if (name == null || name.trim().length() <= 0) {
        out.print("用户名不能为空");
        return;
    }

//    String url = request.getParameter("url");
//    response.sendRedirect(url);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <b><%=name%></b> 欢迎登陆
</body>
</html>
