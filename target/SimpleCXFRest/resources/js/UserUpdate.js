/**
 * Created by Emre-PC on 11.4.2017.
 */
function x(n) {


    _.templateSettings = {
        evaluate: /\{\{(.+?)\}\}/g,
        interpolate: /\{\{=(.+?)\}\}/g,
        escape: /\{\{-(.+?)\}\}/g
    };

    var Surfboard = Backbone.Model.extend({
        urlRoot: "http://localhost:8080/rest/users/login/" + n,
        defaults:{
            id:0,
            userName:'',
            userRole: {
              id:1
            },
            departmentAdmin:'',
            userRegister:0,
            notification:'',
            userDepartment: '',
            userRegister: 0,
            userPassword: '',
            userMail:''
        }
    });

    var SurfboardsCollection = Backbone.Collection.extend({
        url: "http://localhost:8080/rest/users/login/" + n,
        model: Surfboard
    });


    var Surfboards = new SurfboardsCollection();


    $(document).ready(function () {
        Surfboards.add(JSON.stringify(Surfboards));

        Surfboards.fetch({
            success: function () {
                app.render();
            }
        });
    });

    var SurfboardsView = Backbone.View.extend({

        el: '#updateMod',

        initialize: function () {
            this.render();
        },

        render: function () {
            this.$el.html('');

            Surfboards.each(function (model) {
                var surfboard = new SurfboardView({
                    model: model
                });

                this.$el.append(surfboard.render().el);
            }.bind(this));

            return this;
        }

    });


    var SurfboardView = Backbone.View.extend({


        template: _.template($('#UpdateView').html()),

        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }

    });


    var app = new SurfboardsView;


    setTimeout(function(){
        $('#RoleUpdate').val($('#roleIdUpdate').val());
        if($('#notificationUpdate').val() != 'Bildirim Yok')
        {
            $( "#chcInfoUpdate" ).prop( "checked", true );
            $(".toggle").attr("class", "toggle btn ios btn-primary");
            $("#infoUpdate").show();
            var a = $('#notificationUpdate').val();
            var temp = new Array();
            temp = a.split(', ');
            if(temp[0].toString() =='Haftalık')
            {
                $(".h").attr('checked', 'checked');
            }
            else
            {
                $(".a").attr('checked', 'checked');
            }
            $("input[name=typeUpdate]:checked").val(temp[0].toString())
            $('#dayUpdate').val(temp[1].toString())
        }
    }, 250);

}