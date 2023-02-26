<%@ page import="com.dev.model.Bookmark" %>
<%@ page import="java.util.List" %>
<jsp:include page="common/header.jsp"/>
<%
    List<Bookmark> bookmarks = (List<Bookmark>) request.getAttribute("bookmarks");
%>
<div class="d-flex justify-content-between">
    <h5>Bookmark</h5>
    <a href="/bookmark/create" >Create Bookmark</a>
</div>
<div class="row">
    <%
        for (Bookmark bookmark : bookmarks) {
    %>
    <div class="col-4">
        <div class="border border-dark p-2">
            <h4><%=bookmark.getName()%>
            </h4>
            <hr/>
            <a href="/bookmark/<%=bookmark.getId()%>">View</a> |
            <a href="/bookmark/<%=bookmark.getId()%>/edit">Edit</a> |
            <a href="/bookmark/<%=bookmark.getId()%>/delete">Delete</a>
        </div>
    </div>
    <%}%>
</div>
<jsp:include page="common/footer.jsp"/>