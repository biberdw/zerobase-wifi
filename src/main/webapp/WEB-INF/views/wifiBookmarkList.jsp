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
</head>
<body>
  <div>
      <h1>와이파이 정보 구하기</h1>
  </div>
  <div>
      <a href="/">홈</a>&nbsp;&nbsp;|&nbsp;
      <a href="/apps/histories">위치 히스토리 목록</a>&nbsp;&nbsp;|&nbsp;&nbsp;
      <a id="fetchWifi" href="#">Open API 와이파이 정보 가져오기</a>&nbsp;&nbsp;|&nbsp;
      <a href="/apps/bookmarks/wifi">즐겨찾기 보기</a>&nbsp;&nbsp;|&nbsp;
      <a href="/apps/bookmarks">즐겨찾기 그룹 관리</a>
  </div>



  <div>
      <table>
          <tr>
              <th>ID</th>
              <th>즐겨찾기 이름</th>
              <th>와이파이명</th>
              <th>등록일자</th>
              <th>비고</th>
          </tr>
          <!-- 여기서 if문 및 반복문 -->
          <c:choose>
              <c:when test="${empty wifiBookmarks}">
                <tr>
                    <td colspan="5">등록된 데이터가 없습니다.</td>
                </tr>
              </c:when>
              <c:otherwise>
                  <c:forEach var="wifiBookmark" items="${wifiBookmarks}">
                  <tr>
                      <td>${wifiBookmark.id}</td>
                      <td>${wifiBookmark.bookmarkName}</td>
                      <td><a href="/apps/bookmarks/wifi?id=${wifiBookmark.id}">${wifiBookmark.wifiName}</a></td>
                      <td>${wifiBookmark.created}</td>

                      <td>
                          <form class="delete" action="/apps/bookmarks/wifi" method="post">
                              <a class="delete-btn" href="#">삭제</a>
                              <input type="hidden" name="wifiId" value="${wifiBookmark.wifiId}"/>
                              <input type="hidden" name="bookmarkId" value="${wifiBookmark.bookmarkId}"/>
                              <input type="hidden" name="_method" value="DELETE"/>
                          </form>
                      </td>

                  </tr>
                  </c:forEach>
              </c:otherwise>
          </c:choose>
      </table>
  </div>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="/js/submitDelete.js"></script>
</body>
</html>
