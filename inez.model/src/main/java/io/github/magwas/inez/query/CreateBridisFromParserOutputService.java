package io.github.magwas.inez.query;

import static io.github.magwas.runtime.LogUtil.debug;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.parse.IdUtil;
import io.github.magwas.inez.parse.ParserOutput;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class CreateBridisFromParserOutputService
		implements Function<ParserOutput, Stream<Bridi>> {

	@Autowired
	SumtiRepository sumtiRepository;

	public Stream<Bridi> apply(ParserOutput parserOutput) {
		String top = parserOutput.top();
		Map<String, List<String>> refMap = parserOutput.referenceMap();
		return apply(top, refMap);
	}

	private Stream<Bridi> apply(String top, Map<String, List<String>> refMap) {
		debug("apply(", top, refMap);
		if (!refMap.containsKey(top)) {
			Bridi bridi = new Bridi(getIdOrRepr(top), top, null);
			debug("bridi->", bridi);
			return Stream.of(bridi);
		}
		List<String> partList = refMap.get(top);
		return Stream
				.of(new Bridi(getIdOrRepr(top), top,
						partList.stream().map(x -> getIdOrRepr(x)).toList()))
				.mapMulti((topBridi, consumer) -> {
					consumer.accept(topBridi);
					partList.stream().map(x -> apply(x, refMap)).flatMap(x -> x)
							.forEach(x -> consumer.accept(x));
				});
	}

	private String getIdOrRepr(String top) {
		Set<Sumti> candidates = sumtiRepository.findAllByRepresentation(top);
		if (candidates.isEmpty())
			return IdUtil.createID(top);
		if (candidates.size() > 1)
			System.err.println("multiple candidates for " + top + ":" + candidates);
		return candidates.iterator().next().id();
	}
}