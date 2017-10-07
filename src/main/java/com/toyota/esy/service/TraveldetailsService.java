
package com.toyota.esy.service;

import com.toyota.esy.dao.TravelDAO;
import com.toyota.esy.dao.UsersDAO;
import com.toyota.esy.dto.TraveldetailsDTO;
import com.toyota.esy.model.Traveldetails;
import com.toyota.esy.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TraveldetailsService {

    @Autowired
    private TravelDAO travelDAO;

    @Autowired
    private UsersDAO usersDAO;

    /**
     *  Seyahat kayıt edilir Kullanıcı için
     * */
    @Transactional
    public TraveldetailsDTO save(TraveldetailsDTO dto)
    {
        Traveldetails traveldetails=new Traveldetails();
        //Login olan kullanıcının seyaheti kayıt edililiyor.
        //
        Users user = usersDAO.find(dto.getUser().getId());

        try{
            traveldetails.setEstimatedCost(dto.getEstimatedCost());
            traveldetails.setIsDeleted(dto.getIsDeleted());
            traveldetails.setProjectCode(dto.getProjectCode());
            traveldetails.setTravelFinishDate(dto.getTravelFinishDate());
            traveldetails.setTravelLocation(dto.getTravelLocation());
            traveldetails.setTravelMission(dto.getTravelMission());
            traveldetails.setTravelStartDate(dto.getTravelStartDate());
            traveldetails.setUser(user);

            traveldetails = travelDAO.persist(traveldetails);
        }catch (Exception e) {
            return null;
        }
        return dto.convert(traveldetails);
    }
    /**
     *  Var olan kullanıcın seyahat güncelleme işlemi
     * */

    @Transactional
    public TraveldetailsDTO edit(int id, TraveldetailsDTO dto) {
        Traveldetails traveldetails=new Traveldetails();
        try{
            Users user = usersDAO.find(dto.getUser().getId());
            traveldetails.setId(id);
            traveldetails.setEstimatedCost(dto.getEstimatedCost());
            traveldetails.setIsDeleted(dto.getIsDeleted());
            traveldetails.setProjectCode(dto.getProjectCode());
            traveldetails.setTravelFinishDate(dto.getTravelFinishDate());
            traveldetails.setTravelLocation(dto.getTravelLocation());
            traveldetails.setTravelMission(dto.getTravelMission());
            traveldetails.setTravelStartDate(dto.getTravelStartDate());

            traveldetails.setUser(user);


            traveldetails = travelDAO.merge(traveldetails);
        }catch (Exception e) {
            return null;
        }
        return dto.convert(traveldetails);

    }

    @Transactional
    public Boolean delete(int id) {
        try {
            travelDAO.travelDeleted(id);
        } catch (Exception e) {
            return null;
        }
        return true;
    }


    /**
     *      Sadece belli bir id ye ait seyahetler listelenir
     * */
    public  TraveldetailsDTO get(int id) {
        Traveldetails traveldetails;
        try {
            traveldetails = travelDAO.find(id);
        } catch (Exception e) {
            return null;
        }
        return new TraveldetailsDTO().convert(traveldetails);
    }

    /**
     *  Silinmemişler seyahat kayıtlarını getirir. admin için olduğundan son 1 yıl gelmekte
     *  * */
    public List<TraveldetailsDTO> getUnDeleted() {

        List<TraveldetailsDTO> travelList = new ArrayList<TraveldetailsDTO>();
        try {
            Date now =new Date();

            long millis=now.getTime();//saniye cinsinden şuan

            long  year=31556926;
            long ago= (millis)-(year*1000);// saniye cinsinden 1 yıl önce
            java.sql.Date before=new java.sql.Date(ago);

            for (Traveldetails travel : travelDAO.findUnDeleted(before))
                travelList.add(new TraveldetailsDTO().convert(travel));
        } catch (Exception e) {
            return null;
        }
        return travelList;
    }
    /**
     * User için
     * */
    public List<TraveldetailsDTO> searchUserByIdForUser(int id) {
        List<TraveldetailsDTO> travelList = new ArrayList<TraveldetailsDTO>();
        try {
            Date now =new Date();
            long millis=now.getTime();//saniye cinsinden şuan
            long  month=525948766;//2 ay saniye*100
            long ago= (millis)-(month*10);// ago => 2 ay öncesi
            java.sql.Date before=new java.sql.Date(ago);//Date tipine dönüştürüldü
            for (Traveldetails travel : travelDAO.searchByUserIdForUser(id,before))
                travelList.add(new TraveldetailsDTO().convert(travel));
        } catch (Exception e) {
            return null;
        }
        return travelList;
    }

    /**
     * Admin için seçilen kullanıcın son 1 yılı
     * User için olan dao daki metot kullanıldı.
     * */
    public List<TraveldetailsDTO> searchUserByIdForAdmin(int id,java.sql.Date TravelStartDate , java.sql.Date TravelFinishDate ) {
        List<TraveldetailsDTO> travelList = new ArrayList<TraveldetailsDTO>();

        try {

            Date now =new Date();

            long millis=now.getTime();//saniye cinsinden şuan

            long  year=31556926;
            long ago= (millis)-(year*1000);// saniye cinsinden 1 yıl önce
            java.sql.Date before=new java.sql.Date(ago);

            for (Traveldetails travel : travelDAO.searchByUserIdForAdmin(id,before,TravelStartDate,TravelFinishDate))
                travelList.add(new TraveldetailsDTO().convert(travel));
        } catch (Exception e) {
            return null;
        }
        return travelList;
    }

    public List<TraveldetailsDTO> searchUserById(int id ) {
        List<TraveldetailsDTO> travelList = new ArrayList<TraveldetailsDTO>();

        try {

            Date now =new Date();

            long millis=now.getTime();//saniye cinsinden şuan

            long  year=31556926;
            long ago= (millis)-(year*1000);// saniye cinsinden 1 yıl önce
            java.sql.Date before=new java.sql.Date(ago);

            for (Traveldetails travel : travelDAO.searchByUserId(id,before))
                travelList.add(new TraveldetailsDTO().convert(travel));
        } catch (Exception e) {
            return null;
        }
        return travelList;
    }



/**
 * Admin için arama
 * */
    public List<TraveldetailsDTO> searchByDateForAdmin(java.sql.Date TravelStartDate , java.sql.Date TravelFinishDate ,int id) {
        List<TraveldetailsDTO> travelList = new ArrayList<TraveldetailsDTO>();
        try {
                for (Traveldetails travel : travelDAO.searchByDateForAdmin(TravelStartDate, TravelFinishDate,id))
                    travelList.add(new TraveldetailsDTO().convert(travel));
        } catch (Exception e) {
            return null;
        }
        return travelList;
    }

    /**
     * User için arama
     * */
    public List<TraveldetailsDTO> searchByDateForUser(java.sql.Date TravelStartDate , java.sql.Date TravelFinishDate ,int id) {

        List<TraveldetailsDTO> travelList = new ArrayList<TraveldetailsDTO>();
        try {
            for (Traveldetails travel : travelDAO.searchByDateforuser(TravelStartDate, TravelFinishDate,id))
                travelList.add(new TraveldetailsDTO().convert(travel));
        } catch (Exception e) {
            return null;
        }
        return travelList;
    }


    public List<TraveldetailsDTO> searchUserByDateForAdmin(java.sql.Date travelStartDate, java.sql.Date travelFinishDate) {
        List<TraveldetailsDTO> travelList = new ArrayList<TraveldetailsDTO>();
        try {
            for (Traveldetails travel : travelDAO.searchUserByDateForAdmin(travelStartDate, travelFinishDate))
                travelList.add(new TraveldetailsDTO().convert(travel));
        } catch (Exception e) {
            return null;
        }
        return travelList;
    }
}
