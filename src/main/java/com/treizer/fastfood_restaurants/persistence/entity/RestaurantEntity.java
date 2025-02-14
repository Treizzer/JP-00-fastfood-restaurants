package com.treizer.fastfood_restaurants.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurants")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(length = 5, nullable = false)
    private String country;

    @Column(name = "restaurant_keys")
    // private List<String> keys;
    private String keys;

    private Double latitude;

    private Double longitude;

    @Column(name = "postal_code", nullable = false)
    private Integer postalCode;

    @Column(length = 5, nullable = false)
    private String province;

    private String website;
}
