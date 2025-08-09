package io.github.magwas.inez.osgi;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.map.repository.config.EnableMapRepositories;

import io.github.magwas.runtime.ContextUtils;

@SpringBootApplication
@EnableMapRepositories("io.github.magwas.inez.storage")
@ComponentScan(basePackages = { "io.github.magwas" })
public class SpringBootBundleActivator implements BundleActivator {

	public static BundleContext bundleContext;

	ConfigurableApplicationContext appContext;

	@Autowired
	BridiElementService bridiElementService;

	@Autowired
	AutowireCapableBeanFactory autowireCapableBeanFactory;

	@Override
	public void start(BundleContext bundleContext) {
		SpringBootBundleActivator.bundleContext = bundleContext;
		Thread.currentThread()
				.setContextClassLoader(this.getClass().getClassLoader());
		appContext = new AnnotationConfigApplicationContext(
				SpringBootBundleActivator.class, MyBeanRegistrar.class);
		ContextUtils.setContext(appContext);
		ContextUtils contextUtils = ContextUtils.getInstance();
		appContext.getAutowireCapableBeanFactory().autowireBean(contextUtils);
		BridiElementService bean0 = contextUtils.getBean(BridiElementService.class);
		bridiElementService = appContext.getBean(BridiElementService.class);
		System.out.println("bean0:" + bean0);
		System.out.println("bean1:" + bridiElementService);

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

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBundleActivator.class);
	}
}
