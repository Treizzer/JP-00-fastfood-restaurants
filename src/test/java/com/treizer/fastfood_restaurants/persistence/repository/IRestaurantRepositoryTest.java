package com.treizer.fastfood_restaurants.persistence.repository;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.treizer.fastfood_restaurants.DataProvider;
import com.treizer.fastfood_restaurants.persistence.entity.RestaurantEntity;

@ExtendWith(MockitoExtension.class)
public class IRestaurantRepositoryTest {

    // Perhaps I can't use it because I don't inject mocks
    @Mock
    private IRestaurantRepository repository;

    // @BeforeEach
    // public void init() {
    // this.repository = Mockito.mock(IRestaurantRepository.class);
    // }

    @Test
    public void testFindAll() {
        // Given

        // When
        when(this.repository.findAll()).thenReturn(DataProvider.restaurantEntityIterableMock());
        List<RestaurantEntity> results = StreamSupport
                .stream(this.repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // Then
        Assertions.assertNotNull(results);
        Assertions.assertFalse(results.isEmpty());
        Assertions.assertEquals("McDonald's", results.get(0).getName());
        Assertions.assertEquals("324 Main St", results.get(0).getAddress());
        Assertions.assertEquals(13662, results.get(0).getPostalCode());
        Assertions.assertEquals("http://mcdonalds.com", results.get(0).getWebsite());
    }

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;

        // When
        when(this.repository.findById(anyLong())).thenReturn(DataProvider.optionalRestaurantEntityMock());
        Optional<RestaurantEntity> result = this.repository.findById(id);

        // Then
        Assertions.assertNotNull(result);
    }

}
