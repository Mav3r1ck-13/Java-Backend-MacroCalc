package com.macrocounter.dao;

import com.macrocounter.model.Food;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcFoodDao implements FoodDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFoodDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Food> list() {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT food_id, protein, carbs, fats, calories, meal\n" +
                        "FROM daily_food_intake;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Food food = mapRowToFood(results);
            foodList.add(food);
        }
        return foodList;
    }

    @Override
    public Food get(int id) {
        Food food = null;
        String sql = "SELECT protein, carbs, fats, calories, meal\n" +
                "FROM daily_food_intake\n" +
                "WHERE food_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()) {
            food =mapRowToFood(results);
        }
        return food;
    }

    @Override
    public Food createNewFood(Food foodToSave) {
        String sql = "INSERT INTO daily_food_intake (protein, carbs, fats, meal)\n" +
                "VALUES(?, ?, ?, ?)  RETURNING food_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, foodToSave.getProtein(),
                foodToSave.getCarbs(), foodToSave.getFats(), foodToSave.getMeal());
        return get(newId);

    }

    @Override
    public boolean update(int id, Food updatedFood) {
        String sql = "UPDATE daily_food_intake\n" +
                "SET protein = ?, carbs = ?, fats = ?, meal = ?\n" +
                "WHERE food_id = ?;";
        return jdbcTemplate.update(sql, updatedFood.getProtein(), updatedFood.getCarbs(), updatedFood.getFats(), updatedFood.getMeal(), id) == 1;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE\n" +
                "FROM daily_food_intake\n" +
                "WHERE food_id = ?;";
        return jdbcTemplate.update(sql, id) == 1;
    }

    private Food mapRowToFood(SqlRowSet rowSet) {
        Food foods = new Food();
        foods.setProtein(rowSet.getInt("protein"));
        foods.setCarbs(rowSet.getInt("carbs"));
        foods.setFats(rowSet.getInt("fats"));
        foods.setMeal(rowSet.getString("meal"));
        return foods;
    }
}
