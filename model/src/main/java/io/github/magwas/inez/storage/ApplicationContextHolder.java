package io.github.magwas.inez.storage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
class ApplicationContextHolder implements ApplicationContextAware {
	private static ApplicationContext _appCtx;

	@Override
	public void setApplicationContext(ApplicationContext ctx) {
		_appCtx = ctx;
	}

	public static Inez getInez() {
		return _appCtx.getBean(Inez.class);
	}
}