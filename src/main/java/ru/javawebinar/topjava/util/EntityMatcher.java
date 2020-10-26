package ru.javawebinar.topjava.util;

import org.assertj.core.internal.IgnoringFieldsComparator;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityMatcher<T> {

	private final String[] IGNORING_FIELDS;

	public EntityMatcher(String... ignoringFields) {
		this.IGNORING_FIELDS = ignoringFields;
	}

	public void assertMatch(T actual, T expected) {
		assertThat(actual)
						.usingRecursiveComparison()
						.ignoringFields(IGNORING_FIELDS)
						.isEqualTo(expected);
	}

	public void assertMatch(Iterable<T> actual, T... expected) {
		assertMatch(actual, Arrays.asList(expected));
	}

	public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
		assertThat(actual).usingElementComparatorIgnoringFields(IGNORING_FIELDS)
											.isEqualTo(expected);
	}
}
