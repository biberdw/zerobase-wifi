<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="/css/table.css">
    <link rel="stylesheet" type="text/css" href="/css/tableDetail.css">
    <link rel="stylesheet" type="text/css" href="/css/bookmarkEdit.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/inc/header/header.jsp"/>



<div>
    <form action="/apps/bookmarks" method="post">
        <table>
            <tr>
                <th>즐겨찾기 이름</th>
                <td><input type="text" name="name" value="${bookmark.name}"></td>
            </tr>
            <tr>
                <th>순서</th>
                <td><input type="text" name="sequenceNum" value="${bookmark.sequenceNum}"></td>
            </tr>
            <tr>
                <td class="btn-td" colspan="17">
                    <c:choose>
                        <c:when test="${mode eq 'edit'}">
                            <button id="submit-btn" type="submit">추가</button>
                        </c:when>
                        <c:when test="${mode eq 'update'}">
                            <a>돌아가기</a>&nbsp;|
                            <button id="submit-btn" type="submit">수정</button>
                            <input type="hidden" name="_method" value="PUT"/>
                            <input type="hidden" name="id" value="${bookmark.id}">
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form>

    <script src="/js/validBookmarkEdit.js"></script>


</div>
</body>
</html>
