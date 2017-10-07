package com.toyota.esy.resource;

import com.toyota.esy.dto.UsersDTO;
import com.toyota.esy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/users")
public class UserResource {

    /**
     * @AutoWire tanımlı ve gerekli olan sınıfdan bağımlılığı otomatik yükleyebilen bir notasyondur.
     * */
    @Autowired
    private UsersService usersService;


    /**
     * User kaydetme işlemi (Test edildi)
     * */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public UsersDTO save(UsersDTO dto) {
        return usersService.save(dto);
    }

    /**
     * User güncelleme işlemi (Test edildi)
     * */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public UsersDTO edit(@PathParam("id") int id, UsersDTO dto) {
        return usersService.edit(id, dto);
    }

    /**
     * Login olan kullanıcının bilgileri getirilir. (Test edildi)
     * */
    @GET
    @Path("/login/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")//
    public UsersDTO userInformation(@PathParam("id") int id){
        UsersDTO user;
        user=usersService.loginUserInformation(id);
        return user;
    }
    /**
     * Admin için tüm Kullanıcılar(Adminler dahil) listelenir.
     * Silinen kullanıcıların gelmemesini istediğimiz için findAll metodu kullanılmadı.
     * **/
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")//Çıktı olarak
    public List<UsersDTO> getAll() {

        return usersService.getAll();
    }

    /**
     * Gelen id ye göre kullanıcı getirilir.
     *
     * */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersDTO get(@PathParam("id") int id) {
        return usersService.get(id);
    }

}
