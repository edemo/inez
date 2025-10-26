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
import io.github.magwas.inez.storage.repository.BridiStoreHistoryRepository;
import io.github.magwas.inez.storage.repository.ProblemRepository;
import io.github.magwas.konveyor.annotations.Delegate;

@Component
@Delegate
public final class InezImpl implements Inez {
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

	@Autowired
	ProblemRepository problemRepository;

	@Autowired
	CreateBridisFromDefinitionService createBridisFromDefinition;

	private InezImpl() {}

	@Override
	public void initialize() throws IOException {
		bridiElementSystemInitialization.apply();
	}

	@Override
	public void registerListener(final BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.add(listener);
	}

	@Override
	public void unregisterListener(final BridiStoreChangeListener listener) {
		bridiStoreChangeListeners.listeners.remove(listener);
	}

	@Override
	public Stream<Bridi> query(final String query) {
		return queryProcessor.apply(query);
	}

	@Override
	public Stream<Bridi> create(final String query) {
		return createBridisFromQuery.apply(query);
	}

	@Override
	public Set<Bridi> save(final Collection<Bridi> values) {
		return saveBridi.apply(values);
	}

	@Override
	public Stream<Bridi> findAllByRepresentation(final String representation) {
		return findAllByRepresentation.apply(representation);
	}

	@Override
	public Optional<Bridi> findById(final String id) {
		return findBridiById.apply(id);
	}

	@Override
	public Sumti createSumti(final String id, final String representation) {
		return createSumti.apply(id, representation);
	}

	public BridiReferenceRepository getBridiReferenceRepository() {
		return bridiReferenceRepository;
	}

	@Override
	public Stream<Bridi> createFromdefinitions(final String definitionName) {
		return createBridisFromDefinition.apply(definitionName);
	}

	@Override
	public BridiElement root() {
		return bridiElementFactory.apply(ElementConstants.ROOT_ID);
	}

	@Override
	public BridiElement byId(final String id) {
		return bridiElementFactory.apply(id);
	}

	public ProblemRepository getProblems() {
		return problemRepository;
	}
}
