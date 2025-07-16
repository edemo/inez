package io.github.magwas.inez.storage;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.github.magwas.inez.model.Bridi;
import io.github.magwas.inez.query.ParserOutput;

@Service
public class CreateBridisFromParserOutput {
	public Set<Bridi> apply(ParserOutput parserOutput) {
		String top = parserOutput.getTop();
		Map<String, List<String>> refMap = parserOutput.getReferenceMap();
		return apply(top, refMap);
	}

	private Set<Bridi> apply(String top, Map<String, List<String>> refMap) {
		if (!refMap.containsKey(top))
			return Set.of(new Bridi(top, top, null));
		List<String> partList = refMap.get(top);
		HashSet<Bridi> ret = new HashSet<>();
		ret.add(new Bridi(top, top, partList));
		for (String sumti : partList)
			ret.addAll(apply(sumti, refMap));
		return ret;
	}
}