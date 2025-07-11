package io.github.magwas.inez;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import io.github.magwas.kodekonveyorannotations.Glue;

@Glue
public class ThrowingErrorListener extends BaseErrorListener {

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
			final int line, final int charPositionInLine, final String msg,
			RecognitionException e) {
		throw new ParseCancellationException(
				line + ":" + charPositionInLine + " " + msg);
	}
}
