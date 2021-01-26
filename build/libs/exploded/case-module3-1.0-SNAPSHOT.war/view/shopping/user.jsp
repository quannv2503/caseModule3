<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/12/2020
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">TiKi niềm vui khi mua sắm</h1>
    <div class="container">
        <div class="row profile">
            <div class="col-md-5">
                <div class="profile-sidebar">
                    <div class="profile-userpic"><img
                            src="<c:out value="img/${customer.avatar}"/>"
                            class="img-responsive" alt="Thông tin cá nhân">
                    </div>
                    <div class="profile-usertitle">
                        <div class="profile-usertitle-name"><c:out value="${customer.name}"/></div>
                        <div class="profile-usertitle-job"> Web Developer</div>
                    </div>
                    <div class="profile-userbuttons">
                        <form action="/shopping" method="post">
                            <button type="submit" class="btn btn-success btn-sm">Trang chủ</button>
                        </form>
                        <form action="/shopping?action=logOut" method="post">
                            <button type="submit" class="btn btn-danger btn-sm">Đăng xuất</button>
                        </form>
                    </div>
                    <div class="profile-usermenu">
                        <ul class="nav">
                            <div class="logo_menuchinh"
                                 style="float:left; padding-top:5px; padding-left:10px; color:#fff; margin-left:auto; margin-right:auto ; line-height:40px; font-size:16px;font-weight:bold;font-family:Arial">
                            </div>
                            <li class="active"><i
                                    class="glyphicon glyphicon-info-sign"></i>
                                <button type="button" class="btn btn-outline-secondary" data-toggle="modal"
                                        data-target="#exampleModalUpdate">Chỉnh sửa thông tin cá nhân
                                </button>
                            </li>
                            <li><a href="https://hocwebgiare.com/"> <i class="glyphicon glyphicon-heart"></i> Sản phẩm
                                yêu thích </a>
                            </li>
                            <li><a href="https://hocwebgiare.com/" target="_blank"> <i
                                    class="glyphicon glyphicon-shopping-cart"></i> Quản lý đơn hàng </a>
                            </li>
                            <li><a href="https://hocwebgiare.com/"> <i class="glyphicon glyphicon-envelope"></i> Tin
                                nhắn </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-7">
                <div class="profile-content">
                    <div>
                        <h3>Họ và tên:<c:out value="${customer.name}"/></h3>
                        <h3>Địa chỉ:<c:out value="${customer.address}"/></h3>
                        <h3>Số điện thoại:<c:out value="${customer.phoneNumber}"/></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div >
    <div class="modal fade" id="exampleModalUpdate" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="container" style="margin-top: 200px">
                        <h2>Chỉnh sửa thông tin</h2>
                        <form action="/shopping?action=updateCustomer" method="post">
                            <div class="form-group" style="display: none">
                                <label>ẩn</label>
                                <input type="text" class="form-control" name="id-customer"
                                       value="${customer.getId()}">
                            </div>
                            <label>Tên</label>
                            <input type="text" class="form-control" name="name"
                                   value="${customer.name}">

                            <label>Địa chỉ</label>
                            <input type="text" class="form-control" name="address"
                                   value="${customer.address}">

                            <label>Số điện thoại</label>
                            <input type="text" class="form-control" name="phoneNumber"
                                   value="${customer.phoneNumber}">

                            <label>Avatar</label>
                            <input type="text" class="form-control" name="avatar"
                                   value=" ${customer.avatar}">
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
</body>
<style>
    body {
        background: #F1F3FA;
    }


    .profile {
        margin: 20px 0;
    }

    .profile-sidebar {
        padding: 20px 0 10px 0;
        background: #fff;
    }


    .profile-userpic img {
        float: none;
        margin: 0 auto;
        width: 60%;
        height: 50%;
        -webkit-border-radius: 50% !important;
        -moz-border-radius: 50% !important;
        border-radius: 50% !important;
    }


    .profile-usertitle {
        text-align: center;
        margin-top: 20px;
    }


    .profile-usertitle-name {
        color: #5a7391;
        font-size: 16px;
        font-weight: 600;
        margin-bottom: 7px;
    }


    .profile-usertitle-job {
        text-transform: uppercase;
        color: #5b9bd1;
        font-size: 12px;
        font-weight: 600;
        margin-bottom: 15px;
    }


    .profile-userbuttons {
        text-align: center;
        margin-top: 10px;
    }


    .profile-userbuttons .btn {
        text-transform: uppercase;
        font-size: 11px;
        font-weight: 600;
        padding: 6px 15px;
        margin-right: 5px;
    }


    .profile-userbuttons .btn:last-child {
        margin-right: 0px;
    }

    .profile-usermenu {
        margin-top: 30px;
    }

    .profile-usermenu ul li {
        border-bottom: 1px solid #f0f4f7;
    }


    .profile-usermenu ul li:last-child {
        border-bottom: none;
    }

    .profile-usermenu ul li a {
        color: #93a3b5;
        font-size: 14px;
        font-weight: 400;
    }


    .profile-usermenu ul li a i {
        margin-right: 8px;
        font-size: 14px;
    }

    .profile-usermenu ul li a:hover {
        background-color: #fafcfd;
        color: #5b9bd1;
    }


    .profile-usermenu ul li.active {
        border-bottom: none;
    }


    .profile-usermenu ul li.active a {
        color: #5b9bd1;
        background-color: #f6f9fb;
        border-left: 2px solid #5b9bd1;
        margin-left: -2px;
    }


    .profile-content {
        padding: 20px;
        background: #fff;
        min-height: 460px;
    }

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
