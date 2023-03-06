package com.macrocounter;

import com.macrocounter.dao.FoodDao;
import com.macrocounter.dao.JdbcFoodDao;
import com.macrocounter.model.Food;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
@SpringBootApplication
public class MacroNutritionTrackerApplication {

    private final Scanner userInput = new Scanner(System.in);
    private final FoodDao foodDao;


    public MacroNutritionTrackerApplication(BasicDataSource dataSource) {

        foodDao = new JdbcFoodDao(dataSource);
    }

    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/DailyFoodIntake");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        MacroNutritionTrackerApplication application = new MacroNutritionTrackerApplication(dataSource);
        application.run();

//		SpringApplication.run(MacroNutritionTrackerApplication.class, args);
    }

    private void run() {
        displayBanner();

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("-----     Main Menu     -----");
            System.out.println();
            displayMenu();
            int selection = promptForInt("Please select an option: ");
            if (selection == 1) {
                promptForNewFood();

            } else if (selection == 2) {

            } else if (selection == 3) {

            } else if (selection == 4) {

            } else if (selection == 5) {
                running = false;
            } else {
                displayError("Invalid option selected.");
            }
        }

    }

    private Food promptForNewFood() {
        Food food = new Food();

        int protein = -1;
        while (protein < 0) {
            protein = promptForInt("Protein: ");
        }
        food.setProtein(protein);
        int carbs = -1;
        while (carbs < 0) {
            carbs = promptForInt("Carbs: ");
        }
        food.setCarbs(carbs);

        int fats = -1;
        while (fats < 0) {
            fats = promptForInt("Fats: ");
        }
        food.setFats(fats);

        String meal = "";
        while (meal.isBlank()) {
            meal = promptForString("Meal: ");
        }
        food.setMeal(meal);
        foodDao.createNewFood(food);
//        System.out.println(food.getMeal() + " created");
        return food;
    }

    private String promptForString(String prompt) {
        System.out.print(prompt);
        return userInput.nextLine();
    }

    private void displayBanner() {
        System.out.println();
        System.out.println("FFFFFFF    OOO     OOO   DDDDD");
        System.out.println("F         O   O   O   O  D      D");
        System.out.println("FFFFF     O   O   O   O  D       D");
        System.out.println("F         O   O   O   O  D      D");
        System.out.println("F          OOO     OOO   DDDDD");
        System.out.println();
    }

    private void displayMenu() {
        System.out.println("1. Add New Food Item");
        System.out.println("2. Update An Already Entered Food");
        System.out.println("3. Delete A Food Item");
        System.out.println("4. List Of All Foods Eaten");
        System.out.println("5. Exit");
    }


    private void displayError(String message) {
        System.out.println();
        System.out.println("***" + message + "***");
        System.out.println();
    }

    private void displayFood(Food food) {
        System.out.println(food);
        System.out.format(String.valueOf(food.getProtein()), food.getCarbs(), food.getFats());
    }

    private int promptForInt(String prompt) {

        return (int) promptForDouble(prompt);
    }

    private double promptForDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = userInput.nextLine();
            try {
                return Double.parseDouble(response);
            } catch (NumberFormatException e) {
                if (response.isBlank()) {
                    return -1; //Assumes negative numbers are never valid entries.
                } else {
                    displayError("Numbers only, please.");
                }
            }
        }
    }

    }
