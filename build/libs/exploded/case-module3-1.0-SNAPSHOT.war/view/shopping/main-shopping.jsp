<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../../../bootstrap-4.5.2-dist/css/bootstrap.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<style>
    .carousel-inner img {
        width: 100%;
        height: 100%;
        vertical-align: middle;
        horiz-align: center;
    }

    .carousel-inner h1 {
        color: #17a2b8;
        font-weight: bold;
    }

    .nav-item a {
        font-size: large;
    }

    .display-4 {
        margin-top: 40px;
    }

    .card {
        margin-left: 60px;
        margin-top: 30px;
    }


    .card-img-top {
        width: 260px;
        height: 260px;
    }

    .navbar-brand {
        padding: 0;
    }

    footer {
        background-color: #f2f2f2;
        padding: 25px;
    }
</style>
<body>
<nav class="navbar navbar-expand-sm bg-light navbar-light sticky-top">
    <a class="navbar-brand" href="/shopping">
        <img src="/view/shopping/img/logo.png" width="60px"
             height="60px"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav">
            <li>
                <form class="form-inline my-2 my-lg-0" action="/shopping?action=search" method="post">
                    <input style="color: hotpink; border: hotpink" class="form-control"
                           placeholder="Search" aria-label="Search" name="search">
                    <button type="submit" class="btn btn-outline-danger"><i class="fa fa-search"></i></button>
                </form>
            </li>
            <li>
                <tr>
                    <form method="post" action="/shopping?action=history">
                        <button type="submit" class="btn btn-primary" class="btn btn-outline-danger"
                                style="float: right; position:absolute ; right: 150px">
                            
                            <i class="fa fa-shopping-cart"></i> ${quantity}
                        </button>
                    </form>
            </li>
            <li>
                <form>
                    <a href="/shopping?action=user"
                       style=" position:absolute ; right: 30px; top:auto ;border:1px solid #243024">
                        <img src="<c:out value="img/${customer.avatar}"/>" width="45" height="45">
                    </a>
                </form>
            </li>
        </ul>
    </div>
</nav>

<!--Welcome-->

<div class="container">
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-sm-6 col-md-4">
                <p hidden name="id"><c:out value="${product.id}"/></p>
                <div class="card" style="width: 262px; height: 490px; margin-bottom: 50px">
                    <img class="card-img-top" src="<c:out value="img/${product.image}.jpg"/>">
                    <div class="card-body">
                        <label></label>
                        <b class="card-title"><c:out value="${product.name}"/></b>
                        <br>
                        <label>Giá:</label>
                        <b><c:out value="${product.price}"/></b>
                        <br>
                        <label>Giảm giá:</label>
                        <b><c:out value="${product.discount}"/></b>
                        <br>
                        <a href="shopping?action=detail&id=${product.id}" class="btn btn-outline-danger">Detail</a>
                        <a href="shopping?action=buy&id=${product.id}" class="btn btn-outline-danger">Buy</a>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<div class="container-fluid">
    <div class="row text-center">
        <div class="col-12">
            <h1 class="display-4">kiti</h1>
        </div>
        <hr>
        <div class="col-12">
            <p class="lead">Ra đời năm 2020 với bề dày lịch sử 2h , cùng với các nghệ nhân điêu nghệ nhất trong ngành
                đã cho ra đời những sản phâm tinh túy nhất đến tay người tiêu dùng</p>
        </div>
    </div>
</div>

<%--cuôi--%>

<footer class="container-fluid text-center">
    <label>Online Store Copyright</label>
    <p> CÔNG TY LIÊN DOANH TNHH VIỆT NAM<br>
        Số 292 Bà Triệu, P. Lê Đại Hành, Q. Hai Bà Trưng, TP. Hà Nội.<br>
        Điện thoại: (028) 1232456<br>
        Email: lienhe@lamthon.com.vn<br>
        Mã số thuế: 0123456789<br>
        Ngày cấp: 29/10/1998 - Nơi cấp: Cục Thuế Thành Phố Hà Nội
    </p>
</footer>
</body>
<script src="../../../bootstrap-4.5.2-dist/js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</html>