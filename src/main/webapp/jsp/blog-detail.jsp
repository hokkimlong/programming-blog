<%@ page import="com.dev.model.Blog" %>
<%@ page import="com.dev.service.DateFormatter" %>
<%@ page import="com.dev.model.User" %>
<jsp:include page="common/header.jsp"/>
<%
    Blog blog = (Blog) request.getAttribute("blog");
    User user = (User) session.getAttribute("user");
%>

<form class="m-2" method="post">
    <div class="d-flex justify-content-end">
        <%
            if (user != null) {
        %>
            <a href="/bookmark/add-blog/<%=blog.getId()%>">Add to Bookmark</a>
        <%} %>
    </div>
    <h4><%=blog.getTitle()%>
    </h4>
    <h5><%=blog.getSummary()%>
    </h5>
    <h6><%=DateFormatter.format(blog.getCreatedDate())%>
    </h6>
    <hr/>
    <p>
        <%=blog.getDescription()%>
    </p>
</form>

<jsp:include page="common/footer.jsp"/>