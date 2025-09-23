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

	String toString(String id, int i) {
		LogUtil.debug(id, i);
		List<String> references = getBridiElementReferenceIds.apply(id).toList();
		StringBuilder builder = new StringBuilder();
		String indent = "\t";
		builder.append(indent.repeat(i));
		builder.append("<element ");
		builder.append("id='" + id + "' ");
		builder.append("type='" + getBridiElementType.apply(id).id + "' ");
		builder.append("name='" + getBridiElementRepresentation.apply(id) + "'>");
		builder.append("\n");
		if (references.size() > 0) {
			builder.append(indent.repeat(i + 1));
			builder.append("<references>\n");
			references.forEach(x -> {
				builder.append(indent.repeat(i + 2));
				builder.append("<reference>");
				builder.append(x);
				builder.append("</reference>\n");
			});
			builder.append(indent.repeat(i + 1));
			builder.append("</references>\n");
		}
		getBridiElementChildren.apply(id)
				.forEach(x -> builder.append(toString(x.id, i + 1)));
		builder.append(indent.repeat(i));
		builder.append("</element>\n");
		return builder.toString();
	}

}