package io.github.magwas.inez.query;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;

@Service
public class CreateBridisFromDefinitionService {

	@Autowired
	CreateBridisFromQueryService createBridisFromQuery;

	public Stream<Bridi> apply(String definitionName) throws Error {
		String elementDefinition;
		try {
			elementDefinition = loadResource(definitionName);
		} catch (IOException e) {
			throw new Error(e);
		}
		return createBridisFromQuery.apply(elementDefinition);
	}

	private String loadResource(String definitionName) throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String elementDefinition = new String(
				classloader.getResourceAsStream(definitionName).readAllBytes());
		return elementDefinition;
	}

}
