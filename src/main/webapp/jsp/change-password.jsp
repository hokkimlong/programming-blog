<jsp:include page="common/header.jsp"/>
<%
    String message = (String) request.getAttribute("message");
    Boolean success = (Boolean) request.getAttribute("success");
%>

<form class="m-2" method="post">
    <h5>change password</h5>
    <%if (message != null) {%>
    <div class="border d-inline p-1 my-1 text-danger">
        ${message}
    </div>
    <%}%>
    <%if (success != null) {%>
    <div class="border d-inline p-1 my-1 text-success">
        password change successfully
    </div>
    <%}%>
    <div>
        <label class="d-block">old password</label>
        <input name="oldPassword" type="password"/>
    </div>
    <div>
        <label class="d-block">new password</label>
        <input name="newPassword" type="password"/>
    </div>
    <div>
        <label class="d-block">confirm password</label>
        <input name="confirmPassword" type="password"/>
    </div>
    <div class="mt-2">
        <button>change</button>
    </div>
    <div class="my-2">
        <a href="/profile">Back</a>
    </div>
</form>

<jsp:include page="common/footer.jsp"/>