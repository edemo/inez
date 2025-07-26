package io.github.magwas.inez;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import io.github.magwas.inez.element.BridiElementSystemInitializationService;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.kodekonveyorannotations.Glue;
import io.github.magwas.runtime.ContextUtils;

@Glue
public interface Inez {
	static Inez getInstance() {
		ContextUtils instance = ContextUtils.getInstance();
		try {
			instance.getBean(BridiElementSystemInitializationService.class).apply();
		} catch (IOException e) {
			throw new Error(e);
		}
		return instance.getBean(InezImpl.class);
	}

	void registerListener(BridiStoreChangeListener listener);

	void unregisterListener(BridiStoreChangeListener listener);

	Stream<Bridi> query(String query);

	Stream<Bridi> create(String query);

	Sumti createSumti(String id, String representation);

	Set<Bridi> save(Collection<Bridi> values);

	Stream<Bridi> findAllByRepresentation(String representation);

	Optional<Bridi> findById(String string);

	Stream<Bridi> createFromdefinitions(String string);

}
