<%@ page import="com.dev.model.User" %>
<jsp:include page="common/header.jsp"/>
<% User user  = (User) session.getAttribute("user");%>
<h5>profile</h5>
<div>
    <p><b>username:</b> <%=user.getUsername()%></p>
    <p><b>role:</b> <%=user.getRole().getName()%></p>
    <div>
        <a href="/change-password">Change Password</a>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>