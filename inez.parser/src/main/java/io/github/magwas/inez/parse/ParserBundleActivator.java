package io.github.magwas.inez.parse;

import java.util.Hashtable;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.wiring.BundleWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.Assert;

@SpringBootApplication
@ComponentScan(basePackages = { "io.github.magwas" })
public class ParserBundleActivator implements BundleActivator {

	ConfigurableApplicationContext appContext;

	@Autowired
	ParseTextService parseText;

	@Override
	public void start(BundleContext bundleContext) {
		Bundle bundle = bundleContext.getBundle();
		BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);
		ClassLoader classLoader = bundleWiring.getClassLoader();
		Thread.currentThread().setContextClassLoader(classLoader);
		appContext = new AnnotationConfigApplicationContext(
				ParserBundleActivator.class);
		parseText = appContext.getBean(ParseTextService.class);
		Assert.notNull(parseText, "parseText is null");
		ServiceRegistration<ParseTextService> registration = bundleContext
				.registerService(ParseTextService.class, parseText,
						new Hashtable<String, String>());
		System.err.println("registered ParseTextService:" + registration);
	}

	@Override
	public void stop(BundleContext bundleContext) {
		SpringApplication.exit(appContext, () -> 0);
	}

}
