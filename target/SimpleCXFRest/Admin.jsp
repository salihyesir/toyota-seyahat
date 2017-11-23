
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

    <div style="margin-bottom: 30px;">

        <select class="form-control drop" onchange="myFunction($('#ds').val(),$('#de').val(), this.value)"  style="width: 200px; float: left; margin-right: 10px;">

        </select>
        <a href="Users.jsp"> <button class="glyphicon glyphicon-user myButton"> </button></a>

    </div>

    <div style="float: left;">
        <p class="datepairExample" style="float: left;">
            <input type="text" id="ds" class="date start" />
            -
            <input type="text" id="de" class="date end" />
        </p>
        <button class="glyphicon glyphicon-search myButton" onclick="searchFunction($('#ds').val(), $('#de').val(), $('.drop').val())"  style="font-size: 18px;"></button>
    </div>

    <div style="text-align: right;"> <a href="AdminTravelAdd.jsp"><button class="glyphicon glyphicon-plus myButton"> </button></a> </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Bolumu</th>
            <th>Müdürü</th>
            <th>Seyahat Eden</th>
            <th>Seyahat Baş. ve Bitiş Tar.</th>
            <th>Seyahat Yeri</th>
            <th>Görevi</th>
            <th>Maliyeti</th>
            <th>Proje Kodu</th>
            <th>İşlemler</th>
        </tr>
        </thead>
        <tbody id="table-body">
        <div>
        <script type="template/html" id="TravelDetailsViewTemplate">
            <td> <span> {{=  user.userDepartment }}</span>  </td>
            <td> <span> {{=  user.departmentAdmin }}</span>  </td>



            <td>
                <input type="hidden" value="{{=  user.id }}" class="userId{{=  id }}" />
                <input type="hidden" value="{{=  user.userName }}" class="uName{{=  id }}" />
            <span class="sp{{=  id }}"> {{=  user.userName }}</span>
                <select class="drop tx{{=  id }} userName{{=  id }} form-control" style="display: none;">

                </select>

            </td>


            <td class="datepairExample">
                <span class="sp{{=  id }}"> {{=  travelStartDate  }}</span>
                <input type="text" class="tx{{=  id }} travelStartDate{{=  id }} date start" value="{{=  travelStartDate  }}" style="display: none; float: left; width: 45%;" /> -
                <input type="hidden" value="{{=  travelStartDate  }}" class="travelStartDateBackUp{{=  id }}" />
                <span class="sp{{=  id }}"> {{=  travelFinishDate  }}</span>
                <input type="text" class="tx{{=  id }} travelFinishDate{{=  id }} date end" value="{{=  travelFinishDate  }}" style="display: none; width: 45%;" />
                <input type="hidden" value="{{=  travelFinishDate  }}" class="travelFinishDateBackUp{{=  id }}" />
            </td>


            <td> <span class="sp{{=  id }}"> {{=  travelLocation }}</span> <input type="text" class="tx{{=  id }} travelLocation{{=  id }}" value="{{=  travelLocation }}" style="display: none;" maxlength="30"  />  </td>
            <input type="hidden" value="{{=  travelLocation  }}" class="travelLocationBackUp{{=  id }}" />
            <td> <span class="sp{{=  id }}"> {{=  travelMission }}</span> <input type="text" class="tx{{=  id }} travelMission{{=  id }}" value="{{=  travelMission }}" style="display: none;" maxlength="20"  />  </td>
            <input type="hidden" value="{{=  travelMission  }}" class="travelMissionBackUp{{=  id }}" />
            <td> <span class="sp{{=  id }}"> {{=  estimatedCost }} <span style="color: #cc0000;">&#8378;</span></span> <input type="text" class="tx{{=  id }} estimatedCost{{=  id }}" value="{{=  estimatedCost }}" style="display: none;"maxlength="10"  />  </td>
            <input type="hidden" value="{{=  estimatedCost  }}" class="estimatedCostBackUp{{=  id }}" />
            <td> <span class="sp{{=  id }}"> {{=  projectCode }}</span> <input type="text" class="tx{{=  id }} projectCode{{=  id }}" value="{{=  projectCode }}" style="display: none;" maxlength="5"  />  </td>
            <input type="hidden" value="{{=  projectCode  }}" class="projectCodeBackUp{{=  id }}" />
            <td>

                <button value="{{=  id }}"  class="edit{{=  id }} glyphicon glyphicon-edit editButton" onclick="editMod(this.value)" data-toggle="tooltip" data-placement="top" title="Düzenle"></button>
                <button value="{{=  id }}"  class="save{{=  id }} glyphicon glyphicon-ok saveButton" style="display: none;" onclick="saveMod(this.value, false, $('.userId' + this.value).val(), $('.drop').val())" data-toggle="tooltip" data-placement="top" title="Kaydet"></button>
                <button value="{{=  id }}"  class="delete{{=  id }} glyphicon glyphicon-trash deleteButton" onclick="idSet(this.value)"  data-toggle="modal" data-target=".deleteModal" data-toggle="tooltip" data-placement="top" title="Sil"></button>
                <button value="{{=  id }}" class="cancel{{=  id }} glyphicon glyphicon-remove cancelButton" onclick="cancelMod(this.value)" style="display: none;" data-toggle="tooltip" data-placement="top" title="İptal"></button>
            </td>
        </script>
        </div>
        </tbody>
    </table>
    <a href="http://localhost:8080/rest/export" style="color: white;" id="href"><img src="resources/img/excel.png" width="25"  height="25"/> Excel Formatında Al</a>



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
                    <button class="btn btn-primary" id="idSet" data-dismiss="modal" onclick="saveMod(this.value, true, $('.userId' + this.value).val(), $('.drop').val(), 'delete')">Sil</button>
                </div>
            </div>
        </div>
    </div>
    </div>





<script src="resources/js/AdminView.js"></script>
<script src="resources/js/DropdownView.js"></script>

<script>

    function idSet(id) {
        $('#idSet').val(id);
        $(".modal-body").html("<p>" + $('.uName' + id).val() + " kullanıcısına ait seyahat kaydını silmek istediğinize emin misiniz?</p>");
    }
    myFunction('', '', 0);
    setTimeout(function(){
        dropbox();
    }, 500);

    setTimeout(function(){
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
    }, 500);

function dateTime() {
    $('.datepairExample .date').datepicker({
        'format': 'yyyy-m-d',
        'autoclose': true
    });

    $('.datepairExample').datepair();
}

    setTimeout(function(){
        dateTime();
    }, 250);

</script>

    <script src="resources/js/Update/AdminUpdate.js"></script>

</body>
</html>
