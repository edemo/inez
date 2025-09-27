package io.github.magwas.inez.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import io.github.magwas.inez.parse.ParseTextService;
import io.github.magwas.kodekonveyorannotations.Glue;
import io.github.magwas.runtime.LoggerService;

@Glue
public class MyBeanRegistrar implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) {}

	@Override
	public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) {
		BundleContext bundleContext = SpringBootBundleActivator.bundleContext;
		ServiceReference<ParseTextService> ref = bundleContext.getServiceReference(ParseTextService.class);
		ParseTextService parseText = bundleContext.getService(ref);
		beanFactory.registerSingleton("parseTextService", parseText);
		LoggerService logger = new LoggerService();
		beanFactory.registerSingleton("loggerService", logger);
	}
}
