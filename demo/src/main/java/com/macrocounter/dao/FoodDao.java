package com.macrocounter.dao;

import com.macrocounter.model.Food;

import java.util.List;

public interface FoodDao {

    List<Food> list();

    Food get(int id);

    Food createNewFood(Food foodToSave);

    boolean update(int id, Food updatedFood);

    boolean delete(int id);

}
