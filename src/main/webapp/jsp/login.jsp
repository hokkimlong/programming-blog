<jsp:include page="common/header.jsp"/>
<%
    String message = (String) request.getAttribute("message");
%>
<form class="m-2" method="post">
    <h5>login</h5>
    <%if (message != null) {%>
    <div class="border d-inline p-1 my-1 text-danger">
        ${message}
    </div>
    <%}%>
    <div>
        <label class="d-block">username</label>
        <input name="username"/>
    </div>
    <div>
        <label class="d-block">password</label>
        <input name="password" type="password"/>
    </div>
    <div class="mt-2">
        <button>login</button>
    </div>
    <div class="my-2">
        <a href="/register">Need Account?</a>
    </div>
</form>
<jsp:include page="common/footer.jsp"/>