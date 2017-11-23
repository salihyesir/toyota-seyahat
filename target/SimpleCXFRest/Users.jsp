
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

    String mail="";
    Cookie[] cookies = request.getCookies();
    if(cookies !=null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("mail")) mail = cookie.getValue();
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

    <div style="text-align: right;"> <a href="UserAdd.jsp"><button class="glyphicon glyphicon-plus myButton"> </button></a> </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Kullanıcı Adı</th>
            <th>E-Posta</th>
            <th>Bölümü</th>
            <th>Bölüm Müdürü</th>
            <th>Rolü</th>
            <th>Seyahat Bilgilendirme</th>
            <th>İşlemler</th>
        </tr>
        </thead>
        <tbody id="table-body">
        <div>
        <script type="template/html" id="UsersDetailsViewTemplate">

            <input type="hidden" class="userRegister{{=  id }}"  value="{{=  userRegister }}" /><input type="hidden" class="userPassword{{=  id }}"  value="{{=  userPassword }}" />
            <td> <span class="sp{{=  id }}"> {{=  userName }}</span> <input type="text" class="tx{{=  id }} userName{{=  id }}" value="{{=  userName }}" style="display: none;" maxlength="45"  />  </td>
            <input type="hidden" value="{{=  userName }}" class="userNameBackUp{{=  id }}" />
            <td> <span class="sp{{=  id }}"> {{=  userMail }}</span> <input type="text" class="tx{{=  id }} userMail{{=  id }}" value="{{=  userMail }}" style="display: none;" maxlength="50"  />  </td>
            <input type="hidden" value="{{=  userMail }}" class="userMailBackUp{{=  id }}" />
            <td> <span class="sp{{=  id }}"> {{=  userDepartment }}</span> <input type="text" class="tx{{=  id }} userDepartment{{=  id }}" value="{{=  userDepartment }}" style="display: none;" maxlength="20"  />  </td>
            <input type="hidden" value="{{=  userDepartment }}" class="userDepartmentBackUp{{=  id }}" />
            <td> <span class="sp{{=  id }}"> {{=  departmentAdmin }}</span> <input type="text" class="tx{{=  id }} departmentAdmin{{=  id }}" value="{{=  departmentAdmin }}" style="display: none;" maxlength="50"  />  </td>
            <input type="hidden" value="{{=  departmentAdmin }}" class="departmentAdminBackUp{{=  id }}" />
            <td> <span class="sp{{=  id }}"> {{=  userRole.roleName }}</span>
            <select class="form-control tx{{=  id }} roleId{{=  id }}" style="display: none;" >
                <option value="1">Kullanıcı</option>
                <option value="2">Admin</option>
            </select>
                <input type="hidden" value="{{=  userRole.id }}" class="role{{=  id }}" />
             </td>
            <td> <span class="sp{{=  id }}"> {{=  notification }}</span>

            <select class="form-control tx{{=  id }} notification{{=  id }}" style="display: none;" >
                <option value="Bildirim Yok">Bildirim Yok</option>
                <option value="Haftalık, Pazartesi">Haftalık Pazartesi</option>
                <option value="Haftalık, Salı">Haftalık Salı</option>
                <option value="Haftalık, Çarşamba">Haftalık Çarşamba</option>
                <option value="Haftalık, Perşembe">Haftalık Perşembe</option>
                <option value="Haftalık, Cuma">Haftalık Cuma</option>
                <option value="Haftalık, Cumartesi">Haftalık Cumartesi</option>
                <option value="Haftalık, Pazar">Haftalık Pazar</option>
                <option value="Aylık, Pazartesi">Aylık Pazartesi</option>
                <option value="Aylık, Salı">Aylık Salı</option>
                <option value="Aylık, Çarşamba">Aylık Çarşamba</option>
                <option value="Aylık, Perşembe">Aylık Perşembe</option>
                <option value="Aylık, Cuma">Aylık Cuma</option>
                <option value="Aylık, Cumartesi">Aylık Cumartesi</option>
                <option value="Aylık, Pazar">Aylık Pazar</option>
            </select>
                <input type="hidden" value="{{=  notification }}" class="userNotification{{=  id }}" />
            </td>
            <td>
                <button value="{{=  id }}"  class="edit{{=  id }} glyphicon glyphicon-edit editButton" onclick="editMod(this.value)" data-toggle="tooltip" data-placement="top" title="Düzenle"></button>
                <button value="{{=  id }}"  class="save{{=  id }} glyphicon glyphicon-ok saveButton" style="display: none;" onclick="mailControl(this.value, false)" data-toggle="tooltip" data-placement="top" title="Kaydet"></button>
                <button value="{{=  id }}"  class="delete{{=  id }} glyphicon glyphicon-trash deleteButton" onclick="idSet(this.value)" data-toggle="modal" data-target=".deleteModal" data-toggle="tooltip" data-placement="top" title="Sil"></button>
                <button value="{{=  id }}" class="cancel{{=  id }} glyphicon glyphicon-remove cancelButton" onclick="cancelMod(this.value)" style="display: none;" data-toggle="tooltip" data-placement="top" title="İptal"></button>
            </td>
        </script>
            </div>
        </tbody>
    </table>
    <input type="hidden" value="<%= mail %>" id="mailCookie" />

    <div class="modal fade deleteModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Silme İşlemi</h4>
                </div>
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">İptal</button>
                    <button class="btn btn-primary" id="idSet" data-dismiss="modal" onclick="saveModForUsers(this.value, true)">Sil</button>
                </div>
            </div>
        </div>
    </div>
    </div>


    </div>
    <script src="resources/js/Users.js"></script>



    <script>

        function idSet(id) {
            $('#idSet').val(id);
            $(".modal-body").html("<p>" + $('.userName' + id).val() + " kullanıcısını silmek istediğinize emin misiniz?</p>");
        }

        setTimeout(function(){
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })
        }, 500);
    </script>

    <script src="resources/js/Update/UsersUpdate.js"></script>

</body>
</html>
