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
import org.springframework.stereotype.Service;

import io.github.magwas.inez.parser.BridiLexer;
import io.github.magwas.inez.parser.BridiParser;
import io.github.magwas.inez.parser.BridiParser.BridiContext;
import io.github.magwas.inez.parser.BridiParser.TextReferenceContext;

@Service
public class ParseText {

	public ParserOutput apply(final String input) {
		BridiLexer lexer = new BridiLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		BridiParser parser = new BridiParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ThrowingErrorListener());
		BridiContext bridi = parser.bridi();
		return compileBridiFromTree(bridi);
	}

	private ParserOutput compileBridiFromTree(final BridiContext bridi) {
		StringBuilder representation = new StringBuilder();
		String top = bridi.getText();

		Map<String, List<String>> references = new HashMap<>();
		List<String> kidrefs = new ArrayList<>();
		int childIndex = 0;
		for (ParseTree kid : bridi.children) {
			if (kid instanceof TerminalNode) {
				representation.append(kid.getText());
			} else if (kid instanceof BridiContext) {
				representation.append(childIndex++);
				ParserOutput parsedKid = compileBridiFromTree((BridiContext) kid);
				references.putAll(parsedKid.referenceMap);
				kidrefs.add(parsedKid.top);
			} else if (kid instanceof TextReferenceContext) {
				representation.append(childIndex++);
				String reference = kid.getText();
				kidrefs.add(reference);
			} else
				throw new ParseCancellationException("unrecognized tree element" + kid);
		}
		String repr = representation.toString();
		if (childIndex > 0) {
			kidrefs.add(0, repr);
			references.put(top, kidrefs);
		}
		ParserOutput ret = new ParserOutput(top, references);
		return ret;
	}

}
