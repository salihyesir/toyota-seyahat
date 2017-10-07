
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


        <div class="form-group">
        <label for="drop">Kullanıcı</label>

            <select id="drop" class="drop form-control" style="width: 200px; margin-right: 10px;">

            </select>
        </div>
        <div class="form-group">
            <label for="location">Seyahat Yeri</label>
            <input type="text" class="form-control" id="location" maxlength="30" >
        </div>
        <div class="form-group">
            <label for="mission">Gidiş Amacı</label>
            <input type="text" class="form-control" id="mission" maxlength="20" >
        </div>


        <div id="datepairExample">
        <div class="form-group">
            <label>Seyahat Başlangıç Bitiş Tarihleri</label><br>
            <input type="text" id="ds" class="date start" style="color: black;" />
            <input type="text" id="de" class="date end" style="color: black;" />
        </div>
        </div>


        <div class="form-group">
            <label for="estimated">Tahmini Maliyet</label>
            <input type="text" class="form-control" id="estimated" maxlength="10" >
        </div>
        <div class="form-group">
            <label for="projectCode">Proje Kodu</label>
            <input type="text" class="form-control" id="projectCode" maxlength="5" >
        </div>
        <button  class="btn btn-default" onclick="saveFunction()">Ekle</button>

</div>



    <script src="resources/js/DropdownView.js"></script>

    <script>
        $('#datepairExample .date').datepicker({
            'format': 'yyyy-m-d',
            'autoclose': true
        });

        // initialize datepair
        $('#datepairExample').datepair();


        setTimeout(function(){
            dropbox();
        }, 500);
    </script>

    <script src="resources/js/Save/AdminTravelAdd.js"></script>


</body>
</html>
