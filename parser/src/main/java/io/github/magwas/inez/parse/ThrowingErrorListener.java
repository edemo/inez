package io.github.magwas.inez.parse;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import io.github.magwas.kodekonveyorannotations.Glue;

@Glue
public class ThrowingErrorListener extends BaseErrorListener {

	private String input;

	public ThrowingErrorListener(String input) {
		this.input = input;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
			final int line, final int charPositionInLine, final String msg,
			RecognitionException e) {
		String[] splat = input.split("\n");
		String theline = null;
		if(splat.length>=line)
		theline = splat[line-1];
		String message = line + ":" + charPositionInLine + " " + msg+
				"\n"+theline+"\n"+" ".repeat(charPositionInLine)+"^";
		System.err.println(message);
		throw new ParseCancellationException(message);
	}
}
