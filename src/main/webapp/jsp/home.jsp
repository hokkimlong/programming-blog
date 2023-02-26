<%@ page import="com.dev.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dev.service.DateFormatter" %>
<%@ page import="com.dev.model.User" %>
<%@ page import="com.dev.service.AuthService" %>
<jsp:include page="common/header.jsp"/>
<%
    List<Blog> blogs = (List<Blog>) request.getAttribute("blogs");
    User user = (User) session.getAttribute("user");
    Boolean isUserAdmin = false;
    if (user != null) {
        isUserAdmin = user.getRole().getName().equals("admin");
    }
%>
<div class="d-flex justify-content-between">
    <h5 class="my-3">blogs</h5>
    <%if (user != null && isUserAdmin) {%>
    <a href="/blog/create">Create blog</a>
    <%}%>
</div>
<div class="row">
    <%
        for (Blog blog : blogs) {
    %>
    <div class="col-4">
        <div class="border border-dark p-2">
            <h4><%=blog.getTitle()%>
            </h4>
            <h5><%=blog.getSummary()%>
            </h5>
            <h6><%=DateFormatter.format(blog.getCreatedDate())%>
            </h6>
            <hr/>
            <div class="d-flex justify-content-between">
                <div>
                    <a href="/blog/<%=blog.getId()%>">View</a>
                    <%if (user != null && isUserAdmin) {%>
                    |
                    <a href="/blog/<%=blog.getId()%>/edit">Edit</a> |
                    <a href="/blog/<%=blog.getId()%>/delete">Delete</a>
                    <%}%>
                </div>
                <div>
                    <%--                   <a href="/b">Add to bookmark</a>--%>
                </div>
            </div>
        </div>
    </div>
    <%}%>
</div>
<jsp:include page="common/footer.jsp"/>