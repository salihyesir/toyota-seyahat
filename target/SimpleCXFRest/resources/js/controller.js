/**
 * Created by Emre-PC on 22.4.2017.
 */
function cont(n, m, p) {


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

    var control = true;
    dropcoll.fetch({
        success: function (collection) {
            collection.each(function (model) {
                var a = model.toJSON();
                if (n == a.userMail){
                    alert('Bu E-Posta adresi sistemde mevcut!');
                    control = false;
                    return
                }
            })
            if(control==true && m == null && p == null)
            {
                userSaveFunction();
            }
            else if(control == true && m != null && p == null)
            {
                saveUserUpdate(m);
            }
            else if(control == true && m != null && p != null)
            {
                saveModForUsers(m, p)
            }
        }
    });


}
