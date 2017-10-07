
package com.toyota.esy.dao;

import com.toyota.esy.model.Traveldetails;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.util.List;


@Repository
public class TravelDAO extends GenericDAO<Traveldetails> {

    public TravelDAO() {
        super(Traveldetails.class);
    }


    public List<Traveldetails> findUnDeleted(java.sql.Date before) {
        try {

            String query = "SELECT s FROM Traveldetails as s WHERE s.isDeleted=:isDeleted AND s.travelStartDate>=:before";

            query += " order by s.id";

            Query result = entityManager.createQuery(query).setParameter("isDeleted", isDeleted).setParameter("before", before);


            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    SQL
    UPDATE Customers
    SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
    WHERE CustomerID = 1;
    JPQL
    UPDATE Publisher pub SET pub.status = 'outstanding'
        WHERE pub.revenue < 1000000 AND 20 > (SELECT COUNT(mag) FROM pub.magazines mag)
    * */
    public Boolean travelDeleted(int id) {
        try {
            String hsql = "UPDATE Traveldetails s SET s.isDeleted=:isDeleted  WHERE s.id=:id ";
            Query query = entityManager.createQuery(hsql).setParameter("id", id).setParameter("isDeleted", isDeleted);
            int result = query.executeUpdate();
            entityManager.clear();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Admin ve user bu metodu ortak kullanıyor gelen parametreye göre listeleme yapılıyor
     **/
    public List<Traveldetails> searchByUserIdForAdmin(int id, java.sql.Date before, java.sql.Date travelStartDate, java.sql.Date travelFinishDate) {
        try {
            String query;
            Query result;
            if (travelStartDate == null && travelFinishDate == null) {
                query = "SELECT s FROM Traveldetails as s WHERE s.user.id=:id AND s.isDeleted=:isDeleted AND s.travelStartDate>=:before";
                query += " order by s.id";//Sıralama için yazıyoruz id ye göre sıralanıyor
                result = entityManager.createQuery(query).setParameter("id", id).setParameter("isDeleted", isDeleted).setParameter("before", before);
            } else {
                query = "SELECT s FROM Traveldetails as s WHERE s.isDeleted=:isDeleted AND s.travelStartDate >=:travelStartDate AND s.travelStartDate <=:travelFinishDate AND s.travelFinishDate<=:travelFinishDate AND s.user.id=:id ";
                query += " order by s.id";
                result = entityManager.createQuery(query).setParameter("travelFinishDate", travelFinishDate, TemporalType.DATE)
                        .setParameter("isDeleted", isDeleted).setParameter("travelStartDate", travelStartDate, TemporalType.DATE)
                        .setParameter("id", id);

            }
            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Traveldetails> searchByUserId(int id, java.sql.Date before) {
        try {
            String query;
            Query result;

            query = "SELECT s FROM Traveldetails as s WHERE s.user.id=:id AND s.isDeleted=:isDeleted AND s.travelStartDate>=:before";
            query += " order by s.id";//Sıralama için yazıyoruz id ye göre sıralanıyor
            result = entityManager.createQuery(query).setParameter("id", id).setParameter("isDeleted", isDeleted).setParameter("before", before);

            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Traveldetails> searchByUserIdForUser(int id, java.sql.Date before) {
        try {
            String query = "SELECT s FROM Traveldetails as s WHERE user.id=:id AND s.isDeleted=:isDeleted AND s.travelStartDate>=:before";
            query += " order by s.id";
            Query result = entityManager.createQuery(query).setParameter("id", id).setParameter("before", before).setParameter("isDeleted", isDeleted);
            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Traveldetails> searchByDateForAdmin(java.sql.Date travelStartDate, java.sql.Date travelFinishDate, int id) {
        try {
            /*if (travelStartDate.getTime() > travelFinishDate.getTime())
                return null;*/
            String query;
            Query result;
            if (id == 0) {
                query = "SELECT s FROM Traveldetails as s WHERE s.isDeleted=:isDeleted AND s.travelStartDate >=:travelStartDate AND s.travelStartDate <=:travelFinishDate AND s.travelFinishDate<=:travelFinishDate ";
                query += " order by s.id";
                result = entityManager.createQuery(query).setParameter("travelFinishDate", travelFinishDate, TemporalType.DATE)
                        .setParameter("isDeleted", isDeleted).setParameter("travelStartDate", travelStartDate, TemporalType.DATE);
            } else {
                query = "SELECT s FROM Traveldetails as s WHERE s.isDeleted=:isDeleted AND s.travelStartDate >=:travelStartDate AND s.travelStartDate <=:travelFinishDate AND s.travelFinishDate<=:travelFinishDate AND s.user.id=:id ";
                query += " order by s.id";
                result = entityManager.createQuery(query).setParameter("travelFinishDate", travelFinishDate, TemporalType.DATE)
                        .setParameter("isDeleted", isDeleted).setParameter("travelStartDate", travelStartDate, TemporalType.DATE)
                        .setParameter("id", id);
            }
            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Traveldetails> searchUserByDateForAdmin(Date travelStartDate, Date travelFinishDate) {
        try {
            String query;
            Query result;

            query = "SELECT s FROM Traveldetails as s WHERE s.isDeleted=:isDeleted AND s.travelStartDate >=:travelStartDate AND s.travelStartDate <=:travelFinishDate AND s.travelFinishDate<=:travelFinishDate ";
            query += " order by s.id";
            result = entityManager.createQuery(query).setParameter("travelFinishDate", travelFinishDate, TemporalType.DATE)
                    .setParameter("isDeleted", isDeleted).setParameter("travelStartDate", travelStartDate, TemporalType.DATE);
            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Traveldetails> searchByDateforuser(java.sql.Date travelStartDate, java.sql.Date travelFinishDate, int id) {
        try {
            if (travelStartDate.getTime() > travelFinishDate.getTime())
                return null;
            String query = "SELECT s FROM Traveldetails as s WHERE s.isDeleted=:isDeleted AND s.travelStartDate >=:travelStartDate " +
                    "AND s.travelStartDate <=:travelFinishDate AND s.travelFinishDate<=:travelFinishDate AND s.user.id=:id";
            query += " order by s.id";

            Query result = entityManager.createQuery(query).setParameter("travelFinishDate", travelFinishDate, TemporalType.DATE)
                    .setParameter("isDeleted", isDeleted).setParameter("travelStartDate", travelStartDate, TemporalType.DATE)
                    .setParameter("id", id);
            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Seyahati olan kullanıcıların mail adreslerini dönen sorgu
     * silinme tarih ve zamanında gitmesi sağlandı
     * Seyahat hatırlatmak için
     */
    public List<Traveldetails> userMail(String notification, java.sql.Date time) {
        try {
            //Kullanıcı silindi ise seyahat de silinmiş oluyor en sondaki isDeleteda gerek yok
            String query = "SELECT s FROM Traveldetails AS s WHERE s.user.isDeleted=:isDeleted AND s.travelStartDate>=:time AND s.user.notification=:notification " +
                    "AND s.isDeleted=:isDeleted ";
            query += " order by s.id";
            Query result = entityManager.createQuery(query).setParameter("isDeleted", isDeleted)
                    .setParameter("notification", notification).setParameter("time", time, TemporalType.DATE);

            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}