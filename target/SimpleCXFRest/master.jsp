<title>Toyota Seyahat Takip Servisi</title>
<link rel="shortcut icon" type="image/png" href="resources/favicon.png"/>
<script src="resources/lib/jquery-2.1.3.min.js"></script>
<script src="resources/lib/underscore.js"></script>
<script src="resources/lib/backbone.js"></script>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<script src="resources/lib/bootstrap.min.js"></script>
<link rel="stylesheet" href="resources/css/mystyle.css">




<script src="resources/lib/jquery.timepicker.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/jquery.timepicker.css" />

<script src="resources/lib/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-datepicker.standalone.css" />

<script src="resources/lib//pikaday.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/pikaday.css" />

<script src="resources/lib//jquery.ptTimeSelect.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" href="resources/css/jquery-ui.css" type="text/css" media="all" />

<script src="resources/lib/moment.min.js"></script>
<script src="resources/lib/site.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/site.css" />

<script src="resources/lib/datepair.min.js"></script>
<script src="resources/lib/jquery.datepair.min.js"></script>

<script src="resources/lib/bootstrap-toggle.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-toggle.css" />

<script src="resources/js/UserUpdate.js"></script>



<script src="resources/js/controller.js"></script>
<%
    String userName ="", id= "" ,mail="";
    Cookie[] cookies = request.getCookies();
    if(cookies !=null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userName")) userName = cookie.getValue();
            if (cookie.getName().equals("id")) id = cookie.getValue();
            if (cookie.getName().equals("mail")) mail = cookie.getValue();
        }
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<style>
    .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20px; }
    .toggle.ios .toggle-handle { border-radius: 20px; }
</style>


<body style="margin-top:5px; padding:0px; background-image: url('resources/img/21_AirlineSecrecy.jpg');  background-size: 1600px 800px;">
<div class="container">

    <div style="float: left; height: 100px; width: 50%; margin-top: 10px;">
        <a href="login.jsp">  <img src="resources/img/logo_toyota.png" width="120" height="72"  /></a>
    </div>
    <div style="height: 100px; margin-top: 10px; text-align: right;">
        <button type="button" id="userButton" onclick="x(<%= id %>)"  class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg"><%=userName %></button>
        <form method="get" action="LogoutServlet" style="float: right; margin-top: 5px;">
        <button type="submit" id="logoutButton" class="glyphicon glyphicon-off myButton"></button>
        </form>
    </div>

    <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" id="dialogPanel">
        <div class="modal-dialog modal-lg" role="document" style="width: 35%;">
            <div class="modal-content" style="background-color: grey; padding: 10px;">

                    <div id="updateMod">
                        <script type="template/html" id="UpdateView">

                            <div class="form-group update-form">
                                <label for="userUpdate">Kullanıcı Adı, Soyadı</label>
                                <input type="text" class="form-control" id="userUpdate" value="{{=  userName }}" maxlength="45" >
                            </div>
                            <div class="form-group update-form">
                                <label for="registerUpdate">Sicil Numarası</label>
                                <input type="text" class="form-control" id="registerUpdate" value="{{=  userRegister }}" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
                            </div>
                            <div class="form-group update-form">
                                <label for="userMailUpdate">E-Posta</label>
                                <input type="text" class="form-control" id="userMailUpdate" value="{{=  userMail }}" maxlength="50" >
                            </div>
                            <div class="form-group update-form">
                                <label for="passUpdate">Şifre</label>
                                <input type="password" class="form-control" id="passUpdate" maxlength="45" >
                            </div>
                            <div class="form-group update-form">
                                <label for="departmentUpdate">Bölümü</label>
                                <input type="text" class="form-control" id="departmentUpdate" value="{{=  userDepartment }}" maxlength="20" >
                            </div>
                            <div class="form-group update-form">
                                <label for="departmentAdminUpdate">Bölüm Müdürü</label>
                                <input type="text" class="form-control" id="departmentAdminUpdate" value="{{=  departmentAdmin }}" maxlength="50" >
                            </div>

                            <div class="form-group update-form">
                                <label for="RoleUpdate">Rolü</label>
                                <select id="RoleUpdate" class="form-control">
                                    <option value="1">Kullanıcı</option>
                                    <option value="2">Admin</option>
                                </select>
                                <input type="hidden" value="{{=  userRole.id }}" id="roleIdUpdate" />
                            </div>



                            <div id="infoUpdate" style="border: 1px dashed white;  width: 100%; display: none;  padding: 10px; margin-top: 10px; margin-bottom: 10px; color: white;">

                                <input type="radio" name="typeUpdate" class="h" style="width: auto;" value="Haftalık" checked /> Haftalık <input type="radio" name="typeUpdate" class="a" style="width: auto;" value="Aylık" /> Aylık
                                <br>

                                <div class="form-group">
                                    <select id="dayUpdate" class="form-control">
                                        <option value="Pazartesi">Pazartesi</option>
                                        <option value="Salı">Salı</option>
                                        <option value="Çarşamba">Çarşamba</option>
                                        <option value="Perşembe">Perşembe</option>
                                        <option value="Cuma">Cuma</option>
                                        <option value="Cumartesi">Cumartesi</option>
                                        <option value="Pazar">Pazar</option>
                                    </select>
                                    <input type="hidden" value="{{=  notification }}" id="notificationUpdate" />
                                </div>



                            </div>
                            <input type="hidden" value="{{=  id }}" id="id" />
                        </script>
                    </div>
                <input type="checkbox" data-toggle="toggle" data-style="ios" name="chcInfo" id="chcInfoUpdate"  onchange="checkUpdate()"  style="width: auto;"  /><br><br>

                <button type="submit" class="btn btn-default" id="userUpdateButton" style="margin-bottom: 20px;" onclick="mailControlUpdate($('#id').val())">Kaydet</button>

             <input type="hidden" value="<%= mail %>" id="mailCookieUpdate" />
            </div>
        </div>
    </div>

    <script>
        function checkUpdate() {
            if ($('#chcInfoUpdate').is(":checked"))
                $("#infoUpdate").show();
            else
                $("#infoUpdate").hide();
        }
    </script>

    <script src="resources/js/Update/LoginUserUpdate.js"></script>