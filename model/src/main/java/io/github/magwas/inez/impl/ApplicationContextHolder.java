package io.github.magwas.inez.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {
	private static ApplicationContext _appCtx;

	@Override
	public void setApplicationContext(ApplicationContext ctx) {
		_appCtx = ctx;
	}

	public static InezImpl getInez() {
		return _appCtx.getBean(InezImpl.class);
	}
}