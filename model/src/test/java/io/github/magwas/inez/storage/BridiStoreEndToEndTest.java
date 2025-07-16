package io.github.magwas.inez.storage;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.inez.LogUtil;
import io.github.magwas.inez.TestConfig;
import io.github.magwas.inez.model.Bridi;
import io.github.magwas.inez.model.BridiTestData;
import io.github.magwas.inez.query.ParseText;
import io.github.magwas.inez.query.ParserOutput;

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
			output.getReferenceMap().entrySet()
					.forEach(x -> LogUtil.debug("parsed", x));
			saveOutput(output.getTop(), output);
		});
		bridiStore.save(GO1);
		bridiStore.save(GO2);
		bridiStore.findAllByRepresentation(GO_REPRESENTATION)
				.forEach(x -> LogUtil.debug("go", x));
		bridiReferenceRepository.findAll().forEach(
				bridiReference -> LogUtil.debug("bridiReference", bridiReference));
		bridiStore
				.getBridiIdBySelbriAndSumtiIds(bridiStore.createID(IS_A_REPR),
						bridiStore.createID(THING_REPR), 2)
				.forEach(x -> LogUtil.debug("getBridiBySelbriAndSumtiIds", x));

	}

	private String saveOutput(String top, ParserOutput parserOutput) {
		String bridiId = top;
		List<String> references = parserOutput.getReferenceMap().get(top);
		if (null != references)
			references.forEach(x -> saveOutput(x, parserOutput));
		Bridi bridi = bridiStore.createBridiFromRepresentations(top, references);
		bridiStore.save(bridi);
		return bridiId;
	}

}
