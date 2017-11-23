/**
 * Created by Emre-PC on 9.4.2017.
 */
/**
 * Created by Emre-PC on 9.4.2017.
 */

function dropbox() {


_.templateSettings = {
    evaluate:    /\{\{(.+?)\}\}/g,
    interpolate: /\{\{=(.+?)\}\}/g,
    escape:      /\{\{-(.+?)\}\}/g
};

var dropdownModel = Backbone.Model.extend({
    urlRoot: "http://localhost:8080/rest/users/all",
    defaults: {
        id: 0,
        userName: 'Kullanıcı Seçiniz'
    }
});

var dropdownCollection = Backbone.Collection.extend({
    url: "http://localhost:8080/rest/users/all",
    model: dropdownModel
});


var dropcoll = new dropdownCollection();




$(document).ready(function() {
    dropcoll.add(JSON.stringify(dropcoll));

    dropcoll.fetch({
        success: function(){
            dropapp.render();
        }
    });
});

var dropsView = Backbone.View.extend({

    el: '.drop',

    initialize: function() {
        this.render();
    },

    render: function() {
        this.$el.html('<option value="0">Kullanıcı Seçiniz</option>');

        dropcoll.each(function(model) {
            var drop = new dropView({
                model: model,
                attributes : {
                    value: model.id
                },
            });

            this.$el.append(drop.render().el);
        }.bind(this));

        return this;
    }

});


var dropView = Backbone.View.extend({
    tagName: 'option',

    template: _.template('{{=  userName }}'),

    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }

});


var dropapp = new dropsView;
}