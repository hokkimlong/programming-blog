<%@ page import="com.dev.model.Blog" %>
<%@ page import="com.dev.service.DateFormatter" %>
<jsp:include page="common/header.jsp"/>
<%
    Blog blog = (Blog) request.getAttribute("blog");
%>

<form class="m-2" method="post">
    <h3>404</h3>
    <h4>not found</h4>
</form>

<jsp:include page="common/footer.jsp"/>