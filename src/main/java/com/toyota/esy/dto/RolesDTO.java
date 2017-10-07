/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.esy.dto;

import com.toyota.esy.model.Roles;

import java.io.Serializable;

public class RolesDTO implements Serializable {

    public RolesDTO convert(Roles entity) {
        this.setId(entity.getId());
        this.setRoleName(entity.getRoleName());
        return this;
    }

    private Integer id;

    private String roleName;


//BeanSerializer özelliği için mutlaka bir sınıfta json veri yollanması lazım yoksa sınıf bulunamaz

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
