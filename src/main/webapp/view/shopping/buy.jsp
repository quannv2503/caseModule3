<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/10/2020
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../../bootstrap-4.5.2-dist/css/bootstrap.css">

</head>
<body>
<div class="container" style="position: relative;top:20px">
    <div class="row">
        <div class="col-md-6">
            <div>
                <img src="<c:out value="img/${product.image}.jpg"/>" style="height: 400px;width: 450px">
            </div>
            <div>
                <p>Tên sản phẩm:<b><c:out value="${product.name}"/></b></p>
                <p>Giá:<b> <c:out value="${product.price}"/></b></p>
                <p>Hàng còn:<b> <c:out value="${product.quantity}"/></b></p>
                <p>Đang giảm giá:<b> <c:out value="${product.discount}%"/></b></p>
                <p>Mô tả:<b> <c:out value="${product.description}"/></b></p>
            </div>
        </div>
        <div class="col-md-6">
            <div>
                <form action="/shopping" method="get">
                    <button type="submit">quay lại</button>
                </form>
                <br>
                <h4>Bước 1:Nhập số lượng sản phẩm muốn mua</h4>
                <div>
                    <form method="post" action="/shopping?action=next2&id=${product.id}">
                        <input name="quantityBuy" type="number"
                        <c:if test='${requestScope["a"] != null}'>
                               value="${quantityBuy}"
                        </c:if>
                        >
                        <div style="display: none">
                            <label>ẩn</label>
                            <input type="text" name="quantity-product" value="${product.quantity}">
                        </div>

                        <input type="submit" value="tiếp">
                    </form>
                </div>

                <div>
                    <c:if test='${requestScope["a"] != null}'>
                        <h4>Bước 2:Xác nhận lại thông tin khách hàng</h4>
                        <div>
                            <form method="post" action="/shopping?action=order&id=${product.id}&quantityBuy=${quantityBuy}">
                                <div style="display: none">
                                    <label>ẩn</label>
                                    <input type="text" name="id-customer" value="${customer.id}">
                                </div>
                                <div style="display: none">
                                    <label>ẩn</label>
                                    <input type="text" name="avatar" value="${customer.avatar}">
                                </div>
                                <div style="display: none">
                                    <label>ẩn</label>
                                    <input type="text" name="quantity-product" value="${product.quantity}">
                                </div>
                                <label>Tên:</label>
                                <input type="text" name="name" value="${customer.name}">
                                <br>
                                <label>SĐT:</label>
                                <input type="text" name="phoneNumber" value="${customer.phoneNumber}">
                                <br>
                                <label>Địa chỉ:</label>
                                <input type="text" name="address" value="${customer.address}">
                                <br>
                                <br>
                                <h3>Tổng số tiền:<c:out value="${quantityBuy*product.realPrice}"/></h3>
                                <input type="submit" value="Đặt hàng">
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
