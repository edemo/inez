package io.github.magwas.inez;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import io.github.magwas.kodekonveyorannotations.Glue;
import io.github.magwas.runtime.ApplicationContextHolder;

@Glue
public interface Inez {
	String QUERY_BRIDI_ID = "$?";

	static Inez getInstance() {
		return ApplicationContextHolder.getBean(InezImpl.class);
	}

	void registerListener(BridiStoreChangeListener listener);

	void unregisterListener(BridiStoreChangeListener listener);

	Stream<Bridi> query(String query);

	Stream<Bridi> create(String query);

	Set<Bridi> save(Collection<Bridi> values);

	Stream<Bridi> findAllByRepresentation(String representation);

	Optional<Bridi> findById(String string);

}
