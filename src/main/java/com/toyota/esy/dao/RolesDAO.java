package com.toyota.esy.dao;

import com.toyota.esy.model.Roles;
import org.springframework.stereotype.Repository;



@Repository
public class RolesDAO  extends GenericDAO<Roles> {

    public RolesDAO() {super(Roles.class);}

}
