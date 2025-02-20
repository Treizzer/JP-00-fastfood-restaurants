package com.treizer.fastfood_restaurants.service.implemetation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.treizer.fastfood_restaurants.DataProvider;
import com.treizer.fastfood_restaurants.persistence.entity.RestaurantEntity;
import com.treizer.fastfood_restaurants.persistence.repository.IRestaurantRepository;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantInsertDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantUpdateDto;
import com.treizer.fastfood_restaurants.service.implementation.RestaurantService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private IRestaurantRepository repository;

    @InjectMocks
    private RestaurantService service;

    @Test
    public void testFindAll() {
        // Given

        // When
        when(this.repository.findAll()).thenReturn(DataProvider.restaurantEntityIterableMock());
        List<RestaurantDto> result = this.service.findAll();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("McDonald's", result.get(0).getName());
        Assertions.assertEquals("324 Main St", result.get(0).getAddress());
        Assertions.assertEquals(44.9213, result.get(0).getLatitude());
        Assertions.assertEquals(-74.89021, result.get(0).getLongitude());
        Assertions.assertEquals(13662, result.get(0).getPostalCode());

        verify(this.repository).findAll();
    }

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;

        // When
        when(this.repository.findById(anyLong())).thenReturn(DataProvider.optionalRestaurantEntityMock());
        RestaurantDto result = this.service.findById(id);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("McDonald's", result.getName());
        Assertions.assertEquals("324 Main St", result.getAddress());
        Assertions.assertEquals(44.9213, result.getLatitude());
        Assertions.assertEquals(-74.89021, result.getLongitude());
        Assertions.assertEquals(13662, result.getPostalCode());

        verify(this.repository).findById(anyLong());
    }

    @Test
    public void testFindByIdError() {
        // Given
        Long id = 10L;

        // When
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.service.findById(id);
        });

        verify(this.repository).findById(anyLong());
    }

    @Test
    public void testSave() {
        // Given
        RestaurantInsertDto insertDto = DataProvider.restaurantInsertDtoMock();

        // When
        when(this.repository.save(any(RestaurantEntity.class))).thenReturn(DataProvider.restaurantEntityMock());
        RestaurantDto result = this.service.save(insertDto);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Tacos' Pepe", result.getName());
        Assertions.assertEquals("234 Loro", result.getAddress());
        Assertions.assertEquals(234.31223, result.getLatitude());
        Assertions.assertEquals(-30.34153, result.getLongitude());
        Assertions.assertEquals(89253, result.getPostalCode());

        verify(this.repository).save(any(RestaurantEntity.class));
    }

    @Test
    public void testSaveError() {
        // Given
        RestaurantInsertDto insertDto = DataProvider.restaurantInsertDtoMock();

        // When
        when(this.repository.save(any(RestaurantEntity.class))).thenThrow(UnsupportedOperationException.class);

        // Then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            this.service.save(insertDto);
        });

        verify(this.repository).save(any(RestaurantEntity.class));
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void testUpdateWithId() {
        // Given
        RestaurantUpdateDto updateDto = DataProvider.restaurantUpdateDtoMock();
        Long id = 1L;

        // When
        when(this.repository.findById(anyLong())).thenReturn(DataProvider.optionalRestaurantEntityMock());
        when(this.repository.save(any(RestaurantEntity.class))).thenReturn(DataProvider.restaurantEntityUpdatedMock());
        RestaurantDto result = this.service.update(updateDto, id);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Name 1", result.getName());
        Assertions.assertEquals("1 Pine St.", result.getAddress());
        Assertions.assertEquals(111.111, result.getLatitude());
        Assertions.assertEquals(-22.222, result.getLongitude());
        Assertions.assertEquals(11, result.getPostalCode());

        verify(this.repository).findById(anyLong());
        verify(this.repository).save(any(RestaurantEntity.class));
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void testUpdateErrorWithId() {
        // Given
        Long id = 10L;

        // When
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.service.update(null, id);
        });

        verify(this.repository).findById(anyLong());
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void testUpdateWithoutId() {
        // Given
        RestaurantUpdateDto updateDto = DataProvider.restaurantUpdateDtoMock();

        // When
        when(this.repository.findById(anyLong())).thenReturn(DataProvider.optionalRestaurantEntityMock());
        when(this.repository.save(any(RestaurantEntity.class))).thenReturn(DataProvider.restaurantEntityUpdatedMock());
        RestaurantDto result = this.service.update(updateDto, null);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Name 1", result.getName());
        Assertions.assertEquals("1 Pine St.", result.getAddress());
        Assertions.assertEquals(111.111, result.getLatitude());
        Assertions.assertEquals(-22.222, result.getLongitude());
        Assertions.assertEquals(11, result.getPostalCode());

        verify(this.repository).findById(anyLong());
        verify(this.repository).save(any(RestaurantEntity.class));
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void testUpdateErrorWithoutId() {
        // Given
        RestaurantUpdateDto updateDto = DataProvider.restaurantUpdateDtoErrorMock();

        // When
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.service.update(updateDto, null);
        });

        verify(this.repository).findById(anyLong());
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void testDeleteById() {
        // Given
        Long id = 1L;

        // When
        when(this.repository.findById(anyLong())).thenReturn(DataProvider.optionalRestaurantEntityMock());
        RestaurantDto result = this.service.deleteById(id);

        // Then
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.repository).deleteById(longCaptor.capture());

        Assertions.assertEquals(1L, longCaptor.getValue());
        Assertions.assertNotNull(result);
        Assertions.assertEquals("McDonald's", result.getName());
        Assertions.assertEquals("324 Main St", result.getAddress());
        Assertions.assertEquals(44.9213, result.getLatitude());
        Assertions.assertEquals(-74.89021, result.getLongitude());
        Assertions.assertEquals(13662, result.getPostalCode());

        verify(this.repository).findById(anyLong());
        verify(this.repository).deleteById(anyLong());
        verifyNoMoreInteractions(this.repository);
    }

}
