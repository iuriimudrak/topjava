package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
				"classpath:spring/spring-app.xml",
				"classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

	@Autowired
	private MealService service;

	@Autowired
	private MealRepository repository;

	@Test
	public void get() {
		Meal actual = service.get(MEAL_1.getId(), USER_ID);
		MEAL_ENTITY_MATCHER.assertMatch(actual, MEAL_1);
	}

	@Test
	public void getNotOwn() {
		assertThrows(NotFoundException.class, () -> service.get(MEAL_1.getId(), ADMIN_ID));
	}

	@Test
	public void getNotFound() {
		assertThrows(NotFoundException.class, () -> service.get(123, USER_ID));
	}

	@Test
	public void delete() {
		service.delete(MEAL_1.getId(), USER_ID);
		assertNull( repository.get(MEAL_1.getId(), USER_ID) );
	}

	@Test
	public void deleteNotOwn() {
		assertThrows(NotFoundException.class, () -> service.delete(MEAL_1.getId(), ADMIN_ID));
	}

	@Test
	public void deleteNotFound() {
		assertThrows(NotFoundException.class, () -> service.delete(123, ADMIN_ID));
	}

	@Test
	public void getBetweenInclusive() {
		List<Meal> actualMeals = service.getBetweenInclusive(LocalDate.of(2020,2,20), LocalDate.of(2020,2,25), USER_ID);

		MEAL_ENTITY_MATCHER.assertMatch(actualMeals, MEAL_3, MEAL_2, MEAL_5, MEAL_1);

		actualMeals = service.getBetweenInclusive(LocalDate.of(2020, 2, 1), LocalDate.of(2020, 2, 29), USER_ID);
		MEAL_ENTITY_MATCHER.assertMatch(actualMeals, USER_MEALS);

		actualMeals = service.getBetweenInclusive(LocalDate.of(2020, 2, 26), LocalDate.of(2020, 2, 29), USER_ID);
		MEAL_ENTITY_MATCHER.assertMatch(actualMeals, MEAL_4);

		actualMeals = service.getBetweenInclusive(null, null, USER_ID);
		MEAL_ENTITY_MATCHER.assertMatch(actualMeals, USER_MEALS);

		actualMeals = service.getBetweenInclusive(null, LocalDate.of(2020, 1, 29), USER_ID);
		MEAL_ENTITY_MATCHER.assertMatch(actualMeals, new ArrayList<>());
	}

	@Test
	public void getAll() {
		List<Meal> actualMeals = service.getAll(USER_ID);
		MEAL_ENTITY_MATCHER.assertMatch(actualMeals, USER_MEALS);
	}

	@Test
	public void update() {
		Meal updatedMeal = getUpdated(MEAL_1);
		service.update(updatedMeal, USER_ID);
		MEAL_ENTITY_MATCHER.assertMatch(service.get(MEAL_1.getId(), USER_ID), updatedMeal);
	}

	@Test
	public void create() {
		Meal newMeal = getNew();
		Meal createdMeal = service.create(newMeal, USER_ID);
		newMeal.setId(createdMeal.getId());

		MEAL_ENTITY_MATCHER.assertMatch(createdMeal, newMeal);
		MEAL_ENTITY_MATCHER.assertMatch(service.get(newMeal.getId(), USER_ID), newMeal);

	}

	@Test
	public void updateNotOwn() {
		assertThrows(NotFoundException.class, () -> service.delete(MEAL_1.getId(), ADMIN_ID));
	}

	@Test
	public void updateNotFound() {
		assertThrows(NotFoundException.class, () -> service.delete(123, ADMIN_ID));
	}
}