package io.github.magwas.inez.element;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.runtime.LogUtil;

@Service
public class RepresentBridiElementService implements ElementConstants {

	@Autowired
	GetBridiElementRepresentationService getBridiElementRepresentation;
	@Autowired
	GetBridiElementChildrenService getBridiElementChildren;
	@Autowired
	GetBridiElementParentService getBridiElementParent;
	@Autowired
	GetBridiElementTypeService getBridiElementType;
	@Autowired
	GetBridiElementReferenceIdsService getBridiElementReferenceIds;

	public String apply(String id) {
		return toString(id, 0);
	}

	private String toString(String id, int indentLevel) {
		LogUtil.debug(id, indentLevel);
		List<String> references = getBridiElementReferenceIds.apply(id).toList();
		StringBuilder builder = new StringBuilder(ELEMENT_REPRESENTATION_STRINGBUILDER_INITIAL_CAPACITY)
				.append(INDENT.repeat(indentLevel))
				.append("<element id='")
				.append(id)
				.append("' type='")
				.append(getBridiElementType.apply(id).id)
				.append("' name='")
				.append(getBridiElementRepresentation.apply(id))
				.append("'>\n");
		if (!references.isEmpty()) {
			builder.append(INDENT.repeat(indentLevel + 1))
					.append("<references>\n");
			references.forEach(x -> builder.append(INDENT.repeat(indentLevel + 2))
                    .append("<reference>")
                    .append(x)
                    .append("</reference>\n"));
			builder.append(INDENT.repeat(indentLevel + 1))
					.append("</references>\n");
		}
		getBridiElementChildren.apply(id)
				.forEach(x -> builder.append(toString(x.id, indentLevel + 1)));
		builder.append(INDENT.repeat(indentLevel))
				.append("</element>\n");
		return builder.toString();
	}

}
