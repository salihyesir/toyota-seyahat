/**
 * Created by Emre-PC on 26.4.2017.
 */

function editMod(n) {
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
    $(".projectCode" + n).val($(".projectCodeBackUp" + n).val());

    $("td input").each(function() {
        var element = $(this);
        element.css("border-color", "");
    });
}

function saveMod(n, isDeleted) {

    if($('.travelStartDate' +n).val() == '' || $('.travelFinishDate' +n).val() == '' || $('.travelLocation' +n).val() == '' || $('.travelMission' +n).val() =='' || $('.projectCode' +n).val() == '')
    {
        alert("Boş alanları doldurunuz!");
        $("td input").each(function() {
            var element = $(this);
            if (element.val() == "") {
                element.css("border-color", "red");
            }
        });
        return;
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

    savemodel.save({
            travelLocation: $('.travelLocation' + n).val(),
            travelMission: $('.travelMission' + n).val(),
            projectCode: $('.projectCode' + n).val(),
            travelStartDate: $('.travelStartDate' + n).val(),
            travelFinishDate: $('.travelFinishDate' + n).val(),
            estimatedCost: $('.estimatedCost' + n).val(),
            user: {
                id: $('#userId').val()
        },
        isDeleted: isDeleted
});
    if($('#ds').val() != '' && $('#de').val() != '')
    {
        setTimeout(function(){
            searchFunction($('#ds').val(), $('#de').val(), $('#userId').val());
        }, 250);
    }
    else
    {
        setTimeout(function(){
            userView($('#userId').val())
        }, 500);
    }



}