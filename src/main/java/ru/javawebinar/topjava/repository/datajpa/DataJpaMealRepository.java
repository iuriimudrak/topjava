package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

	private final CrudMealRepository mealRepository;
	private final CrudUserRepository userRepository;

	@Autowired
	public DataJpaMealRepository(CrudMealRepository mealRepository, CrudUserRepository userRepository) {
		this.mealRepository = mealRepository;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public Meal save(Meal meal, int userId) {
		if (!meal.isNew() && get(meal.getId(), userId) == null) {
			return null;
		} else {
			meal.setUser(userRepository.getOne(userId));
			return mealRepository.save(meal);
		}
	}

	@Override
	public boolean delete(int id, int userId) {
		return mealRepository.delete(id, userId) != 0;
	}

	@Override
	public Meal get(int id, int userId) {
		return mealRepository.findById(id)
												 .filter(meal -> meal.getUser().getId() == userId)
												 .orElse(null);
	}

	@Override
	public List<Meal> getAll(int userId) {
		return mealRepository.getAll(userId);
	}

	@Override
	public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
		return mealRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
	}
}
