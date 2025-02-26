package com.treizer.fastfood_restaurants.presentation.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.treizer.fastfood_restaurants.DataProvider;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantInsertDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantUpdateDto;
import com.treizer.fastfood_restaurants.service.interfaces.ICommonService;

import jakarta.persistence.EntityNotFoundException;

@WebMvcTest(RestaurantController.class)
@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // @Mock
    @MockitoBean
    private ICommonService<RestaurantDto, RestaurantInsertDto, RestaurantUpdateDto> service;

    // @InjectMocks
    // private RestaurantController controller;

    // @Test
    public void testFindAllPrototype() {
        // Given

        // When
        when(this.service.findAll()).thenReturn(DataProvider.createRestaurantDtoListMock());
        // ResponseEntity<List<RestaurantDto>> result = this.controller.findAll();
        ResponseEntity<List<RestaurantDto>> result = ResponseEntity.ok(null);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        // Potential null pointer access: The method getBody() may return null
        // Java(536871831)
        // System.out.println(result.getBody().get(0).getName());
        Optional.ofNullable(result.getBody()).ifPresent(restaurants -> {
            Assertions.assertEquals("McDonald's", restaurants.get(0).getName());
            Assertions.assertEquals("324 Main St", restaurants.get(0).getAddress());
            Assertions.assertEquals(44.9213, restaurants.get(0).getLatitude());
            Assertions.assertEquals(-74.89021, restaurants.get(0).getLongitude());
            Assertions.assertEquals(13662, restaurants.get(0).getPostalCode());
        });

        verify(this.service).findAll();
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testFindAll() throws Exception {
        // Given
        List<RestaurantDto> mockRestaurants = DataProvider.createRestaurantDtoListMock();

        // When
        when(this.service.findAll()).thenReturn(mockRestaurants);
        this.mockMvc.perform(get("/api/restaurant"))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("McDonald's"))
                .andExpect(jsonPath("$[0].address").value("324 Main St"))
                .andExpect(jsonPath("$[0].latitude").value(44.9213))
                .andExpect(jsonPath("$[0].longitude").value(-74.89021))
                .andExpect(jsonPath("$[0].postalCode").value(13662));

        verify(this.service).findAll();
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testFindById() throws Exception {
        // Given
        Long id = 1L;

        // when
        when(this.service.findById(anyLong())).thenReturn(DataProvider.getRestaurantDtoMock());
        // this.mockMvc.perform(get("/api/restaurant/1"))
        this.mockMvc.perform(get("/api/restaurant/{id}", id))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("McDonald's"))
                .andExpect(jsonPath("$.address").value("324 Main St"))
                .andExpect(jsonPath("$.latitude").value(44.9213))
                .andExpect(jsonPath("$.longitude").value(-74.89021))
                .andExpect(jsonPath("$.postalCode").value(13662));

        verify(this.service).findById(anyLong());
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testFindByIdNotFoundError() throws Exception {
        // Given
        Long id = 1L;

        // When
        // when(this.service.findById(anyLong())).thenReturn(null);
        when(this.service.findById(anyLong())).thenThrow(EntityNotFoundException.class);

        this.mockMvc.perform(get("/api/restaurant/{id}", id))
                // Then()
                .andExpect(status().isNotFound());

        verify(this.service).findById(anyLong());
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testFindByIdBadRequestError() throws Exception {
        // Given
        Long id = 0L;

        // When
        this.mockMvc.perform(get("/api/restaurant/{id}", id))
                // Then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSave() throws Exception {
        // Given
        RestaurantInsertDto insertDto = DataProvider.createRestaurantInsertDtoMock();
        String content = DataProvider.createJSONDto(insertDto);

        // When
        when(this.service.save(any(RestaurantInsertDto.class))).thenReturn(DataProvider.createRestaurantDtoMock());
        // RestaurantDto result = this.service.save(insertDto); // Don't use it

        this.mockMvc.perform(post("/api/restaurant")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(content))
                // Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(6L))
                .andExpect(jsonPath("$.name").value("Tacos' Pepe"))
                .andExpect(jsonPath("$.address").value("234 Loro"))
                .andExpect(jsonPath("$.latitude").value(23.31223))
                .andExpect(jsonPath("$.longitude").value(-30.34153))
                .andExpect(jsonPath("$.postalCode").value(89253));

        verify(this.service).save(any(RestaurantInsertDto.class));
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        RestaurantUpdateDto updateDto = DataProvider.createRestaurantUpdateDtoMock();
        String content = DataProvider.createJSONDto(updateDto);
        Long id = 1L;

        // When
        when(this.service.update(any(RestaurantUpdateDto.class), anyLong()))
                .thenReturn(DataProvider.createRestaurantDtoUpdatedMock());
        this.mockMvc.perform(put("/api/restaurant/{id}", id)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(content))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name 1"))
                .andExpect(jsonPath("$.address").value("1 Pine St."))
                .andExpect(jsonPath("$.latitude").value(11.111))
                .andExpect(jsonPath("$.longitude").value(-22.222))
                .andExpect(jsonPath("$.postalCode").value(111));

        verify(this.service).update(any(RestaurantUpdateDto.class), anyLong());
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testUpdateNotFoundError() throws Exception {
        // Given
        RestaurantUpdateDto updateDto = DataProvider.createRestaurantUpdateDtoMock();
        String content = DataProvider.createJSONDto(updateDto);
        Long id = 0L;

        // When
        when(this.service.update(any(RestaurantUpdateDto.class), anyLong())).thenThrow(EntityNotFoundException.class);
        // If you don't write "/" at the end; return in authomatic 405 error code
        // this.mockMvc.perform(put("/api/restaurant")
        this.mockMvc.perform(put("/api/restaurant/{id}", id)
                // You don't need contentType, accept and content
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(content))
                // Then
                .andExpect(status().isNotFound());

        // If you're going to use verify -> you'll need sending id and content
        verify(this.service).update(any(RestaurantUpdateDto.class), anyLong());
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testDeleteById() throws Exception {
        // Given
        Long id = 1L;

        // When
        when(this.service.deleteById(anyLong())).thenReturn(DataProvider.getRestaurantDtoMock());

        // Then
        this.mockMvc.perform(delete("/api/restaurant/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("McDonald's"))
                .andExpect(jsonPath("$.address").value("324 Main St"))
                .andExpect(jsonPath("$.latitude").value(44.9213))
                .andExpect(jsonPath("$.longitude").value(-74.89021))
                .andExpect(jsonPath("$.postalCode").value(13662));

        verify(this.service).deleteById(anyLong());
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testDeleteByIdBadRequestError() throws Exception {
        // Given
        Long id = 0L;

        // When
        this.mockMvc.perform(delete("/api/restaurant/{id}", id))
                // Then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteByIdNotFoundError() throws Exception {
        // Given
        Long id = 10L;

        // When
        when(this.service.deleteById(anyLong())).thenThrow(EntityNotFoundException.class);
        this.mockMvc.perform(delete("/api/restaurant/{id}", id))
                // Then
                .andExpect(status().isNotFound());

        verify(this.service).deleteById(anyLong());
        verifyNoMoreInteractions(this.service);
    }

}
