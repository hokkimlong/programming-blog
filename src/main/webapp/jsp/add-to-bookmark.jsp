<%@ page import="com.dev.model.Bookmark" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dev.model.Blog" %>
<jsp:include page="common/header.jsp"/>
<%
    List<Bookmark> bookmarks = (List<Bookmark>) request.getAttribute("bookmarks");
    Blog blog = (Blog) request.getAttribute("blog");
%>
<div class="d-flex justify-content-between">
    <h5>Add blog <%=blog.getTitle()%> to bookmark</h5>
</div>
<form method="post" action="/bookmark/add-blog">
    <input type="hidden" name="blogId" value="<%=blog.getId()%>"/>
    <select name="bookmarkId"> <%
        for (Bookmark bookmark : bookmarks) {
    %>
        <option value="<%=bookmark.getId()%>"><%=bookmark.getName()%></option>
        <%}%>
    </select>
    <button>Add</button>
</form>
<jsp:include page="common/footer.jsp"/>