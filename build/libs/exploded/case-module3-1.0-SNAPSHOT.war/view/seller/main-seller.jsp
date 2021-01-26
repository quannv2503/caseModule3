<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/8/2020
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand-sm bg-light navbar-light sticky-top">
    <a class="navbar-brand">
        <img src="/view/shopping/img/logo.png" width="60px"
             height="60px"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav">
            <li>
                <form class="form-inline my-2 my-lg-0" action="/sell?action=search" method="post">
                    <input style="color: hotpink; border: hotpink" class="form-control" type="search"
                           placeholder="Search" aria-label="Search" name="search">
                    <button type="submit" class="btn btn-outline-danger"><i class="fa fa-search"></i></button>
                </form>
            </li>
            <li>
                <c:set var="quantity" value="0"></c:set>
                <c:forEach var="item" items="${sessionScope.cart}">
                    <c:set var="quantity" value="${quantity + item.quantity}"></c:set>
                </c:forEach>
                <tr>
                    <form method="post" action="/sell?action=general">
                        <button type="submit" class="btn btn-outline-danger"
                                style="float: right; position:absolute ; right: 150px">
                            Tổng quan
                        </button>
                    </form>
            </li>
            <li class="nav-item">
                <form>
                    <a href="/sell?action=logOut" type="button" class="btn btn-outline-danger"
                       style=" position:absolute ; right: 15px; top:auto">Đăng xuất
                    </a>
                </form>
            </li>
        </ul>
    </div>
</nav>

<%--thêm--%>
<a href="/sell?action=add" data-toggle="modal" data-target="#exampleModalCenter">Thêm mới sản phẩm</a></p>
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="container">
                    <form action="/sell?action=add" method="post" id="usrform1">
                        <div class="form-group">
                            <label>Tên sản phẩm</label>
                            <input type="text" class="form-control" name="name" placeholder="Nhập tên sản phẩm">
                        </div>

                        <div class="form-group">
                            <label>Giá</label>
                            <input type="text" class="form-control" name="price" placeholder="Nhập giá">
                        </div>

                        <div class="form-group">
                            <label>Số lượng</label>
                            <input type="text" class="form-control" name="quantity" placeholder="Nhập số lượng">
                        </div>

                        <div class="form-group">
                            <label>Giảm giá</label>
                            <input type="text" class="form-control" name="discount"
                                   placeholder="giảm giá">
                        </div>

                        <div class="form-group" class="form-control">
                            <label>Loại</label>
                            <select name="type">
                                <option value="quan ao">Quần áo</option>
                                <option value="giay dep">Giày dép</option>
                                <option value="tui xach">Túi xách</option>
                                <option value="khac">Khác</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Mô tả</label>
                            <textarea placeholder="Thêm mô tả" rows="5" cols="59" name="description" form="usrform1">

                          </textarea>
                        </div>
                        <div class="form-group">
                            <label>Link ảnh</label>
                            <input type="text" class="form-control" name="image"
                                   placeholder="nhập link ảnh">
                        </div>

                        <button type="submit" class="btn btn-primary">ADD</button>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<%--thân--%>
<div class="container">
    <div class="row">
        <c:forEach var="product" items='${requestScope["products"]}'>
            <div class="col-md-6 col-lg-4" style="margin-bottom: 0px">
                <div class="single_service">
                    <div class="thumb">
                        <div><img src="<c:out value="img/${product.image}.jpg"/>" style="height: 150px">
                        </div>
                    </div>
                    <div class="service_infor">
                        <p>Tên sản phẩm: <c:out value="${product.name}"/></p>
                        <p>Giá tiền: <c:out value="${product.price}"/></p>
                        <p>Số lượng: <c:out value="${product.quantity}"/></p>
                        <p>Giảm giá: <c:out value="${product.discount}%"/></p>
                        <div class="row">
                            <div class="col">
                                <button type="button" class="btn btn-outline-secondary" data-toggle="modal"
                                        data-target="#exampleModalUpdate${product.id}">Chỉnh sửa
                                </button>
                                <div class="modal fade" id="exampleModalUpdate${product.id}" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <div class="container">
                                                    <h2>Chỉnh sửa thông tin</h2>
                                                    <form action="/sell?action=edit" method="post">
                                                        <label>Tên</label>
                                                        <input type="text" class="form-control" name="name"
                                                               value="${product.name}">

                                                        <label>Giá</label>
                                                        <input type="text" class="form-control" name="price"
                                                               value="${product.price}">

                                                        <label>Số lượng</label>
                                                        <input type="text" class="form-control" name="quantity"
                                                               value="${product.quantity}">

                                                        <label>Giá</label>
                                                        <input type="text" class="form-control" name="price"
                                                               value="${product.price}">

                                                        <label>Giảm giá</label>
                                                        <input type="text" class="form-control" name="discount"
                                                               value="${product.discount}">

                                                        <label>Loại</label>
                                                        <select name="type">
                                                            <option value="quan ao">Quần áo</option>
                                                            <option value="giay dep">Giầy dép</option>
                                                            <option value="tui xach">Túi xách</option>
                                                            <option value="khac">Khác</option>
                                                        </select>
                                                        <label>Mô tả</label>
                                                        <input type="text" class="form-control" name="description"
                                                               value=" ${product.description}">
                                                        <label>Link ảnh</label>
                                                        <input type="text" class="form-control" name="image"
                                                               value="${product.image}">
                                                        <div style="display: none">
                                                            <label>ẩn</label>
                                                            <input type="text" class="form-control" name="id"
                                                                   value="${product.id}">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <input type="submit" class="btn btn-primary" value="Update">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <button type="button" class="btn btn-outline-secondary" data-toggle="modal"
                                        data-target="#exampleModalUpdate${product.name}">Ngừng bán
                                </button>
                                <div class="modal fade" id="exampleModalUpdate${product.name}" tabindex="-1"
                                     role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <div class="container">
                                                    <h2>ARE YOU SURE?</h2>
                                                    <form method="post" action="/sell?action=delete">
                                                        <div style="display: none">
                                                            <label>ẩn</label>
                                                            <input type="text" class="form-control" name="id"
                                                                   value="${product.id}">
                                                        </div>

                                                        <label>Name</label>
                                                        <input type="text" class="form-control" name="name"
                                                               value="${product.name}">
                                                        <label>Price</label>
                                                        <input type="text" class="form-control" name="price"
                                                               value="${product.price}">

                                                        <br>
                                                        <div class="modal-footer">
                                                            <input type="submit" value="Xóa">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    Đóng
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a href="sell?action=rep&id=${product.id}" class="btn btn-outline-danger"
                               style="position: relative;bottom:85px;left: 204px ">Rep bình luận</a>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
</div>
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

    #jumbo-text {
        margin-left: 120px;
    }

    .display-4 {
        margin-top: 40px;
    }

    .card {
        margin-left: 60px;
        margin-top: 30px;
    }

    .footer-img {
        margin-left: 70px;
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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</html>
