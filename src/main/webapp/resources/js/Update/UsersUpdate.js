/**
 * Created by Emre-PC on 26.4.2017.
 */

function editMod(n) {
    $('.roleId' +n).val($('.role' + n).val());
    $('.notification' +n).val($('.userNotification' + n).val());
    $('.tx' + n).css('display', '');
    $('.sp' + n).css('display', 'none');
    $(".edit" +n).hide();
    $(".save" +n).show();
    $(".delete" +n).hide();
    $(".cancel" +n).show();
}

function cancelMod(n) {
    $('.tx' + n).css('display', 'none');
    $('.sp' + n).css('display', '');
    $(".edit" + n).show();
    $(".save" + n).hide();
    $(".delete" +n).show();
    $(".cancel" +n).hide();
    $(".userName" + n).val($(".userNameBackUp" + n).val());
    $(".userMail" + n).val($(".userMailBackUp" + n).val());
    $(".userDepartment" + n).val($(".userDepartmentBackUp" + n).val());
    $(".departmentAdmin" + n).val($(".departmentAdminBackUp" + n).val());

    $("td input").each(function() {
        var element = $(this);
        element.css("border-color", "");
    });
}


function mailControl(id, isDeleted) {
    if($('.userMail' + id).val() == $('.userMailBackUp' + id).val())
    {
        saveModForUsers(id, isDeleted);
    }
    else
    {
        cont($('.userMail' + id).val(), id, isDeleted);
    }
}

function saveModForUsers(n, isDeleted) {
    if(isDeleted == false)
    {
        var logic = true;
        var r = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
        if($('.userMail' +n).val().match(r) == null || $('.userMail' +n).val() == '')
        {
            alert("Geçersiz E-Posta Adresi!");
            $('.userMail' +n).css("border-color", "red");
            logic=false;
        }
        if($('.userName' +n).val() == '' || $('.userDepartment' +n).val() == '' || $('.departmentAdmin' +n).val() == '')
        {
            alert("Geçersiz Alanları Düzeltiniz!");
            $("td input").each(function() {
                var element = $(this);
                if (element.val() == "") {
                    element.css("border-color", "red");
                }
            });
            return;
        }
        if(logic==false){return;}

        $('.tx' + n).css('display', 'none');
        $('.sp' + n).css('display', '');
        $(".edit" +n).show();
        $(".save" +n).hide();
        $(".delete" +n).show();
    }





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
            userMail:'',
            isDeleted: false
        }
    });

    var savemodel = new saveModel({id: n});
    if(isDeleted == true)
    {
        $('.roleId' +n).val($('.role' + n).val());
        $('.notification' +n).val($('.userNotification' + n).val());
    }
    savemodel.save({
        userName: $('.userName' + n).val(),
        userDepartment: $('.userDepartment' + n).val(),
        departmentAdmin: $('.departmentAdmin' + n).val(),
        userRole: {
            id: $('.roleId' + n).val(),
        },
        notification: $('.notification' + n).val(),
        userRegister: $('.userRegister' + n).val(),
        userMail: $('.userMail' + n).val(),
        userPassword: $('.userPassword' + n).val(),
        isDeleted: isDeleted
    });
    setTimeout(function(){
        Surfboards.fetch({
            success: function(){
                app.render();
            }
        });
    }, 250);
}