<%@ page import="com.dev.model.Bookmark" %>
<jsp:include page="common/header.jsp"/>

<%
    Bookmark bookmark = (Bookmark) request.getAttribute("bookmark");
%>

<form class="m-2" method="post">
    <h5><%=bookmark != null ? "update" : "create"%> bookmark</h5>
    <div>
        <label class="d-block">title</label>
        <input name="name" required value="<%=bookmark!=null? bookmark.getName() :""%>"/>
    </div>
    <div class="mt-2">
        <button><%=bookmark != null ? "Update" : "Create"%>
        </button>
    </div>
    <div class="my-2">
        <a href="/">Cancel</a>
    </div>
</form>

<jsp:include page="common/footer.jsp"/>