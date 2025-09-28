package io.github.magwas.inez.parse;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.parser.BridiLexer;
import io.github.magwas.inez.parser.BridiParser;
import io.github.magwas.kodekonveyorannotations.Glue;

@Glue
@Service
public class CreateParserFromTokensService {

	BridiParser apply(final String input) {
		BridiLexer lexer = new BridiLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		BridiParser parser = new BridiParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ThrowingErrorListener(input));
		return parser;
	}
}
