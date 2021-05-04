<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  model.User: Xiaomi
  Date: 15.01.2021
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:if test="${sessionScope.isGuest}">
                    <%--                    <li class="nav-item">--%>
                    <%--                        <a class="nav-link active" aria-current="page" href="#">Home</a>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="nav-item">--%>
                    <%--                        <a class="nav-link" href="#">Link</a>--%>
                    <%--                    </li>--%>

                    <li class="nav-item">
                        <a class="nav-link" aria-current="page">Hello guest</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/reg">Registration</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/auth">Authorization</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.isUser}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="/info">Hello ${sessionScope.user.getName()}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/calc">Calculator</a>
                    </li>
                    <c:if test="${sessionScope.isAdmin}">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/allInfo">All users info</a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/out">Log out</a>
                    </li>
                </c:if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Library
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/authors">Authors</a></li>
                        <li><a class="dropdown-item" href="/books">Books</a></li>
                        <c:if test="${sessionScope.isUser}">
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/basket">Basket</a></li>
                        </c:if>
                        <c:if test="${sessionScope.isAdmin}">
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/addBook">Add book</a></li>
                            <li><a class="dropdown-item" href="/addAuthor">Add author</a></li>
                        </c:if>

                    </ul>
                </li>
            </ul>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

</body>
</html>
