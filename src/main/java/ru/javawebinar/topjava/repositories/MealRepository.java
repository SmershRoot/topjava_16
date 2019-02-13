package ru.javawebinar.topjava.repositories;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal getMealById(Long id);

    List<Meal> getMeals();

    Meal createMeal (Meal meal);

    Meal updateMeal (Meal meal);

    void delete(Long id);
}
