package io.github.magwas.inez;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.parser.BridiLexer;
import io.github.magwas.inez.parser.BridiParser;
import io.github.magwas.inez.parser.BridiParser.BridiContext;
import io.github.magwas.inez.parser.BridiParser.TextReferenceContext;
import io.github.magwas.inez.storage.BridiStore;

@Service
public class ParseText {

	@Autowired
	BridiStore bridiStore;

	public BridiSet apply(final String input) {
		BridiLexer lexer = new BridiLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		BridiParser parser = new BridiParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ThrowingErrorListener());
		BridiContext bridi = parser.bridi();
		return compileBridiFromTree(bridi);
	}

	private BridiSet compileBridiFromTree(final BridiContext bridi) {
		Map<String, Bridi> bridis = new HashMap<>();
		StringBuilder bridiRepresentation = new StringBuilder();
		StringBuilder representation = new StringBuilder();
		List<String> sumtiList = new ArrayList<>();
		int index = 0;
		for (ParseTree kid : bridi.children) {
			if (kid instanceof TerminalNode) {
				bridiRepresentation.append(kid.getText());
				representation.append(kid.getText());
			} else if (kid instanceof BridiContext) {
				BridiSet bridisDown = compileBridiFromTree((BridiContext) kid);
				Map<String, Bridi> downBridis = bridisDown.bridis;
				bridis.putAll(downBridis);
				Bridi sumti = downBridis.get(bridisDown.topId);
				sumtiList.add(sumti.id);
				bridiRepresentation.append(index++);
				representation.append(sumti.representation);
			} else if (kid instanceof TextReferenceContext) {
				String reference = kid.getChild(1).getText();
				sumtiList.add(reference);
				bridiRepresentation.append(index++);
				representation.append(reference);
			} else
				throw new ParseCancellationException("unrecognized tree element" + kid);
		}
		Bridi topBridi;
		String representationString = representation.toString();
		if (sumtiList.isEmpty()) {
			topBridi = new Bridi(representationString, representationString);
			bridiStore.save(topBridi);
		} else {
			String bridiRepresentationString = bridiRepresentation.toString();
			Bridi selbri = new Bridi(bridiRepresentationString,
					bridiRepresentationString);
			bridiStore.save(selbri);

			bridis.put(selbri.id, selbri);
			sumtiList.add(0, bridiRepresentationString);
			topBridi = new Bridi(representationString, representationString,
					sumtiList, false);
			bridiStore.save(topBridi);
		}
		bridis.put(topBridi.id, topBridi);
		return new BridiSet(topBridi.id, bridis);
	}
}
