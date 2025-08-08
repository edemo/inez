package io.github.magwas.inez.osgi;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.WeakHashMap;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.keyvalue.core.KeyValueAdapter;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.map.MapKeyValueAdapter;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories
@ComponentScan(basePackages = { "io.github.magwas" })
public class SpringBootBundleActivator implements BundleActivator {

	ConfigurableApplicationContext appContext;

	@Autowired
	BridiElementService bridiElementService;

	@Override
	public void start(BundleContext bundleContext) {
		Thread.currentThread()
				.setContextClassLoader(this.getClass().getClassLoader());
		appContext = new AnnotationConfigApplicationContext(
				SpringBootBundleActivator.class);
		displayAllBeans();
		bridiElementService = appContext.getBean(BridiElementService.class);
		Dictionary<String, String> props = new Hashtable<String, String>();
		bridiElementService.initialize();
		bundleContext.registerService(BridiElementService.class,
				bridiElementService, props);
		System.err.println("registered service");
	}

	public void displayAllBeans() {
		System.out.println("beans:");
		String[] allBeanNames = appContext.getBeanDefinitionNames();
		for (String beanName : allBeanNames) {
			System.out.println(beanName);
		}
	}

	@Override
	public void stop(BundleContext bundleContext) {
		SpringApplication.exit(appContext, () -> 0);
	}

	@Bean
	public KeyValueOperations keyValueTemplate() {
		return new KeyValueTemplate(keyValueAdapter());
	}

	@Bean
	public KeyValueAdapter keyValueAdapter() {
		return new MapKeyValueAdapter(WeakHashMap.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBundleActivator.class);
	}
}
