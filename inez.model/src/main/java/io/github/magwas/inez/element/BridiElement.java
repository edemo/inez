package io.github.magwas.inez.element;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import io.github.magwas.inez.storage.AddReferencesService;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.inez.storage.repository.SumtiRepository;
import io.github.magwas.kodekonveyorannotations.Delegate;
import io.github.magwas.runtime.LogUtil;

@Delegate
public class BridiElement implements ElementConstants {
	@Id
	public String id;
	@Autowired
	GetBridiElementRepresentationService getBridiElementRepresentation;
	@Autowired
	GetRelativeForBridiElementService getRelativeForBridiElement;
	@Autowired
	GetBridiElementChildrenService getBridiElementChildren;
	@Autowired
	GetBridiElementParentService getBridiElementParent;
	@Autowired
	GetBridiElementTypeService getBridiElementType;
	@Autowired
	GetBridiElementReferencesService getBridiElementReferences;
	@Autowired
	RepresentBridiElementService representBridiElement;
	@Autowired
	SumtiRepository sumtiRepository;
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;
	@Autowired
	AddReferencesService addReferences;
	@Autowired
	IsInstanceService isInstance;
	@Autowired
	CreateBridiElementService createBridiElement;
	@Autowired
	BridiElementFactory bridiElementFactory;
	@Autowired
	IsOfTypeService isOfType;

	@Autowired
	GetBridiElementReferenceIdsService getBridiElementReferenceIds;
	@Autowired
	GetBridiElementTypeIdService getBridiElementTypeId;

	void fixParent() {
		if (ROOT_ID.equals(id))
			return;
		String parent = getBridiElementParent.apply(id);
		if (parent != null)
			return;
		LogUtil.debug("fixing", id);
		List<String> references = getBridiElementReferenceIds.apply(id).toList();
		if (references.size() > 1) {
			String firstSumtiID = references.get(1);
			if (isOfType.apply(firstSumtiID, CONTAINER_ID))
				parent = firstSumtiID;
			else
				parent = getBridiElementParent.apply(firstSumtiID);
		}
		if (parent == null) {
			parent = UNPLACED_ID;
		}
		String unplacedId = "unplaced:" + id;
		sumtiRepository.save(new Sumti(unplacedId, unplacedId));
		LogUtil.debug("reparenting", id, parent);
		addReferences.apply(unplacedId, List.of(CONTAINS_ID, parent, id));
	}

	BridiElement(String id) {
		this.id = id;
	}

	public String getRepresentation() {
		return getBridiElementRepresentation.apply(id);
	}

	public Stream<BridiElement> getChildren() {
		return getBridiElementChildren.apply(id);
	}

	public BridiElement getType() {
		return getBridiElementType.apply(id);
	}

	public Stream<BridiElement> getReferences() {
		return getBridiElementReferences.apply(id);
	}

	public BridiElement getParent() {
		return bridiElementFactory.apply(getBridiElementParent.apply(id));
	}

	public String toXml() {
		return representBridiElement.apply(id);
	}

	@Override
	public String toString() {
		return "BridiElement(" + id + "," + getRepresentation() + ")";
	}

	public boolean isInstance(String typeId) {
		return isInstance.apply(id, typeId);
	}

	public BridiElement create(String containerId, String typeId,
			String representation, String... references) {
		return createBridiElement.apply(containerId, typeId, representation,
				references);
	}

}
