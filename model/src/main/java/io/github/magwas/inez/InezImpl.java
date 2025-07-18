package io.github.magwas.inez;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.query.CreateBridisFromParserOutputService;
import io.github.magwas.inez.query.ParseTextService;
import io.github.magwas.inez.query.ParserOutput;
import io.github.magwas.inez.query.QueryProcessorService;
import io.github.magwas.inez.storage.BridiStoreChangeListenersService;
import io.github.magwas.inez.storage.BridiStoreHistoryRepository;
import io.github.magwas.inez.storage.CreateBridiFromSumtiService;
import io.github.magwas.inez.storage.DeleteBridiService;
import io.github.magwas.inez.storage.FindAllByRepresentationService;
import io.github.magwas.inez.storage.FindAllIdByRepresentationService;
import io.github.magwas.inez.storage.FindBridiByIdService;
import io.github.magwas.inez.storage.GetBridiIdBySelbriAndSumtiIdsService;
import io.github.magwas.inez.storage.SaveBridiService;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
class InezImpl implements Inez {
	@Autowired
	BridiStoreChangeListenersService bridiStoreChangeListeners;
	@Autowired
	QueryProcessorService queryProcessor;
	@Autowired
	ParseTextService parseText;
	@Autowired
	CreateBridisFromParserOutputService createBridisFromParserOutput;
	@Autowired
	BridiStoreHistoryRepository bridiStoreHistoryRepository;
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;
	@Autowired
	CreateBridiFromSumtiService createBridiFromSumti;
	@Autowired
	SumtiRepository sumtiRepository;
	@Autowired
	SaveBridiService saveBridi;
	@Autowired
	DeleteBridiService deleteBridi;
	@Autowired
	FindBridiByIdService findBridiById;
	@Autowired
	GetBridiIdBySelbriAndSumtiIdsService getBridiIdBySelbriAndSumtiIds;
	@Autowired
	FindAllIdByRepresentationService findAllIdByRepresentation;
	@Autowired
	FindAllByRepresentationService findAllByRepresentation;

	private InezImpl() {
	}

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
