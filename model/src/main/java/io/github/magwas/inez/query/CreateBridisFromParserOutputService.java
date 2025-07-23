package io.github.magwas.inez.query;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.InezUtil;

@Service
public class CreateBridisFromParserOutputService
		implements Function<ParserOutput, Stream<Bridi>> {
	public Stream<Bridi> apply(ParserOutput parserOutput) {
		String top = parserOutput.top();
		Map<String, List<String>> refMap = parserOutput.referenceMap();
		return apply(top, refMap);
	}

	private Stream<Bridi> apply(String top, Map<String, List<String>> refMap) {
		if (!refMap.containsKey(top)) {
			Bridi bridi = new Bridi(InezUtil.createID(top), top, null);
			return Stream.of(bridi);
		}
		List<String> partList = refMap.get(top);
		return Stream
				.of(new Bridi(InezUtil.createID(top), top,
						partList.stream().map(x -> InezUtil.createID(x)).toList()))
				.mapMulti((topBridi, consumer) -> {
					consumer.accept(topBridi);
					partList.stream().map(x -> apply(x, refMap)).flatMap(x -> x)
							.forEach(x -> consumer.accept(x));
				});
	}
}