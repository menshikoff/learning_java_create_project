package edu.javacourse.studentorder.domain;

public class Address {

    private String postCode;
    private Street street;
    private String building;
    private String extention;
    private String appartment;

    public Address(String postCode, Street street, String building, String extention, String appartment) {
        this.postCode = postCode;
        this.street = street;
        this.building = building;
        this.extention = extention;
        this.appartment = appartment;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }
}
