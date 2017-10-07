
<%--
  Created by IntelliJ IDEA.
  User: Salih
  Date: 19.2.2017
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%
    if(session.getAttribute("rol")==null){
        response.sendRedirect("/login.jsp");
    }
    else if(session.getAttribute("rol").equals(2))
        response.sendRedirect("/Admin.jsp");

    String id= "";
    Cookie[] cookies = request.getCookies();
    if(cookies !=null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) id = cookie.getValue();
        }
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="master.jsp"/>
</head>
<body>
        <input type="hidden" value="<%= id %>" id="userId" />
        <div class="form-group">
            <label for="location">Seyahat Yeri</label>
            <input type="text" class="form-control" id="location" maxlength="30" >
        </div>
        <div class="form-group">
            <label for="mission">Gidiş Amacı</label>
            <input type="text" class="form-control" id="mission" maxlength="20" >
        </div>
        <div class="form-group">
            <label for="projectCode">Proje Kodu</label>
            <input type="text" class="form-control" id="projectCode" maxlength="5" >
        </div>

        <div id="datepairExample">
            <div class="form-group">
                <label>Seyahat Başlangıç Bitiş Tarihleri</label><br>
                <input type="text" id="ds" class="date start" />
                <input type="text" id="de" class="date end" />
            </div>
        </div>

        <button class="btn btn-default" onclick="saveFunction()">Ekle</button>
</div>

<script>
    $('#datepairExample .date').datepicker({
        'format': 'yyyy-m-d',
        'autoclose': true
    });

    // initialize datepair
    $('#datepairExample').datepair();

</script>
        <script src="resources/js/Save/UserTravelAdd.js"></script>
</body>
</html>
