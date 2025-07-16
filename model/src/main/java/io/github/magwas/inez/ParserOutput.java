package io.github.magwas.inez;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ParserOutput {
	final String top;
	Map<String, List<String>> referenceMap = new HashMap<>();
}