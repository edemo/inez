package io.github.magwas.inez.query;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.element.BridiElementFactory;
import io.github.magwas.inez.parse.ParseTextService;
import io.github.magwas.inez.storage.SaveBridiService;
import io.github.magwas.runtime.LogUtil;

@Service
public class CreateBridisFromQueryService {
	@Autowired
	ParseTextService parseText;
	@Autowired
	CreateBridisFromParserOutputService createBridisFromParserOutput;
	@Autowired
	SaveBridiService saveBridi;
	@Autowired
	BridiElementFactory bridiElementFactory;

	public Stream<Bridi> apply(final String query) {
		LogUtil.debug("create(" + query);
		List<Bridi> toSave = parseText.apply(query)
				.map(createBridisFromParserOutput).flatMap(x -> x)
				.peek(x -> LogUtil.debug("saving", x)).toList();
		saveBridi.apply(toSave);
		toSave.forEach(x -> bridiElementFactory.apply(x.id()));
		return toSave.stream();
	}

}
