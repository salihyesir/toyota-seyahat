/**
 * Created by Emre-PC on 26.4.2017.
 */


function saveFunction(){

    if($('#drop').val() == 0)
    {
        alert("Kullanıcı Seçiniz...");
        $('#drop').css("border-color", "red");
        return;
    }

    if($('#location').val() == '' || $('#mission').val() == '' || $('#ds').val() == '' || $('#de').val() =='' || $('#estimated').val() == '' || $('#projectCode').val() == '')
    {
        alert("Boş alanları doldurunuz!");
        $("input").each(function() {
            var element = $(this);
            if (element.val() == "") {
                element.css("border-color", "red");
            }
        });
        return;
    }

    var saveModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/rest/travel',
        defaults: {
            travelLocation: '',
            travelMission: '',
            projectCode: '',
            travelStartDate: new Date().setDate(3),
            travelFinishDate: new Date().setDate(4),
            estimatedCost: 0,
            isDeleted: false,
            user: {
                id:0
            }

        }
    });

    var savemodel = new saveModel();

    savemodel.toJSON();
    savemodel.save({
        travelLocation: $('#location').val(),
        travelMission: $('#mission').val(),
        projectCode: $('#projectCode').val(),
        travelStartDate: $('#ds').val(),
        travelFinishDate: $('#de').val(),
        estimatedCost: $('#estimated').val(),
        isDeleted: false,
        user: {
            id: $('#drop').val()
        }
    })
    window.location = "Admin.jsp";
}

