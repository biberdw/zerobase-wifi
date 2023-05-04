<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>와이파이 정보 구하기</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
            crossorigin="anonymous"
    />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<style>
    .main {
        background-image: url("https://cdn.pixabay.com/photo/2018/04/28/10/55/architecture-3357028__340.jpg");
        background-size: cover;
        background-position: center;
        height: 250px;
        display: flex; /* 부모 div를 flex 컨테이너로 설정 */
        justify-content: center; /* 수평 정렬 */
        align-items: center; /* 수직 정렬 */
        color: white; /* 글자 색상 */
        font-size: 2em; /* 글자 크기 */
    }
</style>
<body>
<div class="main">서울시 공공와이파이</div>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input
                        class="form-control me-2"
                        type="search"
                        placeholder="검색"
                        aria-label="Search"
                />
                <button class="btn btn-outline-success" type="submit">
                    Search
                </button>
            </form>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4" style="margin-top: 100px; margin-left: 200px">
            <a href="/apps/wifi" style="margin-bottom: 50px">페이지로 보기</a>
            <div
                    id="carouselExample1"
                    class="carousel slide"
                    data-bs-ride="carousel"
            >
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img
                                src="/img/3.png"
                                class="d-block w-100"
                                alt="..."
                        />
                    </div>
                    <div class="carousel-item">
                        <img
                                src="/img/2.png"
                                class="d-block w-100"
                                alt="..."
                        />
                    </div>
                    <div class="carousel-item">
                        <img
                                src="/img/1.png"
                                class="d-block w-100"
                                alt="..."
                        />
                    </div>
                </div>
                <button
                        class="carousel-control-prev"
                        type="button"
                        data-bs-target="#carouselExample1"
                        data-bs-slide="prev"
                >
              <span
                      class="carousel-control-prev-icon"
                      aria-hidden="true"
              ></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button
                        class="carousel-control-next"
                        type="button"
                        data-bs-target="#carouselExample1"
                        data-bs-slide="next"
                >
              <span
                      class="carousel-control-next-icon"
                      aria-hidden="true"
              ></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
        <div class="col-md-4" style="margin-top: 100px; margin-left: 100px">
            <a id="map" href="#" style="margin-bottom: 50px">지도로 보기</a>
            <div
                    id="carouselExample2"
                    class="carousel slide"
                    data-bs-ride="carousel"
            >
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img
                                src="https://image.zdnet.co.kr/2021/01/15/e3b89e63a5a4a7d0bd44e4e9bb06e54c.png"
                                class="d-block w-100"
                                alt="..."
                        />
                    </div>
                    <div class="carousel-item">
                        <img
                                src="https://img.hankyung.com/photo/202101/01.25011782.1.jpg"
                                class="d-block w-100"
                                alt="..."
                        />
                    </div>
                    <div class="carousel-item">
                        <img
                                src="http://wiki.hash.kr/images/6/64/%EC%B9%B4%EC%B9%B4%EC%98%A4%EB%A7%B5_UI.png"
                                class="d-block w-100"
                                alt="..."
                        />
                    </div>
                </div>
                <button
                        class="carousel-control-prev"
                        type="button"
                        data-bs-target="#carouselExample2"
                        data-bs-slide="prev"
                >
              <span
                      class="carousel-control-prev-icon"
                      aria-hidden="true"
              ></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button
                        class="carousel-control-next"
                        type="button"
                        data-bs-target="#carouselExample2"
                        data-bs-slide="next"
                >
              <span
                      class="carousel-control-next-icon"
                      aria-hidden="true"
              ></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>
</div>
<div style="margin-top: 200px"></div>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"
></script>
<script>
    $("#map").click(function(){
        alert("아직 준비중입니다. ^^;;");
    });
</script>
</body>
</html>
