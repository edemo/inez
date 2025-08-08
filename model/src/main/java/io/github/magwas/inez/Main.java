package io.github.magwas.inez;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.magwas.runtime.Config;

public class Main {
	public static void main(String[] args) throws IOException {
		Thread.currentThread().setContextClassLoader(Main.class.getClassLoader());
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);
		Inez.getInstance().create("{osgi} is a {bitch}")
				.forEach(x -> System.out.println(x));
	}

}
