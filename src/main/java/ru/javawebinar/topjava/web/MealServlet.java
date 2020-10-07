package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MemoryDao;
import ru.javawebinar.topjava.dao.MemoryDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.MAX_CALORIES;

public class MealServlet extends HttpServlet {
	private static final Logger log = getLogger(UserServlet.class);

	private MemoryDao<Meal, Integer> mealDao;

	@Override
	public void init() {
		mealDao = new MemoryDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action != null ? action : "read") {
			case "update":
			case "create": {
				String id = request.getParameter("id");
				Meal meal = id == null ?
								new Meal(LocalDateTime.now()
																			.truncatedTo(ChronoUnit.MINUTES), "", 0) :
								mealDao.get(Integer.parseInt(id));
				request.setAttribute("meal", meal);
				request.getRequestDispatcher("form.jsp")
							 .forward(request, response);
				break;
			}
			case "delete": {
				Integer id = Integer.parseInt(request.getParameter("id"));
				boolean success = mealDao.delete(id);
				log.debug("Delete meal: {}", success);
				response.sendRedirect("meals");
				break;
			}
			case "read":
			default: {
				List<Meal> meals = mealDao.getAll();
				meals.sort(Comparator.comparing(Meal::getDateTime));
				log.debug("Forward to meals.jsp. Meals size: {}", meals.size());
				request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, MAX_CALORIES));
				request.getRequestDispatcher("meals.jsp")
							 .forward(request, response);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		int calories = Integer.parseInt(request.getParameter("calories"));

		LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"));

		String description = request.getParameter("description");
		String stringId = request.getParameter("id");

		Integer id = stringId.equals("") ? null : Integer.parseInt(request.getParameter("id"));

		Meal meal = new Meal(id, dateTime, description, calories);
		mealDao.save(meal);
		log.debug("Updating: {}", meal);
		response.sendRedirect("meals");
	}
}