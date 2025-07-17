package io.github.magwas.inez.impl;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiStoreChangeListener;
import io.github.magwas.inez.Inez;
import io.github.magwas.inez.query.CreateBridisFromParserOutput;
import io.github.magwas.inez.query.ParseText;
import io.github.magwas.inez.query.ParserOutput;
import io.github.magwas.inez.query.QueryProcessor;
import io.github.magwas.inez.storage.BridiStore;
import io.github.magwas.inez.storage.BridiStoreChangeListeners;

@Component
class InezImpl implements Inez {
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

	private InezImpl() {
	};

	public void registerListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.getListeners().add(listener);
	}

	public void unregisterListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.getListeners().remove(listener);
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
