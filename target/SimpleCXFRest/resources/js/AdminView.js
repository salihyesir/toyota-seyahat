/**
 * Created by Emre-PC on 9.4.2017.
 */
function myFunction(ds, de, userId) {
    var m='', download='';

    if(ds == '' && de == '' && userId == 0)
    {
        m = 'get';
    }
    else if(ds == '' && de == '' && userId != 0)
    {
        m = "search/" + userId;
    }
    else if(ds != '' && de != '' && userId != 0)
    {
        m = "search/" + ds + "/" + de  + "/" + userId;

    }
    else if (ds != '' && de != '' && userId == 0)
        m= "searchbydateforadmin/" + ds+ "/"+ de +"/"+ userId;

    if(ds == '' && de == '')
    {
        download = userId;
    }
    else if(ds != '' && de != '' && userId == 0)
    {
        download = ds + "/" + de;
    }
    else if(ds != '' && de != '' && userId != 0)
    {
        download = userId + "/"+ ds + "/" + de;
    }




    _.templateSettings = {
        evaluate: /\{\{(.+?)\}\}/g,
        interpolate: /\{\{=(.+?)\}\}/g,
        escape: /\{\{-(.+?)\}\}/g
    };

    var Surfboard = Backbone.Model.extend({
        urlRoot: "http://localhost:8080/rest/travel/" + m,
        defaults: {
            id: '',
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
                userId: 0
            }
        }
    });

    var SurfboardsCollection = Backbone.Collection.extend({
        url: "http://localhost:8080/rest/travel/" + m,
        model: Surfboard
    });


    var Surfboards = new SurfboardsCollection();


    $(document).ready(function () {
        Surfboards.add(JSON.stringify(Surfboards));

        Surfboards.fetch({
            success: function () {
                app.render();
                $("#href").attr("href", "http://localhost:8080/rest/export/" + download);
            }
        });
    });

    var SurfboardsView = Backbone.View.extend({

        el: '#table-body',

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
        tagName: 'tr',

        template: _.template($('#TravelDetailsViewTemplate').html()),

        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }

    });


    var app = new SurfboardsView;
    setTimeout(function(){
        dropbox();
    }, 250);

    setTimeout(function(){
        $('.drop').val(userId);
    }, 300);



    setTimeout(function(){
        dateTime();
    }, 250);
}




function searchFunction(ds, de, userId) {
    var download='';
    if(ds == '' && de == '')
    {
        download = userId;
    }
    else if(ds != '' && de != '' && userId == 0)
    {
        download = ds + "/" + de;
    }
    else if(ds != '' && de != '' && userId != 0)
    {
        download = userId + "/"+ ds + "/" + de;
    }

    if(ds=='')
    {
        alert("Seyahat Başlangıç Tarihini Giriniz!");
    }else if(de=='')
    {
        alert("Seyahat Bitiş Tarihini Giriniz'");
    }
    else
    {



        _.templateSettings = {
            evaluate: /\{\{(.+?)\}\}/g,
            interpolate: /\{\{=(.+?)\}\}/g,
            escape: /\{\{-(.+?)\}\}/g
        };

        var Surfboard = Backbone.Model.extend({
            urlRoot: "http://localhost:8080/rest/travel/searchbydateforadmin/" + ds + "/" + de  + "/" + userId,
            defaults: {
                id: '',
                userId: 0,
                travelLocation: '',
                travelMission: '',
                projectCode: '',
                travelStartDate: new Date().setDate(3),
                travelFinishDate: new Date().setDate(4),
                estimatedCost: 0,
                user: {
                    userName: '',
                    userDepartment: '',
                    departmentAdmin: ''
                }
            }
        });

        var SurfboardsCollection = Backbone.Collection.extend({
            url: "http://localhost:8080/rest/travel/searchbydateforadmin/" + ds + "/" + de  + "/" + userId,
            model: Surfboard
        });


        var Surfboards = new SurfboardsCollection();


        $(document).ready(function () {
            Surfboards.add(JSON.stringify(Surfboards));

            Surfboards.fetch({
                success: function () {
                    app.render();
                    $("#href").attr("href", "http://localhost:8080/rest/export/" + download);
                }
            });
        });

        var SurfboardsView = Backbone.View.extend({

            el: '#table-body',

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
            tagName: 'tr',

            template: _.template($('#TravelDetailsViewTemplate').html()),

            render: function () {
                this.$el.html(this.template(this.model.toJSON()));
                return this;
            }

        });


        var app = new SurfboardsView;
    }

    setTimeout(function(){
        dropbox();
    }, 250);

    setTimeout(function(){
        $('.drop').val(userId);
    }, 300);


    setTimeout(function(){
        dateTime();
    }, 250);
}
