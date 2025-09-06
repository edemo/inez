package io.github.magwas.inez.osgi;

import java.io.IOException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.map.repository.config.EnableMapRepositories;

import io.github.magwas.inez.Inez;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.inez.query.BridiFunction;
import io.github.magwas.kodekonveyorannotations.Glue;

@SpringBootApplication
@EnableMapRepositories("io.github.magwas.inez.storage")
@ComponentScan(basePackages = { "io.github.magwas" })
@Glue
public class SpringBootBundleActivator implements BundleActivator {

	public static BundleContext bundleContext;

	ConfigurableApplicationContext appContext;

	@Autowired
	InezImpl inez;

	@Autowired
	AutowireCapableBeanFactory autowireCapableBeanFactory;

	@Override
	public void start(BundleContext bundleContext) throws IOException {
		SpringBootBundleActivator.bundleContext = bundleContext;
		Thread.currentThread()
				.setContextClassLoader(this.getClass().getClassLoader());
		appContext = new AnnotationConfigApplicationContext(
				SpringBootBundleActivator.class, MyBeanRegistrar.class);
		inez = appContext.getBean(InezImpl.class);
		inez.initialize();

		bundleContext.registerService(Inez.class, inez, null);
		System.err.println("registered service");
	}

	@Override
	public void stop(BundleContext bundleContext) {
		SpringApplication.exit(appContext, () -> 0);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBundleActivator.class);
	}

	public BridiFunction obtainAndWireOSGIService(String relname) {
		ServiceReference<BridiFunction> ref = (ServiceReference<BridiFunction>) bundleContext
				.getServiceReference(relname);
		BridiFunction fun = bundleContext.getService(ref);
		autowireCapableBeanFactory.autowireBean(fun);
		return fun;
	}

}
