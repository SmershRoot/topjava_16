package ru.javawebinar.topjava.repositories;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MealRepositoryImpl implements MealRepository {
    Map<Long,  Meal> meals = new ConcurrentHashMap<>();
    AtomicLong count = new AtomicLong(0);

    {
        createMeal(new Meal(null, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        createMeal(new Meal(null, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        createMeal(new Meal(null, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        createMeal(new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        createMeal(new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        createMeal(new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Meal getMealById(Long id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getMeals() {
        Collection<Meal> coll = meals.values();

        if (coll instanceof List)
            return (List)coll;
        else
            return new ArrayList(coll);
    }

    @Override
    public Meal createMeal(Meal meal) {
        return saveMeal(meal);
    }

    @Override
    public Meal updateMeal(Meal meal) {
        return saveMeal(meal);
    }

    @Override
    public void delete(Long id) {
        meals.remove(id);
    }

    private Meal saveMeal(Meal meal){
        if (meal.getId() == null){
            meal.setId(count.incrementAndGet());
        }

        return meals.put(meal.getId(),meal);
    }
}
