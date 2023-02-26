<%@ page import="java.util.List" %>
<%@ page import="com.dev.service.DateFormatter" %>
<%@ page import="com.dev.model.BookmarkBlog" %>
<%@ page import="com.dev.model.Bookmark" %>
<jsp:include page="common/header.jsp"/>
<%
    Bookmark bookmark = (Bookmark) request.getAttribute("bookmark");
    List<BookmarkBlog>bookmarkBlogs = (List<BookmarkBlog>) request.getAttribute("bookmarkBlogs");
%>
<div class="d-flex justify-content-between">
    <h5 class="my-3">bookmark : <%=bookmark.getName()%></h5>
</div>
<div class="row">
    <%
        for (BookmarkBlog bookmarkBlog : bookmarkBlogs) {
    %>
    <div class="col-4">
        <div class="border border-dark p-2">
            <h4><%=bookmarkBlog.getBlog().getTitle()%>
            </h4>
            <h5><%=bookmarkBlog.getBlog().getSummary()%>
            </h5>
            <h6><%=DateFormatter.format(bookmarkBlog.getBlog().getCreatedDate())%>
            </h6>
            <hr/>
            <div class="d-flex justify-content-between">
                <div>
                    <a href="/blog/<%=bookmarkBlog.getBlog().getId()%>">View Blog</a> |
                    <a href="/bookmark-blog/<%=bookmarkBlog.getId()%>/<%=bookmark.getId()%>/delete">Remove From Bookmark</a>
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