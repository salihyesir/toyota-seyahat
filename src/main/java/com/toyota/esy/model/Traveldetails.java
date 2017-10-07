/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.esy.model;

import com.toyota.esy.dto.TraveldetailsDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@Entity// Veri tabanına kaydediliceği için bir entity olarak kabul ediliyor JPA entitysi
@Table(name = "traveldetails")
public class Traveldetails implements Serializable {


    public Traveldetails convert(TraveldetailsDTO dto) {
        this.setId(dto.getId());
        this.setTravelLocation(dto.getTravelLocation());
        this.setTravelMission(dto.getTravelMission());
        this.setProjectCode(dto.getProjectCode());
        this.setTravelStartDate(dto.getTravelStartDate());
        this.setTravelFinishDate(dto.getTravelFinishDate());
        this.setIsDeleted(dto.getIsDeleted());
        this.setEstimatedCost(dto.getEstimatedCost());
        this.setUser(new Users().convert(dto.getUser()));
        return this;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TravelId", unique = true)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TravelLocation")
    private String travelLocation;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TravelMission")
    private String travelMission;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ProjectCode")
    private String projectCode;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TravelStartDate")
    @Temporal(TemporalType.DATE)
    private Date travelStartDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TravelFinishDate")
    @Temporal(TemporalType.DATE)
    private Date travelFinishDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "IsDeleted")
    private boolean isDeleted;

    @Size(max = 10)
    @Column(name = "EstimatedCost")
    private String estimatedCost;

    @JoinColumn(name = "UserId", nullable = false, unique = true)
    @ManyToOne(optional = false)
    private Users user;


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

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }



}
