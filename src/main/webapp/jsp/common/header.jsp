<%@ page import="com.dev.model.User" %>
<html>
<head>
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
    <%
    User user = (User) session.getAttribute("user");
    %>
<div class="container-xl">
<nav class="p-2 d-flex border border-bottom justify-content-between my-2 border-dark">
    <h4>
        <a href="/">c0de blog</a>
    </h4>
    <%if (user == null) {%>
    <div>
        <a href="/login">login</a> |
        <a href="/register">register</a>
    </div>
    <%} else {%>
    <div>
        <a href="/bookmark">My Bookmarks</a> |
    </div>
    <div>
        <a href="/profile" class="border p-1 mr-1 border-primary d-inline-block mr-1"><b><%=user.getUsername()%>
        </b></a> |
        <a href="/logout">logout</a>
    </div>
    <%}%>
</nav>