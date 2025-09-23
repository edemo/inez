package io.github.magwas.inez;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.magwas.inez.element.BridiElement;
import io.github.magwas.inez.element.BridiElementFactory;
import io.github.magwas.inez.element.BridiElementSystemInitializationService;
import io.github.magwas.inez.element.ElementConstants;
import io.github.magwas.inez.query.CreateBridisFromDefinitionService;
import io.github.magwas.inez.query.CreateBridisFromQueryService;
import io.github.magwas.inez.query.QueryProcessorService;
import io.github.magwas.inez.storage.BridiStoreChangeListenersService;
import io.github.magwas.inez.storage.BridiStoreHistoryRepository;
import io.github.magwas.inez.storage.CreateBridiFromSumtiService;
import io.github.magwas.inez.storage.CreateSumtiService;
import io.github.magwas.inez.storage.DeleteBridiService;
import io.github.magwas.inez.storage.FindAllByRepresentationService;
import io.github.magwas.inez.storage.FindAllIdByRepresentationService;
import io.github.magwas.inez.storage.FindBridiByIdService;
import io.github.magwas.inez.storage.GetBridiIdBySelbriAndSumtiIdsService;
import io.github.magwas.inez.storage.SaveBridiService;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.kodekonveyorannotations.Delegate;

@Component
@Delegate
public class InezImpl implements Inez {
	@Autowired
	BridiStoreChangeListenersService bridiStoreChangeListeners;
	@Autowired
	QueryProcessorService queryProcessor;
	@Autowired
	BridiStoreHistoryRepository bridiStoreHistoryRepository;
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;
	@Autowired
	CreateBridiFromSumtiService createBridiFromSumti;
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
	@Autowired
	BridiElementSystemInitializationService bridiElementSystemInitialization;
	@Autowired
	BridiElementFactory bridiElementFactory;
	@Autowired
	CreateBridisFromQueryService createBridisFromQuery;
	@Autowired
	CreateSumtiService createSumti;

	private InezImpl() {
	}

	public void initialize() throws IOException {
		bridiElementSystemInitialization.apply();
	}

	public void registerListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.add(listener);
	}

	public void unregisterListener(BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.remove(listener);
	}

	public Stream<Bridi> query(String query) {
		return queryProcessor.apply(query);
	}

	public Stream<Bridi> create(String query) {
		return createBridisFromQuery.apply(query);
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

	@Override
	public Sumti createSumti(String id, String representation) {
		return createSumti.apply(id, representation);
	}

	public BridiReferenceRepository getBridiReferenceRepository() {
		return bridiReferenceRepository;
	}

	@Autowired
	CreateBridisFromDefinitionService createBridisFromDefinition;

	@Override
	public Stream<Bridi> createFromdefinitions(String definitionName) {
		return createBridisFromDefinition.apply(definitionName);
	}

	public BridiElement root() {
		return bridiElementFactory.apply(ElementConstants.ROOT_ID);
	}

	public BridiElement byId(String id) {
		return bridiElementFactory.apply(id);
	}

}
