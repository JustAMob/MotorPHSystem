/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

/**
 *
 * @author JustAMob
 */
public class Address {
    private int addressId;
    private String building;
    private String street;
    private String city;
    private String province;
    private String zipcode;

    public Address() {
    }

    public Address(int address_id, String building, String street, String city, String province, String zipcode) {
        this.addressId = address_id;
        this.building = building;
        this.street = street;
        this.city = city;
        this.province = province;
        this.zipcode = zipcode;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getBuilding() {
        return building;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setAddress_id(int address_id) {
        this.addressId = address_id;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    
}
