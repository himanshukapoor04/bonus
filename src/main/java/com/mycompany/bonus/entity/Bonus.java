package com.mycompany.bonus.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for Bonus
 */
@Entity
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date createdDate;

    @Column
    private String comments;

    @Column
    private Float wageringRequirement;

    @Column
    private boolean status;

    public Bonus() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Float getWageringRequirement() {
        return wageringRequirement;
    }

    public void setWageringRequirement(Float wageringRequirement) {
        this.wageringRequirement = wageringRequirement;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
