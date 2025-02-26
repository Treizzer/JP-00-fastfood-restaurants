package com.treizer.fastfood_restaurants.presentation.dto.restaurant;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestaurantUpdateDto {

    @Min(value = 1, message = "No existen IDs menores a uno")
    Long id;

    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String name;

    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String address;

    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String city;

    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String country;

    // @Size(min = 1, max = 5, message = "No debe tener menos de una clave ni más de
    // cinco")
    // List<String> keys;
    String keys;

    @Digits(integer = 2, fraction = 8, message = "No debe tener más de dos números enteros y no más de ocho decimales")
    Double latitude;

    @Digits(integer = 2, fraction = 8, message = "No debe tener más de dos números enteros y no más de ocho decimales")
    Double longitude;

    @Min(value = 100, message = "No puede tener menos de tres digitos el código postal")
    @Max(value = 999999, message = "No puede tener más de seis digitos el código postal")
    Integer postalCode;

    @Size(min = 3, max = 20, message = "No debe tener menos de tres caracteres ni más de veinte")
    String province;

    @Size(min = 5, max = 70, message = "No debe tener menos de cinco caracteres ni más de setenta")
    String website;

}
