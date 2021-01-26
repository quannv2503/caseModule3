<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/12/2020
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
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
<div>
    <a style="position:fixed;top:10px " href="/sell" class="btn btn-outline-danger">Quay lại</a>
</div>
<center><h2>Thống kê các sản phẩm của bạn bán được</h2></center>
<div class="container">
    <div style="position: absolute;top: 100px">
        <div class="row">
            <c:forEach var="product" items="${generalList}">
                <div class="col-md-4">
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
                            <label>Số lượng đã bán:</label>
                            <b><c:out value="${product.sumQuantityBuy}"/></b> sp
                            <br>
                            <label>Doanh thu:</label>
                            <b><c:out value="${product.sumQuantityBuy*product.price}"/></b> đ
                            <br>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
