package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceededForEche = getFilteredWithExceededForEche(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceededForEche.forEach(u -> System.out.println( u.getCalories()+"|"+
                u.getDateTime()+"|"+u.getDescription()+"|"+u.isExceed()));
        System.out.println("------------------------");
        List<UserMealWithExceed> filteredWithExceededOptionals = getFilteredWithExceededOptionals(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceededOptionals.forEach(u -> System.out.println( u.getCalories()+"|"+
            u.getDateTime()+"|"+u.getDescription()+"|"+u.isExceed()));
    }

    private static List<UserMealWithExceed>  getFilteredWithExceededForEche(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> exceedByDay = new HashMap<>();
        for(UserMeal userMeal : mealList){
            Integer caloriesByDay = Utils.nvl(exceedByDay.get(userMeal.getDateTime().toLocalDate()), 0);
            exceedByDay.put(userMeal.getDateTime().toLocalDate(), caloriesByDay + userMeal.getCalories());
        }
        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();
        for(UserMeal userMeal : mealList){
            if(TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(),startTime,endTime)){
                userMealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(),
                        userMeal.getDescription(),userMeal.getCalories(),
                        exceedByDay.get(userMeal.getDateTime().toLocalDate())>caloriesPerDay));
            }
        }

        return userMealWithExceeds;
    }

    private static List<UserMealWithExceed>  getFilteredWithExceededOptionals(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCaloriesByDay = mealList.stream()
                .collect(Collectors.groupingBy(ml -> ml.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream().filter(ml -> TimeUtil.isBetween(ml.getDateTime().toLocalTime(),
                startTime,endTime)
        ).map(um -> new UserMealWithExceed(um.getDateTime(),
                um.getDescription(),
                um.getCalories(),
                sumCaloriesByDay.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
        .collect(Collectors.toList());
    }

}
