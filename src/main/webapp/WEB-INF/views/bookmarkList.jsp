<%--
  Created by IntelliJ IDEA.
  User: DONGWOO
  Date: 2023/04/20
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" type="text/css" href="/css/table.css">
  <link rel="stylesheet" type="text/css" href="/css/bookmarkList.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div>
  <h1>와이파이 정보 구하기</h1>
</div>
<div>
  <a href="/">홈</a>&nbsp;&nbsp;|&nbsp;
  <a href="/apps/histories">위치 히스토리 목록</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a id="fetchWifi" href="#">Open API 와이파이 정보 가져오기</a>&nbsp;&nbsp;|&nbsp;
  <a href="#">즐겨찾기 보기</a>&nbsp;&nbsp;|&nbsp;
  <a href="/apps/bookmarks">즐겨찾기 그룹 관리</a>
</div>

<form method="get" action="/apps/bookmarks">
  <div>
    <input type="hidden" name="mode" value="edit">
    <button type="submit">즐겨찾기 그룹 추가</button>
  </div>
</form>

<div>
  <table>
    <tr>
      <th>ID</th>
      <th>즐겨찾기 이름</th>
      <th>순서</th>
      <th>등록일자</th>
      <th>수정일자</th>
      <th>비고</th>
    </tr>
    <!-- 여기서 if문 및 반복문 -->
    <c:choose>
      <c:when test="${empty bookmarks}">
        <tr>
          <td colspan="6">정보가 존재하지 않습니다.</td>
        </tr>
      </c:when>
      <c:otherwise>
        <c:forEach var="bookmark" items="${bookmarks}">
          <tr>
            <td>${bookmark.id}</td>
            <td>${bookmark.name}</td>
            <td>${bookmark.sequenceNum}</td>
            <td>${bookmark.created}</td>
            <td>${bookmark.modified}</td>

            <td>
              <form class="delete" action="/apps/bookmarks" method="post">
                <a href="/apps/bookmarks?id=${bookmark.id}&mode=update">수정</a>
                <a class="delete-btn" href="#">삭제</a>
                <input type="hidden" name="id" value="${bookmark.id}"/>
                <input type="hidden" name="_method" value="DELETE"/>
              </form>
            </td>
          </tr>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </table>
  <script src="/js/submitDelete.js"></script>
</div>
</body>
</html>
