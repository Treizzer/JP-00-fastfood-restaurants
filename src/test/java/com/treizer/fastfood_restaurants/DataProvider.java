package com.treizer.fastfood_restaurants;

import java.util.List;
import java.util.Optional;

import com.treizer.fastfood_restaurants.persistence.entity.RestaurantEntity;

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

}
