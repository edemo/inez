package io.github.magwas.testing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.TestInstantiationException;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.magwas.kodekonveyorannotations.Glue;

@Glue
public class TestBase {
	@BeforeEach
	public void setUp() {
		stubUp(this);

	}

	@AfterEach
	public void tearDown() {
	}

	@SuppressWarnings({
			"PMD.AvoidAccessibilityAlteration",
			"PMD.AvoidPrintStackTrace" })
	public static void stubUp(final Object test) {
		try {
			for (Field objField : test.getClass().getDeclaredFields()) {
				if (objField.isAnnotationPresent(InjectMocks.class)) {
					Class<?> type = objField.getType();
					Constructor<?> constructor = type.getDeclaredConstructor();
					constructor.setAccessible(true);
					Object instance = constructor.newInstance();
					objField.setAccessible(true);
					objField.set(test, instance);
					stubFill(instance);
				}
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			throw new TestInstantiationException("stubUp", e);
		}
	}

	@SuppressWarnings({
			"PMD.AvoidPrintStackTrace",
			"PMD.AvoidAccessibilityAlteration" })
	public static void stubFill(final Object instance) {
		Class<? extends Object> type = instance.getClass();
		for (Field field : type.getDeclaredFields()) {
			if (field.isAnnotationPresent(Autowired.class)) {
				String stubName = field.getType().getName() + "Stub";
				Class<?> stub;
				Object value;
				try {
					stub = Class.forName(stubName);
					Method method = stub.getDeclaredMethod("stub");
					if (null == method) {
						throw new TestInstantiationException(
								stubName + " does not have stub");
					}
					method.setAccessible(true);
					value = method.invoke(null);
				} catch (ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
					throw new TestInstantiationException("problem with stub " + stubName,
							e);
				}
				field.setAccessible(true);
				try {
					field.set(instance, value);
				} catch (IllegalAccessException e) {
					throw new TestInstantiationException("stubFill", e);
				}
			}
		}
	}

}