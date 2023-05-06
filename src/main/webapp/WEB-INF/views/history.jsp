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
      <a href="/apps/histories">위치 히스토리 목록</a>&nbsp;&nbsp;|&nbsp;
      <a id="fetchWifi" href="#">Open API 와이파이 정보 가져오기</a>
  </div>

  <div>
      <table>
          <tr>
              <th>ID</th>
              <th>X좌표</th>
              <th>Y좌표</th>
              <th>조회일자</th>
              <th>비고</th>
          </tr>
          <!-- 여기서 if문 및 반복문 -->
          <c:choose>
              <c:when test="${empty histories}">
                <tr>
                    <td colspan="5">조회한 기록이 없습니다.</td>
                </tr>
              </c:when>
              <c:otherwise>
                  <c:forEach var="history" items="${histories}">
                      <form action="/apps/histories" method="POST">
                          <tr>

                          <td>${history.id}</td>
                          <td>${history.latitude}</td>
                          <td>${history.longitude}</td>
                          <td>${history.created}</td>

                          <td><button type="submit">삭제</button> </td>
                          <input type="hidden" name="_method" value="DELETE"/>
                          <input type="hidden" name="id" value="${history.id}">
                        </tr>
                      </form>

                  </c:forEach>
              </c:otherwise>
          </c:choose>
      </table>
  </div>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="/js/location.js"></script>
  <script src="/js/getWifi.js"></script>
</body>
</html>
