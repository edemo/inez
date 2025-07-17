package io.github.magwas.inez.query;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.github.magwas.inez.parser.BridiParser;
import io.github.magwas.inez.parser.BridiParser.BridiContext;
import io.github.magwas.kodekonveyorannotations.Glue;

@Glue
public class CreateParserFromTokensStub implements ParserOutputTestData {
	public static CreateParserFromTokens stub() {
		CreateParserFromTokens mock = mock(CreateParserFromTokens.class);
		BridiParser bridiParsermock = mock(BridiParser.class);
		BridiContext bridiContext = mock(BridiContext.class);
		bridiContext.children = List.of(mock(ParseTree.class));
		when(bridiParsermock.bridi()).thenReturn(bridiContext);
		when(mock.apply(any())).thenCallRealMethod();
		when(mock.apply(INPUT_FROM_UNKNOWN_PARSER)).thenReturn(bridiParsermock);
		return mock;
	}
}
