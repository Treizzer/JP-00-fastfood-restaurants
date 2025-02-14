package com.treizer.fastfood_restaurants.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.treizer.fastfood_restaurants.persistence.entity.RestaurantEntity;

@Repository
public interface IRestaurantRepository extends CrudRepository<RestaurantEntity, Long> {

}
