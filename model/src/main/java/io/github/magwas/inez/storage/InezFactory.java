package io.github.magwas.inez.storage;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import io.github.magwas.inez.Bridi;

@Component
public class InezFactory implements ApplicationContextAware {
	private static ApplicationContext _appCtx;
	@Autowired
	BridiStoreChangeListeners bridiStoreChangeListeners;
	@Autowired
	QueryProcessor queryProcessor;
	@Autowired
	BridiStore bridiStore;

	@Override
	public void setApplicationContext(ApplicationContext ctx) {
		_appCtx = ctx;
	}

	public static InezFactory getInstance() {
		return _appCtx.getBean(InezFactory.class);
	}

	public void registerListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.add(listener);
	}

	public void unregisterListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.remove(listener);
	}

	public Set<Bridi> query(String query) {
		return queryProcessor.apply(query);

	}

	public Collection<Bridi> save(Collection<Bridi> values) {
		return bridiStore.save(values);
	}
}
