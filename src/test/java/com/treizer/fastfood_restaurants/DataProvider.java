package com.treizer.fastfood_restaurants;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.treizer.fastfood_restaurants.persistence.entity.RestaurantEntity;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantInsertDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantUpdateDto;

public class DataProvider {

    // Entity Mocks
    public static Iterable<RestaurantEntity> createRestaurantEntityIterableMock() {
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

    public static Optional<RestaurantEntity> createOptionalRestaurantEntityMock() {
        return Optional.of(new RestaurantEntity(1L, "McDonald's", "324 Main St", "Massena", "US",
                "us/ny/massena/324mainst/-1161002137", 44.9213, -74.89021, 13662, "NY", "http://mcdonalds.com"));
    }

    public static RestaurantEntity createRestaurantEntityMock() {
        return new RestaurantEntity(6L, "Tacos' Pepe", "234 Loro", "Tonala", "Mexico", "tacos/mx/tonala", 23.31223,
                -30.34153, 89253, "Tonala",
                "https://th.bing.com/th?id=OIP.8g6FehTGg-yPmtvGMiqvAQHaE7&w=298&h=198&c=10&rs=1&qlt=99&bgcl=fffffe&r=0&o=6&dpr=1.5&pid=23.1");
    }

    public static RestaurantEntity createRestaurantEntityUpdatedMock() {
        return new RestaurantEntity(1L, "Name 1", "1 Pine St.", "Chapala", "Mexico", "1/chapala/mx",
                11.111, -22.222, 111, "Chapala", "google.es");
    }

    // DTO Mocks
    public static RestaurantInsertDto createRestaurantInsertDtoMock() {
        return RestaurantInsertDto.builder()
                .name("Tacos' Pepe")
                .address("234 Loro")
                .city("Tonala")
                .country("Mexico")
                .keys("tacos/mx/tonala")
                .latitude(23.31223)
                .longitude(-30.34153)
                .postalCode(89253)
                // .postalCode(892531111)
                .province("Tonala")
                .website("google.es")
                .build();
    }

    public static RestaurantInsertDto createRestaurantInsertDtoBadMock() {
        return RestaurantInsertDto.builder()
                .name("Tacos' Pepe")
                .address("234 Loro")
                .city("Tonala")
                .country("Mexico")
                .keys("tacos/mx/tonala")
                .latitude(1D)
                .longitude(1D)
                .postalCode(1)
                .province("Tonala")
                .website("google.es")
                .build();
    }

    public static RestaurantUpdateDto createRestaurantUpdateDtoMock() {
        return RestaurantUpdateDto.builder()
                .id(1L)
                .name("Name 1")
                .address("1 Pine St.")
                .city("Chapala")
                .country("Mexico")
                .keys("1/chapala/mx")
                .latitude(11.111)
                .longitude(-22.222)
                .postalCode(111)
                .province("Chapala")
                .website("google.es")
                .build();
    }

    public static RestaurantDto createRestaurantDtoUpdatedMock() {
        return new RestaurantDto(1L, "Name 1", "1 Pine St.", "Chapala", "Mexico", "1/chapala/mx",
                11.111, -22.222, 111, "Chapala", "google.es");
    }

    public static RestaurantUpdateDto createRestaurantUpdateDtoErrorMock() {
        return RestaurantUpdateDto.builder().id(10L).build();
    }

    public static RestaurantUpdateDto createRestaurantUpdateDtoBadIdMock() {
        return RestaurantUpdateDto.builder().id(0L).build();
    }

    public static List<RestaurantDto> createRestaurantDtoListMock() {
        return List.of(
                new RestaurantDto(1L, "McDonald's", "324 Main St", "Massena", "US",
                        "us/ny/massena/324mainst/-1161002137", 44.9213, -74.89021, 13662, "NY", "http://mcdonalds.com"),
                new RestaurantDto(2L, "Wendy's", "530 Clinton Ave", "Washington Court House", "US",
                        "us/oh/washingtoncourthouse/530clintonave/-791445730", 39.53255, -83.44526, 43160,
                        "OH", "http://www.wendys.com"),
                new RestaurantDto(3L, "Frisch's Big Boy", "408 Market Square Dr", "Maysville", "US",
                        "us/ky/maysville/408marketsquaredr/1051460804", 38.62736, -83.79141, 41056,
                        "KY", "http://www.frischs.com"),
                new RestaurantDto(4L, "McDonald's", "6098 State Highway 37", "Massena", "US",
                        "us/ny/massena/6098statehighway37/-1161002137", 44.95008, -74.84553, 13662, "NY",
                        "http://mcdonalds.com"),
                new RestaurantDto(5L, "OMG! Rotisserie", "139 Columbus Rd", "Athens", "US",
                        "us/oh/athens/139columbusrd/990890980", 39.35155, -82.09728, 45701, "OH",
                        "http://www.omgrotisserie.com"));
    }

    public static RestaurantDto createRestaurantDtoMock() {
        return new RestaurantDto(6L, "Tacos' Pepe", "234 Loro", "Tonala", "Mexico", "tacos/mx/tonala", 23.31223,
                -30.34153, 89253, "Tonala",
                "https://th.bing.com/th?id=OIP.8g6FehTGg-yPmtvGMiqvAQHaE7&w=298&h=198&c=10&rs=1&qlt=99&bgcl=fffffe&r=0&o=6&dpr=1.5&pid=23.1");
    }

    public static RestaurantDto getRestaurantDtoMock() {
        return new RestaurantDto(1L, "McDonald's", "324 Main St", "Massena", "US",
                "us/ny/massena/324mainst/-1161002137", 44.9213, -74.89021, 13662, "NY", "http://mcdonalds.com");
    }

    // Utility

    // public static String createJsonRestaurantInsertDtoMock() {
    // return "{" +
    // "'name': 'Tacos Pepe'," +
    // "'address': '234 Loro'," +
    // "'city': 'Tonala'," +
    // "'country': 'Mexico'," +
    // "'keys': 'tacos/mx/tonala'," +
    // "'latitude': 234.31223," +
    // "'longitude': -30.34153," +
    // "'postalCode': 89253," +
    // "'province': 'Tonala'," +
    // "'website':
    // 'https://th.bing.com/th?id=OIP.8g6FehTGg-yPmtvGMiqvAQHaE7&w=298&h=198&c=10&rs=1&qlt=99&bgcl=fffffe&r=0&o=6&dpr=1.5&pid=23.1'"
    // +
    // "}";
    // }

    // public static JSONObject jsonInsert() throws Exception {
    // JSONObject json = new JSONObject();
    // json.put("name", "Tacos' Pepe");
    // json.put("address", "234 Loro");
    // json.put("city", "Tonala");
    // json.put("country", "Mexico");
    // json.put("keys", "tacos/mx/tonala");
    // json.put("latitude", 234.31223);
    // json.put("longitude", -30.34153);
    // json.put("postalCode", 89253);
    // json.put("province", "Tonala");
    // json.put("website",
    // "https://th.bing.com/th?id=OIP.8g6FehTGg-yPmtvGMiqvAQHaE7&w=298&h=198&c=10&rs=1&qlt=99&bgcl=fffffe&r=0&o=6&dpr=1.5&pid=23.1");
    // return json;
    // }

    public static String createJSONDto(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String createMalFormedJson() {
        return "{ \"name\": \"Test Restaurant\", \"address\": }";
    }

    // public static byte[] insertDtoBytes(final RestaurantInsertDto insert) {
    // try {
    // byte[] om = new
    // ObjectMapper().writeValueAsString(insert).getBytes(StandardCharsets.UTF_8);
    // System.out.println("------------------------------------");
    // System.out.println(om);
    // System.out.println("------------------------------------");
    // return om;
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }

}
