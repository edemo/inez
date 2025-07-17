package io.github.magwas.inez.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiStoreChangeListener;
import io.github.magwas.inez.Inez;
import io.github.magwas.inez.query.CreateBridisFromParserOutput;
import io.github.magwas.inez.query.ParseText;
import io.github.magwas.inez.query.ParserOutput;
import io.github.magwas.inez.query.QueryProcessor;
import io.github.magwas.inez.storage.BridiStoreChangeListeners;
import io.github.magwas.inez.storage.BridiStoreHistory;
import io.github.magwas.inez.storage.CreateBridiFromSumti;
import io.github.magwas.inez.storage.DeleteBridi;
import io.github.magwas.inez.storage.FindAllByRepresentation;
import io.github.magwas.inez.storage.FindAllIdByRepresentation;
import io.github.magwas.inez.storage.FindBridiById;
import io.github.magwas.inez.storage.GetBridiIdBySelbriAndSumtiIds;
import io.github.magwas.inez.storage.SaveBridi;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Component
class InezImpl implements Inez {
	@Autowired
	BridiStoreChangeListeners bridiStoreChangeListeners;
	@Autowired
	QueryProcessor queryProcessor;
	@Autowired
	ParseText parseText;
	@Autowired
	CreateBridisFromParserOutput createBridisFromParserOutput;
	@Autowired
	BridiStoreHistory bridiStoreHistory;
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;
	@Autowired
	CreateBridiFromSumti createBridiFromSumti;
	@Autowired
	SumtiRepository sumtiRepository;
	@Autowired
	SaveBridi saveBridi;
	@Autowired
	DeleteBridi deleteBridi;
	@Autowired
	FindBridiById findBridiById;
	@Autowired
	GetBridiIdBySelbriAndSumtiIds getBridiIdBySelbriAndSumtiIds;
	@Autowired
	FindAllIdByRepresentation findAllIdByRepresentation;
	@Autowired
	FindAllByRepresentation findAllByRepresentation;

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
		Set<Bridi> created = createBridisFromParserOutput.apply(parserOutput);
		saveBridi.apply(created);
		return created;
	}

	public Set<Bridi> save(Collection<Bridi> values) {
		return saveBridi.apply(values);
	}

	@Override
	public Stream<Bridi> findAllByRepresentation(String representation) {
		return findAllByRepresentation.apply(representation);
	}

	@Override
	public Optional<Bridi> findById(String id) {
		return findBridiById.apply(id);
	}

}
