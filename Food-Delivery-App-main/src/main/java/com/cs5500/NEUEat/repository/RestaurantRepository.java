package com.cs5500.NEUEat.repository;

import com.cs5500.NEUEat.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    Page<Restaurant> findByCityIgnoreCase(String city, Pageable pageable);

}
