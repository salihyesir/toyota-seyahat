/**
 * Created by Emre-PC on 26.4.2017.
 */

function userSaveFunction(){

    var logic = true;
    var r = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    if($('#email').val().match(r) == null || $('#email').val() == '')
    {
        alert("Geçersiz E-Posta Adresi!");
        $('#email').css("border-color", "red");
        logic=false;
    }
    if($('#user').val() == '' || $('#register').val() == '' || $('#pass').val() == '' || $('#department').val() == '' || $('#departmentAdmin').val() == '')
    {
        alert("Geçersiz Alanları Düzeltiniz!");
        $("input").each(function() {
            var element = $(this);
            if (element.val() == "") {
                element.css("border-color", "red");
            }
        });
        return;
    }
    if(logic==false){return;}
    var saveModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/rest/users',
        defaults: {
            userName: '',
            userRegister: 0,
            userPassword: '',
            userDepartment: '',
            departmentAdmin: '',
            userRole: {
                roleId: 1
            },
            notification: '',
            userMail: '',
            isDeleted: false

        }
    });
    if ($('#chcInfo').is(":checked"))
    {
        var notification = $("input[name=type]:checked").val()+ ', ' + $('#day').val();
    }
    else
    {
        var notification = 'Bildirim Yok';
    }
    var savemodel = new saveModel();
    savemodel.toJSON();
    savemodel.save({
        userName: $('#user').val(),
        userRegister: $('#register').val(),
        userPassword: $('#pass').val(),
        userDepartment: $('#department').val(),
        departmentAdmin: $('#departmentAdmin').val(),
        userRole: {
            id: $('#Role').val()
        },
        notification: notification ,
        userMail: $('#email').val(),
        isDeleted: false
    });
    window.location = "Users.jsp";
}
