
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
    else if(session.getAttribute("rol").equals(1))
        response.sendRedirect("/index.jsp");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="master.jsp"/>
</head>
<body>
<div>
    <div id="updateMod">

        <input type="hidden" id="userId" value="">

        <div class="form-group">
            <label for="user">Kullanıcı Adı, Soyadı</label>
            <input type="text" class="form-control" id="user" maxlength="45" >
        </div>
        <div class="form-group">
            <label for="register">Sicil Numarası</label>
            <input type="text" class="form-control" id="register" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
        </div>
        <div class="form-group">
            <label for="pass">Şifre</label>
            <input type="password" class="form-control" id="pass" maxlength="45" >
        </div>
        <div class="form-group">
            <label for="department">Bölümü</label>
            <input type="text" class="form-control" id="department" maxlength="20" >
        </div>
        <div class="form-group">
            <label for="departmentAdmin">Bölüm Müdürü</label>
            <input type="text" class="form-control" id="departmentAdmin" maxlength="50" >
        </div>
        <div class="form-group">
            <label for="email">E-Posta</label>
            <input type="text" class="form-control" id="email" maxlength="50" >
        </div>
        <div class="form-group">
        <label for="Role">Rolü</label>
        <select id="Role" class="form-control">
            <option value="1">Kullanıcı</option>
            <option value="2">Admin</option>
        </select>
        </div>



        <style>
            .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20px; }
            .toggle.ios .toggle-handle { border-radius: 20px; }
        </style>

        <input type="checkbox" data-toggle="toggle" data-style="ios" name="chcInfo" id="chcInfo" onchange="check()"  style="width: auto;"  /><br><br>
        <div id="info" style="border: 1px dashed white;  width: 50%; display: none;  padding: 10px; margin-top: 10px; margin-bottom: 10px; color: white;">

            <input type="radio" name="type" style="width: auto;" value="Haftalık" checked/> Haftalık <input type="radio" name="type" style="width: auto;" value="Aylık" /> Aylık
            <br>

            <div class="form-group">
            <select id="day" class="form-control">
                    <option value="Pazartesi">Pazartesi</option>
                    <option value="Salı">Salı</option>
                    <option value="Çarşamba">Çarşamba</option>
                    <option value="Perşembe">Perşembe</option>
                    <option value="Cuma">Cuma</option>
                    <option value="Cumartesi">Cumartesi</option>
                    <option value="Pazar">Pazar</option>
            </select>
            </div>



        </div>
        <button type="submit" class="btn btn-default" style="margin-bottom: 20px;" onclick="cont($('#email').val())">Kaydet</button>

    </div>
</div>



<script>
    function check() {
        if ($('#chcInfo').is(":checked"))
            $("#info").show();
        else
            $("#info").hide();
    }
</script>

<script src="resources/js/Save/UserAdd.js"></script>
</body>
</html>
