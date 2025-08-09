package io.github.magwas.inez.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.util.Assert;

import io.github.magwas.inez.parse.ParseTextService;
import io.github.magwas.runtime.ContextUtils;
import io.github.magwas.runtime.ObjectCache;

public class MyBeanRegistrar implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
			throws BeansException {
	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("looking up ParseTextService");
		BundleContext bundleContext = SpringBootBundleActivator.bundleContext;
		ServiceReference<ParseTextService> ref = bundleContext
				.getServiceReference(ParseTextService.class);
		System.out.println("context:" + bundleContext);
		System.out.println("ref:" + ref);
		Assert.notNull(ref, "service reference for ParseTextService is null");
		ParseTextService parseText = bundleContext.getService(ref);
		beanFactory.registerSingleton("parseTextService", parseText);
		ContextUtils contextUtils = new ContextUtils();
		beanFactory.registerSingleton("contextUtils", contextUtils);
		ObjectCache objectCache = new ObjectCache();
		beanFactory.registerSingleton("objectCache", objectCache);
	}
}