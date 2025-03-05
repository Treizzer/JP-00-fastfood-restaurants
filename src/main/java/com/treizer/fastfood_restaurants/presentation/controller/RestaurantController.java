package com.treizer.fastfood_restaurants.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantInsertDto;
import com.treizer.fastfood_restaurants.presentation.dto.restaurant.RestaurantUpdateDto;
import com.treizer.fastfood_restaurants.service.interfaces.ICommonService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    ICommonService<RestaurantDto, RestaurantInsertDto, RestaurantUpdateDto> restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> findAll() {
        return ResponseEntity.ok(this.restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> findById(@PathVariable Long id) {
        // if (id == null || id <= 0) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        RestaurantDto restaurantDto = this.restaurantService.findById(id);
        return ResponseEntity.ok(restaurantDto);
        // try {

        // } catch (EntityNotFoundException e) {
        // return ResponseEntity.notFound().build();
        // }
        // return restaurantDto == null
        // ? ResponseEntity.notFound().build()
        // : ResponseEntity.ok(restaurantDto);
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> save(@Valid @RequestBody RestaurantInsertDto restaurantInsertDto) {
        RestaurantDto restaurantDto = this.restaurantService.save(restaurantInsertDto);

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(restaurantDto.getId())
                        .toUri())
                .body(restaurantDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> update(@Valid @RequestBody RestaurantUpdateDto restaurantUpdateDto,
            @PathVariable Long id) {
        // if (id <= 0 && restaurantUpdateDto.getId() <= 0) {
        // return ResponseEntity.badRequest().build();
        // }

        try {
            RestaurantDto restaurantDto = this.restaurantService.update(restaurantUpdateDto, id);
            return ResponseEntity.ok(restaurantDto);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestaurantDto> deleteById(@PathVariable Long id) {
        // if (id == null || id <= 0) {
        if (id <= 0) {
            // Testing my exception for first time in method controller
            // throw new BadRequestException("No puede ser nulo y no puede ser meor igual a
            // cero un ID:" + id);
            return ResponseEntity.badRequest().build();
        }

        try {
            var restaurantDto = this.restaurantService.deleteById(id);
            return ResponseEntity.ok(restaurantDto);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        // return restaurantDto == null
        // ? ResponseEntity.notFound().build()
        // : ResponseEntity.ok(restaurantDto);
    }

}
