package com.treizer.fastfood_restaurants.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "No puede ser nulo el nombre")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "No puede ser nulo la direción")
    @Column(nullable = false)
    private String address;

    @NotNull(message = "No puede ser nulo la ciudad")
    @Column(nullable = false)
    private String city;

    @NotNull(message = "No puede ser nulo el país")
    @Column(length = 5, nullable = false)
    private String country;

    @Column(name = "restaurant_keys")
    // private List<String> keys;
    private String keys;

    // If you want to use @Digits the data type needs to be BigDecimal
    // @Digits(integer = 2, fraction = 8, message = "No debe tener más de dos
    // números enteros y no más de ocho decimales")
    private Double latitude;

    // @Digits(integer = 2, fraction = 8, message = "No debe tener más de dos
    // números enteros y no más de ocho decimales")
    private Double longitude;

    @NotNull(message = "No puede ser nulo el código postal")
    @Column(name = "postal_code", nullable = false)
    private Integer postalCode;

    @NotNull(message = "No puede ser nulo la provincia")
    @Column(length = 5, nullable = false)
    private String province;

    // @NotNull(message = "No puede ser nulo el sitio web")
    // @Column(nullable = false)
    private String website;
}
