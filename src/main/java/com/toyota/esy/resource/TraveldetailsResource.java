package com.toyota.esy.resource;

import com.toyota.esy.dto.TraveldetailsDTO;
import com.toyota.esy.service.TraveldetailsService;
import com.toyota.esy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Component
@Path("/travel")
public class TraveldetailsResource
{

    @Autowired
    private TraveldetailsService travelservice;

    @Autowired
    private UsersService usersService;

    /**
     * Kullanıcı için kayıt işlemi
     * */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")//Parametre olarak
    public TraveldetailsDTO save(TraveldetailsDTO dto) {
        //Seyahat icin user kayıt olmalı
        return travelservice.save(dto);
    }
    /**
     * Kullanıcın seyahat güncellemesi
     * */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8" )
    @Consumes(MediaType.APPLICATION_JSON  + ";charset=UTF-8" )
    public TraveldetailsDTO edit(@PathParam("id") int id, TraveldetailsDTO dto) {
        return travelservice.edit(id, dto);
    }


    /**
     * Admin için silinen kayıtlar getirilmez
     * */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<TraveldetailsDTO> getUnDeleted()
    {
        return travelservice.getUnDeleted();
    }


    /**
     * Seyahatın id sine göre alma
     * */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public TraveldetailsDTO get(@PathParam("id") int id) {
        return travelservice.get(id);
    }


    /**
     * Admin için yazılan bölüm Kullanıcı seçilerek kullanıcıların seyahatleri(tümü) izlenir.
     * Eğer kullanıcı seçilmezse silinmemiş kayıtlar gözükür
     **/
    @GET
    @Path("/search/{TravelStartDate}/{TravelFinishDate}/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<TraveldetailsDTO> searchUser(
            @PathParam("TravelStartDate") java.sql.Date TravelStartDate,
            @PathParam("TravelFinishDate") java.sql.Date TravelFinishDate,
            @PathParam("id") int id){
        return travelservice.searchUserByIdForAdmin(id,TravelStartDate,TravelFinishDate);
    }

    @GET
    @Path("/search/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<TraveldetailsDTO> searchUser(@PathParam("id") int id){
        return travelservice.searchUserById(id);
    }

    /**
     * User seyahetleri listelenir
     * Son iki ay
     * */
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<TraveldetailsDTO> userTravel(@PathParam("id") int id){
        return travelservice.searchUserByIdForUser(id);
    }

    /**
     * ADMİN için
     * Tarih aralığına göre arama yapmakta ve sıralamakta
     * */
    @GET
    @Path("/searchbydateforadmin/{TravelStartDate}/{TravelFinishDate}/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<TraveldetailsDTO> searchByDateForAdmin(@PathParam("TravelStartDate") java.sql.Date TravelStartDate,
                                                       @PathParam("TravelFinishDate") java.sql.Date TravelFinishDate,
                                                       @PathParam("id") int id){
        return travelservice.searchByDateForAdmin(TravelStartDate,TravelFinishDate,id);

    }

    /**
     * User için
     * Tarih aralığına göre arama yapmakta ve sıralamakta
     *
     * */
    @GET
    @Path("/searchbydateforuser/{TravelStartDate}/{TravelFinishDate}/{id}" )
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<TraveldetailsDTO> searchByDateForUser(@PathParam("TravelStartDate") java.sql.Date TravelStartDate,
                                                      @PathParam("TravelFinishDate") java.sql.Date TravelFinishDate,
                                                      @PathParam("id") int id){
        return travelservice.searchByDateForUser(TravelStartDate,TravelFinishDate,id);

    }

}
