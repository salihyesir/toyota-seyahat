/**
 * Created by Emre-PC on 26.4.2017.
 */


function editMod(n) {
    $('.userName' +n).val($('.userId' + n).val());
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
    $(".travelStartDate" + n).val($(".travelStartDateBackUp" + n).val());
    $(".travelFinishDate" + n).val($(".travelFinishDateBackUp" + n).val());
    $(".travelLocation" + n).val($(".travelLocationBackUp" + n).val());
    $(".travelMission" + n).val($(".travelMissionBackUp" + n).val());
    $(".estimatedCost" + n).val($(".estimatedCostBackUp" + n).val());
    $(".projectCode" + n).val($(".projectCodeBackUp" + n).val());

    $('.userName' +n).css("border-color", "");
    $("td input").each(function() {
        var element = $(this);
        element.css("border-color", "");
    });
}

function saveMod(n, isDeleted, userId, dropVal, optional) {
    if(optional != 'delete')
    {
        var logic = true;
        if($('.userName' +n).val() == 0)
        {
            alert("Kullanıcı seçiniz!");
            $('.userName' +n).css("border-color", "red");
            logic=false;
        }
        if($('.travelStartDate' +n).val() == '' || $('.travelFinishDate' +n).val() == '' || $('.travelLocation' +n).val() == '' || $('.travelMission' +n).val() =='' || $('.estimatedCost' +n).val() == '' || $('.projectCode' +n).val() == '')
        {
            alert("Geçersiz alanları düzeltiniz!");
            $("td input").each(function() {
                var element = $(this);
                if (element.val() == "") {
                    element.css("border-color", "red");
                }
            });
            return;
        }
        if(logic==false){return;}
    }
    $('.tx' + n).css('display', 'none');
    $('.sp' + n).css('display', '');
    $(".edit" + n).show();
    $(".save" + n).hide();
    $(".delete" +n).show();

    var saveModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/rest/travel',
        defaults: {
            travelLocation: '',
            travelMission: '',
            projectCode: '',
            travelStartDate: new Date().setDate(3),
            travelFinishDate: new Date().setDate(4),
            estimatedCost: 0,
            user: {
                userName: '',
                userDepartment: '',
                departmentAdmin: '',
                id: 0
            },
            isDeleted: false
        }
    });

    var savemodel = new saveModel({id: n});

    if($('.userName' +n).val() == 0)
    {
        var a = $('.userId' + n).val();
    }
    else
    {
        var a = $('.userName' +n).val();
    }

    savemodel.save({
        travelLocation: $('.travelLocation' + n).val(),
        travelMission: $('.travelMission' + n).val(),
        projectCode: $('.projectCode' + n).val(),
        travelStartDate: $('.travelStartDate' + n).val(),
        travelFinishDate: $('.travelFinishDate' + n).val(),
        estimatedCost: $('.estimatedCost' + n).val(),
        user: {
            id: a
        },
        isDeleted: isDeleted
    });
    if($('#ds').val() != '' && $('#de').val() != '') // sadece tarih seçildi / hem tarih hem kullanıcı seçildi.
    {
        setTimeout(function(){
            searchFunction($('#ds').val(), $('#de').val(), $('.drop').val());
        }, 300);
    }
    else if(dropVal != 0 && $('#ds').val() == '' && $('#de').val() == '') // sadece kullanıcı seçildi.
    {
        setTimeout(function(){
            myFunction('', '', userId);
        }, 300);
    }
    else if(dropVal == 0 && $('#ds').val() == '' && $('#de').val() == '') // hiç birşey seçilmedi.
    {
        setTimeout(function(){
            myFunction('', '', 0);
        }, 300);
    }


}