package com.toyota.esy.dao;

import com.toyota.esy.model.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class UsersDAO extends GenericDAO<Users> {

    public UsersDAO() {super(Users.class);}

    //Kullanıcı login olmaya çalıştığında, sorgu yapılıyor.
    public Users loginUserInformation(String userMail, String password) {
        Users users = null;
        try {

            users = (Users) emCall().createQuery("Select u FROM Users u WHERE u.userMail=:userMail and u.userPassword=:password AND u.isDeleted=:isDeleted" )
                    .setParameter("userMail", userMail).setParameter("isDeleted",isDeleted).setParameter("password", password).getSingleResult();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Users> getUser() {
        try {

            String query = "SELECT s FROM Users AS s WHERE  s.isDeleted=:isDeleted";

            query += " order by s.id";

            Query result = entityManager.createQuery(query).setParameter("isDeleted", isDeleted);



            return result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Boolean userDeleted(int id, boolean isDeleted)
    {

        try {
            String hsql= "UPDATE Traveldetails s SET s.isDeleted=:isDeleted WHERE s.user.id=:id  ";
            Query query = entityManager.createQuery(hsql).setParameter("id", id).setParameter("isDeleted",isDeleted);

            int result = query.executeUpdate();
            entityManager.clear();
            return result > 0;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}