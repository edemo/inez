package io.github.magwas.inez;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.parser.BridiLexer;
import io.github.magwas.inez.parser.BridiParser;
import io.github.magwas.inez.parser.SumtiParser.BridiContext;
import io.github.magwas.inez.parser.SumtiParser.TextReferenceContext;

@Service
public class ParseText {

	public List<Bridi> apply(String input) throws ParseCancellationException {
		BridiLexer lexer = new BridiLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		BridiParser parser = new BridiParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ThrowingErrorListener());
		BridiContext bridi = parser.bridi();
		return compileBridiFromTree(bridi);
	}

	public List<Bridi> compileBridiFromTree(BridiContext bridi)
			throws ParseCancellationException {
		Set<Bridi> bridis = new HashSet<>();
		Bridi topBridi;
		String bridiRepresentation = "";
		String representation = "";
		List<String> sumtiList = new ArrayList<>();
		int index = 0;
		for (ParseTree kid : bridi.children) {
			if (kid instanceof TerminalNode) {
				bridiRepresentation += kid.getText();
				representation += kid.getText();
			} else if (kid instanceof BridiContext) {
				List<Bridi> bridisDown = compileBridiFromTree((BridiContext) kid);
				bridis.addAll(bridisDown);
				Bridi sumti = bridisDown.get(0);
				sumtiList.add(sumti.id);
				bridiRepresentation += index++;
				representation += sumti.representation;
			} else if (kid instanceof TextReferenceContext) {
				String reference = kid.getChild(1).getText();
				sumtiList.add(reference);
				bridiRepresentation += index++;
				representation += reference;
			} else
				throw new ParseCancellationException("unrecognized tree element" + kid);
		}
		if (sumtiList.size() == 0) {
			topBridi = new Bridi(representation, representation);
		} else {
			Bridi selbri = new Bridi(bridiRepresentation, bridiRepresentation);
			bridis.add(selbri);
			sumtiList.add(0, selbri.id);
			topBridi = new Bridi(representation, representation, sumtiList);
		}
		List<Bridi> ret = new ArrayList<>(bridis);
		ret.add(0, topBridi);
		return ret;
	}
}
