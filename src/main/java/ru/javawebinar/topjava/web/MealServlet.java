package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
	private static final Logger log = getLogger(UserServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("forward to meals.jsp");
		request.setAttribute("meals", MealsUtil.filteredByStreams(MealsUtil.MEALS, LocalTime.MIN, LocalTime.MAX, MAX_CALORIES));
		request.getRequestDispatcher("meals.jsp").forward(request, response);
	}
}