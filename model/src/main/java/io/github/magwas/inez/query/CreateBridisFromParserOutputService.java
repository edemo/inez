package io.github.magwas.inez.query;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.InezUtil;

@Service
public class CreateBridisFromParserOutputService {
	public Set<Bridi> apply(ParserOutput parserOutput) {
		String top = parserOutput.top();
		Map<String, List<String>> refMap = parserOutput.referenceMap();
		return apply(top, refMap);
	}

	private Set<Bridi> apply(String top, Map<String, List<String>> refMap) {
		if (!refMap.containsKey(top))
			return Set.of(new Bridi(InezUtil.createID(top), top, null));
		List<String> partList = refMap.get(top);
		HashSet<Bridi> ret = new HashSet<>();
		ret.add(new Bridi(InezUtil.createID(top), top,
				partList.stream().map(x -> InezUtil.createID(x)).toList()));
		for (String sumti : partList)
			ret.addAll(apply(sumti, refMap));
		return ret;
	}
}