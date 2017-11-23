/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.esy.model;

import com.toyota.esy.dto.RolesDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "roles")
public class Roles implements Serializable {

    public Roles convert(RolesDTO dto) {
        this.setId(dto.getId());
        this.setRoleName(dto.getRoleName());
        return this;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RoleId", unique = true)
    private Integer id;
    //Basic buna gerek yok aslında
    //notnull diyerek kullanabilirz
    //http://stackoverflow.com/questions/2899073/basicoptional-false-vs-columnnullable-false-in-jpa
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "RoleName")
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userRole")
    private Collection<Users> usersCollection;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
