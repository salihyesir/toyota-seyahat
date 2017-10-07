<%@ page import="java.net.URLDecoder" %><%
    String err="";
    if(session.getAttribute("rol")!=null)
    {
        if(session.getAttribute("rol").equals(2))
            response.sendRedirect("/Admin.jsp");
        else if(session.getAttribute("rol").equals(1))
            response.sendRedirect("/index.jsp");
    }
    Cookie[] cookies = request.getCookies();
    if(cookies !=null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("err")) err = URLDecoder.decode(cookie.getValue(), "UTF-8");
        }
    }
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <jsp:include page="master.jsp"/>
</head>
<body>
<div style="margin-top: 10%;">
    <div class="modal-dialog">
        <div class="loginmodal-container">
            <h1>Oturum Aç</h1><br>
            <form method="post" action="LoginServlet">
                <input type="text" name="userMail" placeholder="Kullanıcı Adı">
                <input type="password" name="password" placeholder="Parola">
                <input type="submit" name="login" class="login loginmodal-submit" value="Giriş">
            </form>
            <div class="login-help">
                <a href="default.jsp " style="color: #0B1022;">Anasayfaya Git</a>
                <div style="text-align: left; margin-bottom: 10px; color: red"><%= err %></div>
            </div>
        </div>
    </div>
</div>
<script>
    $('#userButton').hide();
    $('#logoutButton').hide();
</script>
</body>
</html>
