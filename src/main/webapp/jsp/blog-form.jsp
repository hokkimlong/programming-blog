<%@ page import="com.dev.model.Blog" %>
<jsp:include page="common/header.jsp"/>

<%
    Blog blog = (Blog) request.getAttribute("blog");
%>

<form class="m-2" method="post">
    <h5><%=blog != null ? "update" : "create"%> blog</h5>
    <div>
        <label class="d-block">title</label>
        <input name="title" required value="<%=blog!=null? blog.getTitle() :""%>"/>
    </div>
    <div>
        <label class="d-block">summary</label>
        <textarea required rows="3" name="summary"><%=blog != null ? blog.getSummary() : ""%></textarea>
    </div>
    <div>
        <label class="d-block">description</label>
        <textarea required rows="10" name="description"><%=blog != null ? blog.getDescription() : ""%></textarea>
    </div>
    <div class="mt-2">
        <button><%=blog != null ? "Update" : "Create"%>
        </button>
    </div>
    <div class="my-2">
        <a href="/">Cancel</a>
    </div>
</form>

<jsp:include page="common/footer.jsp"/>