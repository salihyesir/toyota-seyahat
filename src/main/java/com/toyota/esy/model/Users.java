/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.esy.model;


import com.toyota.esy.dto.UsersDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Emre-PC
 */
@Entity
@Table(name = "users")
public class Users implements Serializable {



    public Users convert(UsersDTO dto) {
        this.setId(dto.getId());
        this.setUserName(dto.getUserName());
        this.setUserRegister(dto.getUserRegister());
        this.setUserPassword(dto.getUserPassword());
        this.setUserDepartment(dto.getUserDepartment());
        this.setDepartmentAdmin(dto.getDepartmentAdmin());
        this.setNotification(dto.getNotification());
        this.setIsDeleted(dto.getIsDeleted());
        this.setUserMail(dto.getUserMail());
        this.setUserRole(new Roles().convert(dto.getUserRole()));
        return this;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId", unique = true)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "UserName")
    private String userName;

    @Basic(optional = false)
    @NotNull
    @Column(name = "UserRegister", unique = true)
    private int userRegister;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "UserPassword")
    private String userPassword;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UserDepartment")
    private String userDepartment;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DepartmentAdmin")
    private String departmentAdmin;

    @Size(max = 20)
    @Column(name = "Notification")
    private String notification;

    @Basic(optional = false)
    @NotNull
    @Column(name = "IsDeleted")
    private boolean isDeleted;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UserMail", unique = true)
    private String userMail;

    //@UniqueConstraint(columnNames={"userMail"}) buda olabilir

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Traveldetails> traveldetailsCollection;

    @JoinColumn(name = "RoleId")
    @ManyToOne(optional = false)
    private Roles userRole;



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

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Roles getUserRole() {
        return userRole;
    }

    public void setUserRole(Roles userRole) {
        this.userRole = userRole;
    }




}
