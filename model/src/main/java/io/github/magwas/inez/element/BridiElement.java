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
import io.github.magwas.runtime.ContextUtils;
import io.github.magwas.runtime.LogUtil;

@Delegate
public class BridiElement implements ElementConstants {
	@Id
	String id;
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
	RepresentBridiElementService representBridiElementService;
	@Autowired
	SumtiRepository sumtiRepository;
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;
	@Autowired
	AddReferencesService addReferences;

	public static BridiElement byId(String id) {
		BridiElement element = (BridiElement) ContextUtils.getInstance()
				.wireAndCache(new BridiElement(id));
		element.fixParent();
		return element;
	}

	void fixParent() {
		if (id.equals(ROOT_ID))
			return;
		String parent = getBridiElementParent.apply(id);
		if (parent != null)
			return;
		LogUtil.debug("fixing", id);
		String unplacedId = "unplaced:" + id;
		sumtiRepository.save(new Sumti(unplacedId, unplacedId));
		addReferences.apply(unplacedId, List.of(CONTAINS_ID, UNPLACED_ID, id));
	}

	private BridiElement(String id) {
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
		return byId(getBridiElementParent.apply(id));
	}

	public String toXml() {
		return representBridiElementService.apply(id);
	}

	@Override
	public String toString() {
		return "BridiElement(" + id + "," + getRepresentation() + ")";
	}
}
