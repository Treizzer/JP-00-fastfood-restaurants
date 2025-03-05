package com.treizer.fastfood_restaurants.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treizer.fastfood_restaurants.persistence.entity.RestaurantEntity;
import com.treizer.fastfood_restaurants.persistence.repository.IRestaurantRepository;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantInsertDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantUpdateDto;
import com.treizer.fastfood_restaurants.service.interfaces.ICommonService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RestaurantService implements ICommonService<RestaurantDto, RestaurantInsertDto, RestaurantUpdateDto> {

    @Autowired
    private IRestaurantRepository restaurantRepository;

    private static final ModelMapper MAPPER = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantDto> findAll() {
        var restaurants = this.restaurantRepository.findAll();

        return StreamSupport
                .stream(restaurants.spliterator(), false)
                .map(r -> MAPPER.map(r, RestaurantDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantDto findById(Long id) {
        var restaurantEntity = this.restaurantRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el restaurante con ID: " + id));

        return MAPPER.map(restaurantEntity, RestaurantDto.class);
    }

    @Override
    @Transactional
    public RestaurantDto save(RestaurantInsertDto insertDto) {
        try {
            RestaurantEntity restaurantEntity = MAPPER.map(insertDto, RestaurantEntity.class);
            restaurantEntity = this.restaurantRepository.save(restaurantEntity);
            return MAPPER.map(restaurantEntity, RestaurantDto.class);

        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException(
                    "No fue posible guardar el restaurant: " + insertDto + " -> e: " + e.toString());
        }
        // catch (ConstraintViolationException e) {
        // throw new ConstraintViolationException(
        // "Valor erroneo: " + insertDto + " -> e: " + e.toString(),
        // null);
        // } catch (Exception e) {
        // throw new RuntimeException("Error" + e.toString());
        // }
    }

    @Override
    @Transactional
    public RestaurantDto update(RestaurantUpdateDto updateDto, Long id) {
        var restaurantEntity = (id != null)
                ? this.restaurantRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario con ID: " + id))
                : this.restaurantRepository.findById(updateDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se encontró el usuario con ID: " + updateDto.getId()));

        Optional.ofNullable(updateDto.getName()).ifPresent(restaurantEntity::setName);
        Optional.ofNullable(updateDto.getAddress()).ifPresent(restaurantEntity::setAddress);
        Optional.ofNullable(updateDto.getCity()).ifPresent(restaurantEntity::setCity);
        Optional.ofNullable(updateDto.getCountry()).ifPresent(restaurantEntity::setCountry);
        Optional.ofNullable(updateDto.getKeys()).ifPresent(restaurantEntity::setKeys);
        Optional.ofNullable(updateDto.getLatitude()).ifPresent(restaurantEntity::setLatitude);
        Optional.ofNullable(updateDto.getLongitude()).ifPresent(restaurantEntity::setLongitude);
        Optional.ofNullable(updateDto.getPostalCode()).ifPresent(restaurantEntity::setPostalCode);
        Optional.ofNullable(updateDto.getProvince()).ifPresent(restaurantEntity::setProvince);
        Optional.ofNullable(updateDto.getWebsite()).ifPresent(restaurantEntity::setWebsite);

        this.restaurantRepository.save(restaurantEntity);

        return MAPPER.map(restaurantEntity, RestaurantDto.class);
    }

    @Override
    @Transactional
    public RestaurantDto deleteById(Long id) {
        // var restaurantEntity = this.restaurantRepository.findById(id)
        // .orElseThrow(() -> new EntityNotFoundException("No se encontró el restaurante
        // con ID: " + id));

        var restaurantDto = this.findById(id);

        this.restaurantRepository.deleteById(id);

        // return MAPPER.map(restaurantEntity, RestaurantDto.class);
        return restaurantDto;
    }
}
