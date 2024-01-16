//package com.ait.grooming.model;
//
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.ToString;
//
//import java.util.Collection;
//
//@Entity
//@Table(name = "discounts")
//@Data
//@ToString(exclude = "clients")
//public class Discount {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "name", length = 50, nullable = false)
//    private String name;
//    @OneToMany(mappedBy = "discount")
//    private Collection<User> clients;
//    @Column(name = "discount_rate")
//    private double discountRate;
//    @Column(name = "total_visits")
//    private int totalVisits;
//}
