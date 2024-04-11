package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//"streetAddress":"321 Main Street",
//        "city":"Bangalore",
//        "stateProvince":"Karnataka",
//        "postalCode":"560036",
//        "country":"India"

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;


}
