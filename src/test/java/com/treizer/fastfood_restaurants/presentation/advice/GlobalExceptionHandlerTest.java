package com.treizer.fastfood_restaurants.presentation.advice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.treizer.fastfood_restaurants.DataProvider;
import com.treizer.fastfood_restaurants.presentation.controller.RestaurantController;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantInsertDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantUpdateDto;
import com.treizer.fastfood_restaurants.service.interfaces.ICommonService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@WebMvcTest(RestaurantController.class)
// @ExtendWith(MockitoExtension.class)
@Import(GlobalExceptionHandler.class)
// @ExtendWith(SpringExtension.class)
// @SpringBootTest
// @AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ICommonService<RestaurantDto, RestaurantInsertDto, RestaurantUpdateDto> service;

    @Test
    public void testHandleValidationExceptions() throws Exception {
        // Given
        RestaurantInsertDto insertDto = DataProvider.createRestaurantInsertDtoBadMock();
        String content = DataProvider.createJSONDto(insertDto);

        // When
        // when(this.service.save(insertDto)).thenThrow(UnsupportedOperationException.class);
        this.mockMvc.perform(post("/api/restaurant")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testHandleEntityNotFoundException() throws Exception {
        // Given
        Long id = 10L;

        // When
        // when(this.service.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        when(this.service.findById(anyLong()))
                .thenThrow(new EntityNotFoundException("No se encontró el restaurante con ID: " + id));
        this.mockMvc.perform(get("/api/restaurant/{id}", id))
                // Then
                .andExpect(status().isNotFound());

        verify(this.service).findById(anyLong());
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testHandleHttpMessageNotReadableException() throws Exception {
        // Given
        String malFormedJson = DataProvider.createMalFormedJson();

        // When
        this.mockMvc.perform(post("/api/restaurant")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(malFormedJson))
                // Then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testHandleConstraintViolationException() throws Exception {
        // Given
        RestaurantInsertDto insertDto = DataProvider.createRestaurantInsertDtoMock();
        String content = DataProvider.createJSONDto(insertDto);

        // When
        when(this.service.save(any(RestaurantInsertDto.class))).thenThrow(new ConstraintViolationException(
                "No fue posible guarda el restaurante -> " + insertDto.toString(), null));
        // when(this.service.save(any(RestaurantInsertDto.class))).thenThrow(ConstraintViolationException.class);
        // doThrow(new ConstraintViolationException("Test constraint violation", null))
        // .when(this.service.save(any(RestaurantInsertDto.class)));

        this.mockMvc.perform(post("/api/restaurant")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(content))
                // Then
                .andExpect(status().isBadRequest());

        verify(this.service).save(any(RestaurantInsertDto.class));
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testHandleNullPointerException() throws Exception {
        // Given
        Long id = null;

        // When
        when(this.service.deleteById(anyLong()))
                .thenThrow(new NullPointerException("No puedes pasar un id vacío -> " + id));
        this.mockMvc.perform(delete("/api/restaurant/1"))
                // Then
                .andExpect(status().isBadRequest());

        verify(this.service).deleteById(anyLong());
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void testHandleGlobalException() throws Exception {
        // Given

        // When
        when(this.service.findAll()).thenThrow(new RuntimeException("Oops!"));
        this.mockMvc.perform(get("/api/restaurant"))
                // Then
                .andExpect(status().isInternalServerError());
    }

}
