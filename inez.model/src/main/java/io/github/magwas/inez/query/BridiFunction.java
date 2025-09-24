package io.github.magwas.inez.query;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import io.github.magwas.inez.Bridi;

public interface BridiFunction {

	Stream<Bridi> apply(String top, List<String> partList, int notAnyIndex, List<Set<String>> foundIds);
}
