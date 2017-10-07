package com.toyota.esy.resource;

import com.toyota.esy.service.ExportToExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Component
@Path("/export")
public class ExportToExcel {

     @Autowired
     private ExportToExcelService exportToExcelService;


     /**
      * Hiçbirşey seçilmedi
      * */
    @GET
    @Produces("application/vnd.ms-excel")
    public Response exportExcel()
    {
        return exportToExcelService.exportExcel(0,new java.sql.Date(0), new java.sql.Date(0));
    }


    /**
     * Sadece user seçildi
     * */
    @GET
    @Path("/{id}")
    @Produces("application/vnd.ms-excel")
    public Response exportExcel(@PathParam("id") int id)
    {

        return exportToExcelService.exportExcel(id,new java.sql.Date(0), new java.sql.Date(0));
    }
    /**
     * Sadece zaman aralığı belirlendi
     * */
    @GET
    @Path("/{TravelStartDate}/{TravelFinishDate}")
    @Produces("application/vnd.ms-excel")
    public Response exportExcel(@PathParam("TravelStartDate") java.sql.Date TravelStartDate,
                                @PathParam("TravelFinishDate") java.sql.Date TravelFinishDate)
    {
        return exportToExcelService.exportExcel(0,TravelStartDate,TravelFinishDate);
    }

    /**
     * Tüm herşey belirlendi
     * */
    //javax.ws.rs.core
    @GET
    @Path("/{id}/{TravelStartDate}/{TravelFinishDate}")
    @Produces("application/vnd.ms-excel")
    public Response exportExcel(@PathParam("TravelStartDate") java.sql.Date TravelStartDate,
                                @PathParam("TravelFinishDate") java.sql.Date TravelFinishDate,
                                @PathParam("id") int id)
    {
        return exportToExcelService.exportExcel(id,TravelStartDate,TravelFinishDate);
    }




}
