package com.example.interactivearc.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Interest {
    @Id
    private long id;

    private boolean covid19;

    private boolean election;

    private boolean india;

    private boolean politics;

    private boolean entertainment;

    private boolean business;

    private boolean technology;

    private boolean sports;

    private boolean finance;

    private boolean vaccine;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public boolean isCovid19() {
        return covid19;
    }

    public void setCovid19(boolean covid19) {
        this.covid19 = covid19;
    }

    public boolean isElection() {
        return election;
    }

    public void setElection(boolean election) {
        this.election = election;
    }

    public boolean isIndia() {
        return india;
    }

    public void setIndia(boolean india) {
        this.india = india;
    }

    public boolean isPolitics() {
        return politics;
    }

    public void setPolitics(boolean politics) {
        this.politics = politics;
    }

    public boolean isEntertainment() {
        return entertainment;
    }

    public void setEntertainment(boolean entertainment) {
        this.entertainment = entertainment;
    }

    public boolean isBusiness() {
        return business;
    }

    public void setBusiness(boolean business) {
        this.business = business;
    }

    public boolean isTechnology() {
        return technology;
    }

    public void setTechnology(boolean technology) {
        this.technology = technology;
    }

    public boolean isSports() {
        return sports;
    }

    public void setSports(boolean sports) {
        this.sports = sports;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }
}
