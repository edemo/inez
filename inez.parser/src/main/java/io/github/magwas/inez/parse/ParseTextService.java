package io.github.magwas.inez.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.parser.BridiParser;
import io.github.magwas.inez.parser.BridiParser.BridiContext;
import io.github.magwas.inez.parser.BridiParser.LiteralContext;
import io.github.magwas.inez.parser.BridiParser.ParagraphContext;
import io.github.magwas.inez.parser.BridiParser.TextReferenceContext;
import io.github.magwas.konveyor.runtime.LogUtil;

@Service
public class ParseTextService implements Function<String, Stream<ParserOutput>> {
	@Autowired
	CreateParserFromTokensService createParserFromTokens;

	@Override
	public Stream<ParserOutput> apply(final String input) {
		LogUtil.debug("input:" + input);
		BridiParser parser = createParserFromTokens.apply(input);
		ParagraphContext text = parser.paragraph();
		return text.children.stream()
				.filter(BridiContext.class::isInstance)
				.map(x -> compileBridiFromTree((BridiContext) x));
	}

	private ParserOutput compileBridiFromTree(final BridiContext bridi) {
		StringBuilder representation = new StringBuilder();
		StringBuilder topBuilder = new StringBuilder();
		Map<String, List<String>> references = new HashMap<>();
		List<String> kidrefs = new ArrayList<>();
		int childIndex = 0;
		for (ParseTree kid : bridi.children) {
			String text = kid.getText();
			if (kid instanceof TerminalNode) {
				LogUtil.debug("r:" + text);
				if ("{[".equals(text)) representation.append("{");
				else if ("]}".equals(text)) representation.append("}");
				else representation.append(text);
				topBuilder.append(text);
			} else if (kid instanceof BridiContext) {
				LogUtil.debug("bridi", text, kid.getChildCount());
				ParserOutput parsedKid = compileBridiFromTree((BridiContext) kid);
				references.putAll(parsedKid.referenceMap());
				String kidTop = parsedKid.top();
				kidrefs.add(kidTop);
				representation.append(childIndex++);
				topBuilder.append(kidTop);
			} else if (kid instanceof TextReferenceContext) {
				LogUtil.debug("ref", text);
				representation.append(childIndex++);
				topBuilder.append(text);
				kidrefs.add(text);
			} else if (kid instanceof LiteralContext) {
				LogUtil.debug("literal", text, kid.getChildCount());
				representation.append(childIndex++);
				topBuilder.append(text);
				kidrefs.add(text);
			} else throw new InternalError("unrecognized tree element:" + kid.getClass());
		}
		String repr = representation.toString();
		String top = topBuilder.toString();
		LogUtil.debug("repr:" + representation);
		if (!kidrefs.isEmpty()) {
			kidrefs.add(0, repr);
			references.put(top, kidrefs);
		}
		ParserOutput ret = new ParserOutput(top, references);
		LogUtil.debug("compileBridiFromTree", top, ret);
		return ret;
	}
}
