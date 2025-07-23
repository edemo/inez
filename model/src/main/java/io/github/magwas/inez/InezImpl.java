package io.github.magwas.inez;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.magwas.inez.query.CreateBridisFromParserOutputService;
import io.github.magwas.inez.query.ParseTextService;
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
import io.github.magwas.kodekonveyorannotations.Delegate;
import io.github.magwas.runtime.LogUtil;

@Component
@Delegate
public class InezImpl implements Inez {
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
		bridiStoreChangeListeners.listeners.add(listener);
	}

	public void unregisterListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.remove(listener);
	}

	public Stream<Bridi> query(String query) {
		return Arrays.asList(query.split("\n")).stream()
				.peek(x -> LogUtil.debug("query:" + x)).map(parseText)
				.flatMap(queryProcessor);
	}

	public Stream<Bridi> create(String query) {
		LogUtil.debug("create(" + query);
		List<String> asList = Arrays.asList(query.split("\n"));
		LogUtil.debug("list:" + asList);
		return asList.stream().map(parseText)
				.flatMap(createBridisFromParserOutput::apply)
				.peek(x -> LogUtil.debug("saving" + x)).map(saveBridi);
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
