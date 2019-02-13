package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.repositories.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if (action == "delete") {
            Long id = Long.valueOf(Objects.requireNonNull(request.getParameter("id")));
            MealRepositoryImpl mealRepository = new MealRepositoryImpl();
            mealRepository.delete(id);
        } else {

        }


        request.setAttribute("meals", MealsUtil.getMeals(MealsUtil.MEALS, 2000));

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

}
