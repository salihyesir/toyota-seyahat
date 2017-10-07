function userView(n) {


_.templateSettings = {
    evaluate:    /\{\{(.+?)\}\}/g,
    interpolate: /\{\{=(.+?)\}\}/g,
    escape:      /\{\{-(.+?)\}\}/g
};

var Surfboard = Backbone.Model.extend({
    urlRoot: "http://localhost:8080/rest/travel/user/" + n,
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
            departmentAdmin: ''
        }
    }
});

var SurfboardsCollection = Backbone.Collection.extend({
    url: "http://localhost:8080/rest/travel/user/" + n,
    model: Surfboard
});


var Surfboards = new SurfboardsCollection();



$(document).ready(function() {
    Surfboards.add(JSON.stringify(Surfboards));

    Surfboards.fetch({
        success: function(){
            app.render();
        }
    });
});

var SurfboardsView = Backbone.View.extend({

    el: '#table-body',

    initialize: function() {
        this.render();
    },

    render: function() {
        this.$el.html('');

        Surfboards.each(function(model) {
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

    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }

});


var app = new SurfboardsView;



}



function searchFunction(ds, de, id) {


    if (ds == '') {
        alert("Seyahat Başlangıç Tarihini Giriniz!");
    } else if (de == '') {
        alert("Seyahat Bitiş Tarihini Giriniz'");
    }
    else {

        _.templateSettings = {
            evaluate: /\{\{(.+?)\}\}/g,
            interpolate: /\{\{=(.+?)\}\}/g,
            escape: /\{\{-(.+?)\}\}/g
        };

        var Surfboard = Backbone.Model.extend({
            urlRoot: "http://localhost:8080/rest/travel/searchbydateforuser/" + ds + "/" + de + "/" + id,
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
                    departmentAdmin: ''
                }
            }
        });

        var SurfboardsCollection = Backbone.Collection.extend({
            url: "http://localhost:8080/rest/travel/searchbydateforuser/" + ds + "/" + de + "/" + id,
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
        dateTime();
    }, 1000);
}