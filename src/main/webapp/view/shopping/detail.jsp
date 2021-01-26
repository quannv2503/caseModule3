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
<div>
    <a style="position:fixed;top:10px " href="/shopping" class="btn btn-outline-danger">Quay lại</a>
</div>
<div class="container" style="position: relative;top:20px">
    <div class="row">
        <div class="col-md-6">
            <div>
                <img src="<c:out value="img/${product.image}.jpg"/>" style="height: 520px;width: 500px">
            </div>
        </div>
        <div class="col-md-6">

            <div>
                <p>Tên sản phẩm:<b> <c:out value="${product.name}"/></b></p>
                <p>Giá: <b><c:out value="${product.price}"/></b></p>
                <p>Hàng còn: <b><c:out value="${product.quantity}"/></b></p>
                <p>Đang giảm giá:<b> <c:out value="${product.discount}%"/></b></p>
                <p>Loại: <b><c:out value="${product.type}"/></b></p>
                <p>Mô tả: <b><c:out value="${product.description}"/></b></p>
                <p>Thông tin người bán:</p>
                <p style="position: relative;left:30px"> -Tên: <c:out value="${product.seller.getName()}"/></p>
                <p style="position: relative;left:30px">-Gọi đến số:<b> <c:out
                        value="${product.seller.getPhoneNumber()}"/> </b> để biết thêm chi tiết
                </p>
                <p style="position: relative;left:30px">-Đến mua tại cửa hàng: <c:out
                        value="${product.seller.getAddress()}"/></p>
                <a href="shopping?action=buy&id=${product.id}" class="btn btn-outline-danger">Buy</a>
            </div>
        </div>
    </div>

</div>
<div style="position: relative;top:50px" class="container">
    <div style="position: relative;left:200px">
        <h4>Các bình luận</h4>
    </div>
    <div id="test">

        <c:forEach var="evaluate" items="${evaluates}">

            <c:if test="${evaluate.customer!=null}">
                <h5 class="mt-0" style="position: relative;left:20px"><img id="avatar"
                                                                           src="<c:out value="img/${evaluate.customer.avatar}"/>"
                                                                           height="40"
                                                                           width="40"/>${evaluate.customer.name}</h5>
                <b>${evaluate.content}</b>

            </c:if>
            <c:if test="${evaluate.customer==null}">
                <h4 id="admin">#admin#</h4>
                <b>${evaluate.content}</b>
            </c:if>

            <br>
            <br>
        </c:forEach>
    </div>
    <div style="position: relative;top:3px">
        <textarea placeholder="Đăng bình luận" rows="2" cols="100" name="content" form="usrform">

         </textarea>
        <form method="post" id="usrform" action="/shopping?action=comment&id=${product.id}">
            <input type="submit" value="Đăng">
        </form>
    </div>

</div>
<footer style="position: relative;top:100px" class="container-fluid text-center">
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
<style type="text/css">
    #admin{
        position: relative;
        left: 40px;
    }
    #avatar {
        /* fill the container, preserving aspect ratio, and cropping to fit */
        background-size: cover;

        /* center the image vertically and horizontally */
        background-position: top center;

        /* round the edges to a circle with border radius 1/2 container size */
        border-radius: 50%;
    }

    #test {
        border: 1px #243024;
        width: 1000px;
        height: 300px;
        overflow-x: hidden;
        overflow-y: auto;
    }
</style>
</html>
