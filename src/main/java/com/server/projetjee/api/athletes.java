package com.server.projetjee.api;

public class athletes {
    private String name;
    private String firstname;
    private String country;
    private String sport;
    private String sex;
    private String birthdate;

    public athletes(String name, String firstname, String country, String sport, String sex, String birthdate) {
        this.name = name;
        this.firstname = firstname;
        this.country = country;
        this.sport = sport;
        this.sex = sex;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
