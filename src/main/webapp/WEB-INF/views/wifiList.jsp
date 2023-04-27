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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

  <form method="get" action="/apps/wifi">
      <div>
          <label for="latitude">LAT:</label>
          <input id="latitude" type="text" name="latitude"
                 <c:choose>
                     <c:when test='${empty latitude}'>value="0.0"</c:when>
                     <c:otherwise>value="${latitude}"</c:otherwise>
                 </c:choose>/>
          
          <label for="longitude">LNT:</label>
          <input id="longitude" type="text" name="longitude"
                  <c:choose>
                      <c:when test='${empty longitude}'>value="0.0"</c:when>
                      <c:otherwise>value="${longitude}"</c:otherwise>
                  </c:choose>/>

          <button id="location-btn" type="button">내 위치 가져오기</button>
          <button type="submit">근처 WIFI 정보 가져오기</button>
      </div>
  </form>

  <div>
      <table>
          <tr>
              <th>거리(Km)</th>
              <th>관리번호</th>
              <th>자치구</th>
              <th>와이파이명</th>
              <th>도로명주소</th>
              <th>상세주소</th>
              <th>설치위치(층)</th>
              <th>설치유형</th>
              <th>설치기관</th>
              <th>서비스구분</th>
              <th>망종류</th>
              <th>설치년도</th>
              <th>실내외구분</th>
              <th>WIFI접속환경</th>
              <th>X좌표</th>
              <th>Y좌표</th>
              <th>작업일자</th>
          </tr>
          <!-- 여기서 if문 및 반복문 -->
          <c:choose>
              <c:when test="${empty wifiList}">
                <tr>
                    <td colspan="17">위치정보를 입력한 후에 조회해주세요.</td>
                </tr>
              </c:when>
              <c:otherwise>
                  <c:forEach var="wifi" items="${wifiList}">
                  <tr>
                      <td>${fn:substring(wifi.distance, 0, fn:length(wifi.distance) < 6 ? fn:length(wifi.distance) : 6)}</td>
                      <td>${wifi.controlNumber}</td>
                      <td>${wifi.borough}</td>
                      <td>${wifi.name}</td>
                      <td>${wifi.address}</td>
                      <td>${wifi.detailedAddress}</td>
                      <td>${wifi.floor}</td>
                      <td>${wifi.type}</td>
                      <td>${wifi.agency}</td>
                      <td>${wifi.serviceType}</td>
                      <td>${wifi.netType}</td>
                      <td>${wifi.installationYear}</td>
                      <td>${wifi.inoutDoor}</td>
                      <td>${wifi.connEnv}</td>
                      <td>${wifi.latitude}</td>
                      <td>${wifi.longitude}</td>
                      <td>${wifi.workDate}</td>
                  </tr>
                  </c:forEach>
              </c:otherwise>
          </c:choose>
      </table>
  </div>

  <script src="/js/location.js"></script>
  <script src="/js/getWifi.js"></script>
  <script src="/js/isNumber.js"></script>
</body>
</html>
