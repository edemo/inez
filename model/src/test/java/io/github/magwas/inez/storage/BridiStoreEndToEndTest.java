package io.github.magwas.inez.storage;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.InezUtil;
import io.github.magwas.inez.TestConfig;
import io.github.magwas.inez.impl.LogUtil;
import io.github.magwas.inez.query.ParseText;
import io.github.magwas.inez.query.ParserOutput;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class BridiStoreEndToEndTest implements BridiTestData {

	@Autowired
	SumtiRepository sumtiRepository;

	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	@Autowired
	ParseText parseText;

	@Autowired
	BridiStore bridiStore;

	@Test
	void test() {
		TEST_TEXT.forEach(sentence -> {
			ParserOutput output = parseText.apply(sentence);
			output.referenceMap().entrySet().forEach(x -> LogUtil.debug("parsed", x));
			saveOutput(output.top(), output);
		});
		bridiStore.save(GO1);
		bridiStore.save(GO2);
		bridiStore.findAllByRepresentation(GO_REPRESENTATION)
				.forEach(x -> LogUtil.debug("go", x));
		bridiReferenceRepository.findAll().forEach(
				bridiReference -> LogUtil.debug("bridiReference", bridiReference));
		bridiStore
				.getBridiIdBySelbriAndSumtiIds(InezUtil.createID(IS_A_REPR),
						InezUtil.createID(THING_REPR), 2)
				.forEach(x -> LogUtil.debug("getBridiBySelbriAndSumtiIds", x));

	}

	private String saveOutput(String top, ParserOutput parserOutput) {
		String bridiId = top;
		List<String> references = parserOutput.referenceMap().get(top);
		if (null != references)
			references.forEach(x -> saveOutput(x, parserOutput));
		Bridi bridi = bridiStore.createBridiFromRepresentations(top, references);
		bridiStore.save(bridi);
		return bridiId;
	}

}
