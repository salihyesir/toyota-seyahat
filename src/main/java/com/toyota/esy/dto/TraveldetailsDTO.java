/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.esy.dto;

import com.toyota.esy.model.Traveldetails;

import java.io.Serializable;
import java.util.Date;




public class TraveldetailsDTO implements Serializable {


    public TraveldetailsDTO convert(Traveldetails entity) {
        this.setId(entity.getId());
        this.setTravelLocation(entity.getTravelLocation());
        this.setTravelMission(entity.getTravelMission());
        this.setProjectCode(entity.getProjectCode());
        this.setTravelStartDate(entity.getTravelStartDate());
        this.setTravelFinishDate(entity.getTravelFinishDate());
        this.setIsDeleted(entity.getIsDeleted());
        this.setEstimatedCost(entity.getEstimatedCost());
        this.setUser(new UsersDTO().convert(entity.getUser()));
        return this;
    }



    private Integer id;
    private String travelLocation;
    private String travelMission;
    private String projectCode;
    private Date travelStartDate;
    private Date travelFinishDate;
    private boolean isDeleted;
    private String estimatedCost;
    private UsersDTO user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTravelLocation() {
        return travelLocation;
    }

    public void setTravelLocation(String travelLocation) {
        this.travelLocation = travelLocation;
    }

    public String getTravelMission() {
        return travelMission;
    }

    public void setTravelMission(String travelMission) {
        this.travelMission = travelMission;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Date getTravelStartDate() {
        return travelStartDate;
    }

    public void setTravelStartDate(Date travelStartDate) {
        this.travelStartDate = travelStartDate;
    }

    public Date getTravelFinishDate() {
        return travelFinishDate;
    }

    public void setTravelFinishDate(Date travelFinishDate) {
        this.travelFinishDate = travelFinishDate;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(String estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }




}
