/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.esy.dto;


import com.toyota.esy.model.Users;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsersDTO implements Serializable {


    public UsersDTO convert(Users entity) {
        this.setId(entity.getId());
        this.setUserName(entity.getUserName());
        this.setUserRegister(entity.getUserRegister());
        this.setUserPassword(entity.getUserPassword());
        this.setUserDepartment(entity.getUserDepartment());
        this.setDepartmentAdmin(entity.getDepartmentAdmin());
        this.setNotification(entity.getNotification());
        this.setIsDeleted(entity.getIsDeleted());
        this.setUserMail(entity.getUserMail());
        this.setUserRole(new RolesDTO().convert(entity.getUserRole()));
        return this;
    }

    private Integer id;

    private String userName;

    private int userRegister;

    private String userPassword;

    private String userDepartment;


    private String departmentAdmin;

    private String notification;

    private boolean isDeleted;

    private  String userMail;

    private RolesDTO userRole;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(int userRegister) {
        this.userRegister = userRegister;
    }

    public String getUserPassword() {

        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }


    public String getDepartmentAdmin() {
        return departmentAdmin;
    }


    public void setDepartmentAdmin(String departmentAdmin) {
        this.departmentAdmin = departmentAdmin;
    }


    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setUserRole(RolesDTO userRole) {
        this.userRole = userRole;
    }

    public RolesDTO getUserRole() {
        return userRole;
    }






    public String encryption(String password)
    {
        try{
            MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
            messageDigestNesnesi.update(password.getBytes());
            byte messageDigestDizisi[] = messageDigestNesnesi.digest();
            StringBuffer sb32 = new StringBuffer();
            for (int i = 0; i < messageDigestDizisi.length; i++) {
                sb32.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 32));
            }
            return sb32.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "root.toyota";
        }
    }



}
