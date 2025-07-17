package io.github.magwas.inez;

import java.util.Collection;
import java.util.Set;

import io.github.magwas.inez.impl.ApplicationContextHolder;

public interface Inez {
	static final String QUERY_BRIDI_ID = "$?";

	public static Inez getInstance() {
		return ApplicationContextHolder.getInez();
	}

	public void registerListener(BridiStoreChangeListener listener);

	public void unregisterListener(BridiStoreChangeListener listener);

	public Set<Bridi> query(String query);

	public Set<Bridi> create(String query);

	public Set<Bridi> save(Collection<Bridi> values);

}
