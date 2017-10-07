/**
 * Created by Emre-PC on 26.4.2017.
 */
function mailControlUpdate(id) {
    if($('#userMailUpdate').val() == $('#mailCookieUpdate').val())
    {
        saveUserUpdate(id);
    }
    else
    {
        cont($('#userMailUpdate').val(), id)
    }
}

function saveUserUpdate(n) {
    var logic = true;
    var r = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    if($('#userMailUpdate').val().match(r) == null || $('#userMailUpdate').val() == '')
    {
        alert("Geçersiz E-Posta Adresi!");
        $('#userMailUpdate').css("border-color", "red");
        logic=false;
    }
    if($('#userUpdate').val() == '' || $('#registerUpdate').val() == '' || $('#userMailUpdate').val() == '' || $('#passUpdate').val() == '' || $('#departmentUpdate').val() == '' || $('#departmentAdminUpdate').val() == '')
    {
        alert("Geçersiz Alanları Düzeltiniz!");
        $("#updateMod input").each(function() {
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
            userDepartment: '',
            departmentAdmin: '',
            userRole: {
                id: 1,
                roleName: 'Admin'
            },
            notification: '',
            userRegister: 0,
            userPassword:'',
            userMail:''
        }
    });

    var savemodel = new saveModel({id: n});

    if ($('#chcInfoUpdate').is(":checked"))
    {
        var notification = $("input[name=typeUpdate]:checked").val()+ ', ' + $('#dayUpdate').val();
    }
    else
    {
        var notification = 'Bildirim Yok';
    }


    savemodel.save({
        userName: $('#userUpdate').val(),
        userDepartment: $('#departmentUpdate').val(),
        departmentAdmin: $('#departmentAdminUpdate').val(),
        userRole: {
            id: $('#RoleUpdate').val()
        },
        notification: notification,
        userRegister: $('#registerUpdate').val(),
        userPassword: $('#passUpdate').val(),
        userMail: $('#userMailUpdate').val()
    });
    $('#userUpdateButton').attr('data-dismiss', 'modal');
}