/**
 * Created by Emre-PC on 12.4.2017.
 */



_.templateSettings = {
    evaluate:    /\{\{(.+?)\}\}/g,
    interpolate: /\{\{=(.+?)\}\}/g,
    escape:      /\{\{-(.+?)\}\}/g
};

var Surfboard = Backbone.Model.extend({
    urlRoot: "http://localhost:8080/rest/users/all",
    defaults: {
        userName: '',
        userDepartment: '',
        departmentAdmin: '',
        userRole: {
            roleName: ''
        },
        notification: '',
        userMail:''
    }
});

var SurfboardsCollection = Backbone.Collection.extend({
    url: "http://localhost:8080/rest/users/all",
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

    template: _.template($('#UsersDetailsViewTemplate').html()),

    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }

});


var app = new SurfboardsView;

