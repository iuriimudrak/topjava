package ru.javawebinar.topjava;

import org.jetbrains.annotations.NotNull;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.EntityMatcher;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
	public static final EntityMatcher<Meal> MEAL_ENTITY_MATCHER = new EntityMatcher<>();

	// User meals
	public static final Meal MEAL_1 = new Meal(START_SEQ+2, LocalDateTime.of(2020, Month.FEBRUARY, 25, 10, 0), "Завтрак", 700);
	public static final Meal MEAL_2 = new Meal(START_SEQ+3, LocalDateTime.of(2020, Month.FEBRUARY, 25, 15, 0), "Обед", 1000);
	public static final Meal MEAL_3 = new Meal(START_SEQ+4, LocalDateTime.of(2020, Month.FEBRUARY, 25, 18, 0), "Ужин", 1100);
	public static final Meal MEAL_4 = new Meal(START_SEQ+5, LocalDateTime.of(2020, Month.FEBRUARY, 26, 10, 0), "Завтрак", 500);
	public static final Meal MEAL_5 = new Meal(START_SEQ+6, LocalDateTime.of(2020, Month.FEBRUARY, 25, 13, 0), "Обед", 1200);

	// Admin meals
	public static final Meal MEAL_6 = new Meal(START_SEQ+7, LocalDateTime.of(2020, Month.FEBRUARY, 25, 9, 0), "Завтрак", 900);
	public static final Meal MEAL_7 = new Meal(START_SEQ+8, LocalDateTime.of(2020, Month.FEBRUARY, 25, 22, 0), "Ужин", 400);
	public static final Meal MEAL_8 = new Meal(START_SEQ+9, LocalDateTime.of(2020, Month.FEBRUARY, 26, 10, 0), "Завтрак", 900);

	//Lists of Meals
	public static final List<Meal> USER_MEALS = Stream.of(MEAL_1, MEAL_2, MEAL_3, MEAL_4, MEAL_5).sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(
					Collectors.toList());
	public static final List<Meal> ADMIN_MEALS = Stream.of(MEAL_6, MEAL_7, MEAL_8).sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(
					Collectors.toList());


	public static Meal getNew() {
		return new Meal(LocalDateTime.now(), "Новый прием пищи", 1000);
	}

	public static Meal getUpdated(Meal toUpdateMeal) {
		Meal updatedMeal = new Meal(toUpdateMeal);
		updatedMeal.setDescription(toUpdateMeal.getDescription() + " (upd)");
		updatedMeal.setCalories(toUpdateMeal.getCalories() + 33);

		return updatedMeal;
	}
}
