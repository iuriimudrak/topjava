package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
	public static final int USER_ID = 100;
	public static final int ADMIN_ID = 101;

	public static final List<User> USERS = Arrays.asList(
					new User(USER_ID, "User", "user@example.com", "userpass", Role.USER),
					new User(ADMIN_ID, "Admin", "admin@example.com", "adminpass", Role.ADMIN)
	);
}
