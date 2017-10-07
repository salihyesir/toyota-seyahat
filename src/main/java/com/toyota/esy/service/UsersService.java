package com.toyota.esy.service;

import com.toyota.esy.dao.RolesDAO;
import com.toyota.esy.dao.UsersDAO;
import com.toyota.esy.dto.RolesDTO;
import com.toyota.esy.dto.UsersDTO;
import com.toyota.esy.model.Roles;
import com.toyota.esy.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UsersService {

    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private RolesDAO rolesDAO;


    /**
     * Servis de user kaydı bölümü
     * id alanı otomatik artmakta model de ayarlandı
     * */
    @Transactional
    public UsersDTO save(UsersDTO dto) {
        Users users=new Users();
        Roles roles = rolesDAO.find(dto.getUserRole().getId());
        try{
            users.setUserName(dto.getUserName());
            users.setUserDepartment(dto.getUserDepartment());
            users.setUserRegister(dto.getUserRegister());
            users.setUserPassword(new UsersDTO().encryption(dto.getUserPassword()));//şifrelendi
            users.setDepartmentAdmin(dto.getDepartmentAdmin());
            users.setUserRole(roles);
            users.setNotification(dto.getNotification());
            users.setUserMail(dto.getUserMail());
            users.setIsDeleted(dto.getIsDeleted());
            users = usersDAO.persist(users);
        }catch (Exception e) {
            return null;
        }
        return dto.convert(users);
    }

    /**
     * Kullanıcı güncelleme bölümü bu bölümde silmede yapılıyor
     *
     * */
    @Transactional
    public UsersDTO edit(int id, UsersDTO dto) {
        Users users = usersDAO.find(id);
        Roles roles = new Roles();
        try{
            users.setUserName(dto.getUserName());
            if (dto.getIsDeleted() == false)
                users.setUserPassword(new UsersDTO().encryption(dto.getUserPassword()));//şifrelendi
            else
                users.setUserPassword(dto.getUserPassword());
            users.setUserDepartment(dto.getUserDepartment());
            users.setDepartmentAdmin(dto.getDepartmentAdmin());
            roles.setRoleName(dto.getUserRole().getRoleName());
            roles.setId(dto.getUserRole().getId());
            users.setUserRole(roles);
            users.setNotification(dto.getNotification());
            users.setUserRegister(dto.getUserRegister());
            users.setIsDeleted(dto.getIsDeleted());


            users.setUserMail(dto.getUserMail());
            if (dto.getIsDeleted() == true)
                usersDAO.userDeleted(id,true);
            users = usersDAO.merge(users);
        }catch (Exception e) {
            return null;
        }
        return dto.convert(users);
    }

    /**
     * Login olan kullanıcın bilgilerinin döndürüldüğü servis metodu
     *
     * */
    public UsersDTO loginUserInformation(int id){

        Users user =  usersDAO.find(id);//Login olan kullanıcı bulunur.
        UsersDTO usersDTO = new UsersDTO();//Web servise gidecek olan DTO
        RolesDTO roles =new RolesDTO();//User tablosuna bağımlı olan rol
        try {
            usersDTO.setId(user.getId());//id set edildi.
            usersDTO.setUserName(user.getUserName());//userName set edildi.

            roles.setRoleName(user.getUserRole().getRoleName());
            roles.setId(user.getUserRole().getId());

            usersDTO.setUserRole(roles);//Rol set edildi
            usersDTO.setUserMail(user.getUserMail());//mail set edildi
            usersDTO.setDepartmentAdmin(user.getDepartmentAdmin());//müdür set edildi
            usersDTO.setIsDeleted(user.getIsDeleted());//bu alan set edilmeside olur
            usersDTO.setNotification(user.getNotification());//bildirim set edildi
            usersDTO.setUserPassword(user.getUserPassword());//Şifreleme algoritması kullanılcak
            usersDTO.setUserRegister(user.getUserRegister());//Şifreleme algoritması kullanılcak
            usersDTO.setUserDepartment(user.getUserDepartment());// departman set edildi
        }catch (Exception e){
            return null;
        }
        return usersDTO;
    }
    /**
     * Login işlemi
     * LoginServletden buraya geçilir ve login için gerekli sorguya geçilir
     * mail ve paralo parametrelerine geri dönen user LoginServlete geri döndürülür
     * Session ve cookieler doldurulur.
     * Eğerki user yoksa  Loginservlete null döndürülür
     * userDAO nun içinin null gelme riskine karşılık nesnemiz oluşturulur.
     * */
    public UsersDTO signInUser(String userMail, String password){
        if (usersDAO == null)
            usersDAO= new UsersDAO();
        UsersDTO usersDTO = new UsersDTO();//Web servise gidecek olan DTO
        RolesDTO roles =new RolesDTO();//User tablosuna bağımlı olan rol

        Users user =  usersDAO.loginUserInformation(userMail, password);
        //Girilen parametrelere göre bir user olmama ihtimali için null kontrolü
        if(user==null)
            return null;
        try {
            usersDTO.setId(user.getId());//id set edildi.
            usersDTO.setUserName(user.getUserName());//userName set edildi.

            roles.setId(user.getUserRole().getId());
            roles.setRoleName(user.getUserRole().getRoleName());

            usersDTO.setUserRole(roles);//Rol set edildi
            usersDTO.setUserMail(user.getUserMail());//mail set edildi
            usersDTO.setDepartmentAdmin(user.getDepartmentAdmin());//müdür set edildi
            usersDTO.setIsDeleted(user.getIsDeleted());//bu alan set edilmeside olur
            usersDTO.setNotification(user.getNotification());//bildirim set edildi
            usersDTO.setUserPassword(user.getUserPassword());//Şifreleme algoritması kullanılcak
            usersDTO.setUserRegister(user.getUserRegister());//Şifreleme algoritması kullanılcak
            usersDTO.setUserDepartment(user.getUserDepartment());// departman set edildi
        }catch (Exception e){
            return null;
        }
        return usersDTO;
    }

    /**
     * Tüm kullanıcılar alınır(silinen kullanıcılar hariç)
     * */
    public List<UsersDTO> getAll() {
        List<UsersDTO> userList = new ArrayList<UsersDTO>();
        try {
            for (Users user : usersDAO.getUser()){
                UsersDTO usersDTO = new UsersDTO();
                RolesDTO roles =new RolesDTO();//User tablosuna bağımlı olan rol

                usersDTO.setId(user.getId());//id set edildi.
                usersDTO.setUserName(user.getUserName());//userName set edildi.

                roles.setRoleName(user.getUserRole().getRoleName());
                roles.setId(user.getUserRole().getId());

                usersDTO.setUserRole(roles);//Rol set edildi
                usersDTO.setUserMail(user.getUserMail());//mail set edildi
                usersDTO.setDepartmentAdmin(user.getDepartmentAdmin());//müdür set edildi7
                usersDTO.setNotification(user.getNotification());//bildirim set edildi
                usersDTO.setIsDeleted(user.getIsDeleted());//bu alan set edilmeside olur
                usersDTO.setUserPassword(user.getUserPassword());//Şifreleme algoritması kullanılcak
                usersDTO.setUserRegister(user.getUserRegister());//Şifreleme algoritması kullanılcak
                usersDTO.setUserDepartment(user.getUserDepartment());// departman set edildi

                userList.add(usersDTO);
            }
        }catch (Exception e) {
            return null;
        }
        return userList;
    }

    /**
     *      Sadece belli bir id ye ait kullanıcılar listelenir
     * */
    public  UsersDTO get(int id) {
        Users user =  usersDAO.find(id);//Login olan kullanıcı bulunur.
        UsersDTO usersDTO = new UsersDTO();//Web servise gidecek olan DTO
        RolesDTO roles =new RolesDTO();//User tablosuna bağımlı olan rol
        try {
            usersDTO.setId(user.getId());//id set edildi.
            usersDTO.setUserName(user.getUserName());//userName set edildi.
            usersDTO.setUserMail(user.getUserMail());//mail set edildi
            roles.setRoleName(user.getUserRole().getRoleName());
            roles.setId(user.getUserRole().getId());
            usersDTO.setUserRole(roles);//Rol set edildi
            usersDTO.setDepartmentAdmin(user.getDepartmentAdmin());//müdür set edildi
            usersDTO.setIsDeleted(user.getIsDeleted());//bu alan set edilmeside olur
            usersDTO.setNotification(user.getNotification());//bildirim set edildi
            usersDTO.setUserPassword(user.getUserPassword());//Şifreleme algoritması kullanılcak
            usersDTO.setUserRegister(user.getUserRegister());//Şifreleme algoritması kullanılcak
            usersDTO.setUserDepartment(user.getUserDepartment());// departman set edildi
        }catch (Exception e){
            return null;
        }
        return usersDTO;
    }
}
