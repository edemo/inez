package io.github.magwas;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

public class TestBase {
	@BeforeEach
	public void setUp() {
		stubUp(this);

	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	public static void stubUp(Object test) {
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e);
		}
	}

	public static void stubFill(Object instance) {
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
						throw new Error(stubName + " does not have stub");
					}
					method.setAccessible(true);
					value = method.invoke(null);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Error("problem with stub " + stubName, e);
				}
				field.setAccessible(true);
				try {
					field.set(instance, value);
				} catch (Exception e) {
					throw new Error(e);
				}
			}
		}
	}

}