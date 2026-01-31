package com.cs5500.NEUEat.service;

import com.cs5500.NEUEat.dto.response.RestaurantResponse;
import com.cs5500.NEUEat.model.Dish;
import com.cs5500.NEUEat.model.RestaurantInfo;
import java.util.List;
import org.springframework.data.domain.Page;

public interface RestaurantService {

  int addDish(String id, Dish dish);

  int removeDish(String id, Dish dish);

  List<Dish> getAllDishes(String id);

  RestaurantInfo getInformation(String id);

  int updateInfo(String id, RestaurantInfo info);

  // ✅ Pagination + Sorting + Filtering
  Page<RestaurantResponse> getAllRestaurants(
        int page,
        int size,
        String sortBy,
        String direction,
        String city,
        String tag
  );
}
