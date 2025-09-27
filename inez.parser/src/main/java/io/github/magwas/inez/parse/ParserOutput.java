package io.github.magwas.inez.parse;

import java.util.List;
import java.util.Map;

public record ParserOutput(String top, Map<String, List<String>> referenceMap) {}
