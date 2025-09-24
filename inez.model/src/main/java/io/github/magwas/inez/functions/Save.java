package io.github.magwas.inez.functions;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.inez.parse.ParseTextService;
import io.github.magwas.inez.query.BridiFunction;
import io.github.magwas.runtime.LogUtil;

@Service
public class Save implements BridiFunction {

	@Autowired
	InezImpl inez;
	@Autowired
	ParseTextService parseText;

	@Override
	public Stream<Bridi> apply(final String top, final List<String> partList, final int notAnyIndex,
                               final List<Set<String>> foundIds) {
		LogUtil.debug("SAVE ", partList, foundIds);
		return inez.create(partList.get(1));
	}

}
