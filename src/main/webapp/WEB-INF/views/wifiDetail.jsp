<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DONGWOO
  Date: 2023/04/30
  Time: 1:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="/css/table.css">
    <link rel="stylesheet" type="text/css" href="/css/tableDetail.css">
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
    <form action="/apps/bookmarks/wifi" method="post">
        <select name="bookmarkId">
            <c:choose>
                <c:when test="${empty bookmarks}">
                    <option>즐겨찾기 그룹 이름 선택</option>
                </c:when>
                <c:otherwise>
                        <option selected>즐겨찾기 그룹 이름 선택</option>
                    <c:forEach var="bookmark" items="${bookmarks}">
                        <option value="${bookmark.id}">${bookmark.name}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </select>
        <input type="hidden" name="wifiId" value="${wifiDto.id}">
        <button type="submit">즐겨찾기 추가하기</button>
    </form>
</div>

<div>
    <table>
        <tr>
            <th>거리(Km)</th>
            <td>0.000</td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td>${wifiDto.controlNumber}</td>
        </tr>
        <tr>
            <th>자치구</th>
            <td>${wifiDto.borough}</td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td>${wifiDto.name}</td>
        </tr>
        <tr>
            <th>도로명주소</th>
            <td>${wifiDto.address}</td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td>${wifiDto.detailedAddress}</td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
            <td>${wifiDto.floor}</td>
        </tr>
        <tr>
            <th>설치유형</th>
            <td>${wifiDto.type}</td>
        </tr>
        <tr>
            <th>설치기관</th>
            <td>${wifiDto.agency}</td>
        </tr>
        <tr>
            <th>서비스구분</th>
            <td>${wifiDto.serviceType}</td>
        </tr>
        <tr>
            <th>망종류</th>
            <td>${wifiDto.netType}</td>
        </tr>
        <tr>
            <th>설치년도</th>
            <td>${wifiDto.installationYear}</td>
        </tr>
        <tr>
            <th>실내외구분</th>
            <td>${wifiDto.inoutDoor}</td>
        </tr>
        <tr>
            <th>WIFI접속환경</th>
            <td>${wifiDto.connEnv}</td>
        </tr>
        <tr>
            <th>X좌표</th>
            <td>${wifiDto.latitude}</td>
        </tr>
        <tr>
            <th>Y좌표</th>
            <td>${wifiDto.longitude}</td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td>${wifiDto.workDate}</td>
        </tr>
    </table>
</div>
<script src="/js/getWifi.js"></script>
</body>
</html>
