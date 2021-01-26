<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/13/2020
  Time: 9:47 AM
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
    <a style="position:fixed;top:10px " href="/sell" class="btn btn-outline-danger">Quay lại</a>
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
                                                                           height="50"
                                                                           width="50"
                                                                           style=""/>${evaluate.customer.name}
                </h5>
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
        <textarea placeholder="Đăng bình luận" rows="2" cols="140" name="content" form="usrform">

         </textarea>
        <form method="post" id="usrform" action="/sell?action=comment&id=${product.id}">
            <input type="submit" value="Trả lời">
        </form>
    </div>

</div>
<style type="text/css">
    #admin {
        position: relative;
        left: 40px;
    }

    #avatar {
        background-size: cover;
        background-position: top center;
        border-radius: 50%;
    }

    #test {
        border: 1px #243024;
        width: 100%;
        height: 600px;
        overflow-x: hidden;
        overflow-y: auto;
    }
</style>
</body>
</html>
