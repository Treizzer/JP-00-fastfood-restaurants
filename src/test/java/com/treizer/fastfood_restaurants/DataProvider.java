package com.treizer.fastfood_restaurants;

import java.util.List;
import java.util.Optional;

import com.treizer.fastfood_restaurants.persistence.entity.RestaurantEntity;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantInsertDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantUpdateDto;

public class DataProvider {

    public static Iterable<RestaurantEntity> restaurantEntityIterableMock() {
        return List.of(
                new RestaurantEntity(1L, "McDonald's", "324 Main St", "Massena", "US",
                        "us/ny/massena/324mainst/-1161002137", 44.9213, -74.89021, 13662, "NY", "http://mcdonalds.com"),
                new RestaurantEntity(2L, "Wendy's", "530 Clinton Ave", "Washington Court House", "US",
                        "us/oh/washingtoncourthouse/530clintonave/-791445730", 39.53255, -83.44526, 43160,
                        "OH", "http://www.wendys.com"),
                new RestaurantEntity(3L, "Frisch's Big Boy", "408 Market Square Dr", "Maysville", "US",
                        "us/ky/maysville/408marketsquaredr/1051460804", 38.62736, -83.79141, 41056,
                        "KY", "http://www.frischs.com"),
                new RestaurantEntity(4L, "McDonald's", "6098 State Highway 37", "Massena", "US",
                        "us/ny/massena/6098statehighway37/-1161002137", 44.95008, -74.84553, 13662, "NY",
                        "http://mcdonalds.com"),
                new RestaurantEntity(5L, "OMG! Rotisserie", "139 Columbus Rd", "Athens", "US",
                        "us/oh/athens/139columbusrd/990890980", 39.35155, -82.09728, 45701, "OH",
                        "http://www.omgrotisserie.com"));
    }

    public static Optional<RestaurantEntity> optionalRestaurantEntityMock() {
        return Optional.of(new RestaurantEntity(1L, "McDonald's", "324 Main St", "Massena", "US",
                "us/ny/massena/324mainst/-1161002137", 44.9213, -74.89021, 13662, "NY", "http://mcdonalds.com"));
    }

    public static RestaurantInsertDto restaurantInsertDtoMock() {
        return RestaurantInsertDto.builder()
                .name("Tacos' Pepe")
                .address("234 Loro")
                .city("Tonala")
                .country("Mexico")
                .keys("tacos/mx/tonala")
                .latitude(234.31223)
                .longitude(-30.34153)
                .postalCode(89253)
                .province("Tonala")
                .website(
                        "https://th.bing.com/th?id=OIP.8g6FehTGg-yPmtvGMiqvAQHaE7&w=298&h=198&c=10&rs=1&qlt=99&bgcl=fffffe&r=0&o=6&dpr=1.5&pid=23.1")
                .build();
    }

    public static RestaurantEntity restaurantEntityMock() {
        return new RestaurantEntity(6L, "Tacos' Pepe", "234 Loro", "Tonala", "Mexico", "tacos/mx/tonala", 234.31223,
                -30.34153, 89253, "Tonala",
                "https://th.bing.com/th?id=OIP.8g6FehTGg-yPmtvGMiqvAQHaE7&w=298&h=198&c=10&rs=1&qlt=99&bgcl=fffffe&r=0&o=6&dpr=1.5&pid=23.1");
    }

    public static RestaurantUpdateDto restaurantUpdateDtoMock() {
        return RestaurantUpdateDto.builder()
                .id(1L)
                .name("Name 1")
                .address("1 Pine St.")
                .city("Chapala")
                .country("Mexico")
                .keys("1/chapala/mx")
                .latitude(111.111)
                .longitude(-22.222)
                .postalCode(11)
                .province("Chapala")
                .website("google.es")
                .build();
    }

    public static RestaurantEntity restaurantEntityUpdatedMock() {
        return new RestaurantEntity(1L, "Name 1", "1 Pine St.", "Chapala", "Mexico", "1/chapala/mx",
                111.111, -22.222, 11, "Chapala", "google.es");
    }

    public static RestaurantUpdateDto restaurantUpdateDtoErrorMock() {
        return RestaurantUpdateDto.builder().id(10L).build();
    }

}
