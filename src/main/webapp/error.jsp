<%--
  Created by IntelliJ IDEA.
  User: DONGWOO
  Date: 2023/04/28
  Time: 2:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
에러페이지 입니다 <%= request.getParameter("exceptionMessage")%>
</body>
</html>
