package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOG.debug("redirect to users");

        response.sendRedirect("meals.jsp");
    }
}
