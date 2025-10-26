package io.github.magwas.inez.parse.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.github.magwas.inez.parse.CreateParserFromTokensService;
import io.github.magwas.inez.parser.BridiParser;
import io.github.magwas.inez.parser.BridiParser.BridiContext;
import io.github.magwas.inez.parser.BridiParser.ParagraphContext;
import io.github.magwas.konveyor.annotations.Glue;

@Glue
public class CreateParserFromTokensServiceStub implements ParserOutputTestData {
	public static CreateParserFromTokensService stub() {
		CreateParserFromTokensService mock = mock(CreateParserFromTokensService.class);
		BridiParser bridiParsermock = mock(BridiParser.class);
		BridiContext bridiContext = mock(BridiContext.class);
		bridiContext.children = List.of(mock(ParseTree.class));
		ParagraphContext para = mock(ParagraphContext.class);
		para.children = List.of(bridiContext);
		when(bridiParsermock.paragraph()).thenReturn(para);
		when(mock.apply(any())).thenCallRealMethod();
		when(mock.apply(FROM_UNKNOWN_PARSER_INPUT)).thenReturn(bridiParsermock);
		return mock;
	}
}
