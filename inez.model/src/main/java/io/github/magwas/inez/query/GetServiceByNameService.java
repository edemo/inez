package io.github.magwas.inez.query;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import io.github.magwas.konveyor.runtime.LoggerService;

@Service
public class GetServiceByNameService {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	LoggerService logger;

	public BridiFunction apply(final String serviceName) throws BeansException, ClassNotFoundException {
		logger.debug(serviceName);

		Arrays.asList(applicationContext.getBeanDefinitionNames()).forEach(logger::debug);
		return (BridiFunction) applicationContext.getBean(Class.forName(serviceName));
	}
}
