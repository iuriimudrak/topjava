package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryDaoImpl implements MemoryDao<Meal, Integer> {
	private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();

	{
		MealsUtil.MEALS.forEach(this::save);
	}

	@Override
	public List<Meal> getAll() {
		return new ArrayList<>(repository.values());
	}

	@Override
	public Meal get(Integer id) {
		return repository.get(id);
	}


	private final AtomicInteger mealQuantity = new AtomicInteger(0);

	@Override
	public Meal save(Meal meal) {
		if (meal.getId() == null) {
			int id = mealQuantity.getAndIncrement();
			meal.setId(id);
			return repository.put(id, meal);
		} else {
			return repository.replace(meal.getId(), meal) == null ? null : meal;
		}
	}

	@Override
	public boolean delete(Integer id) {
		return repository.remove(id) != null;
	}
}
