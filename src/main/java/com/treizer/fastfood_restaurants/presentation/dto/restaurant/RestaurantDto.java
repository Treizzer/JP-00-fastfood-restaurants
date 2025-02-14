package com.treizer.fastfood_restaurants.presentation.dto.restaurant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantDto {

    Long id;
    String name;
    String address;
    String city;
    String country;
    // List<String> keys;
    String keys;
    Double latitude;
    Double longitude;
    Integer postalCode;
    String province;
    String website;

}
