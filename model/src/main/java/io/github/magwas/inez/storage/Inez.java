package io.github.magwas.inez.storage;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.ParseText;
import io.github.magwas.inez.ParserOutput;

@Component
public class Inez {
	@Autowired
	BridiStoreChangeListeners bridiStoreChangeListeners;
	@Autowired
	QueryProcessor queryProcessor;
	@Autowired
	ParseText parseText;
	@Autowired
	BridiStore bridiStore;
	@Autowired
	CreateBridisFromParserOutput createBridisFromParserOutput;

	private Inez() {
	};

	public static Inez getInstance() {
		return ApplicationContextHolder.getInez();
	}

	public void registerListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.add(listener);
	}

	public void unregisterListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.remove(listener);
	}

	public Set<Bridi> query(String query) {
		ParserOutput parserOutput = parseText.apply(query);
		Set<Bridi> bridiSet = queryProcessor.apply(parserOutput);
		return bridiSet;
	}

	public Set<Bridi> create(String query) {
		ParserOutput parserOutput = parseText.apply(query);
		Set<Bridi> created = this.createBridisFromParserOutput.apply(parserOutput);
		bridiStore.save(created);
		return created;
	}

	public Set<Bridi> save(Collection<Bridi> values) {
		return bridiStore.save(values);
	}

}
