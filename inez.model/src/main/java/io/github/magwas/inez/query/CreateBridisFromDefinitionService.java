package io.github.magwas.inez.query;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;

@Service
public class CreateBridisFromDefinitionService {

	@Autowired
	CreateBridisFromQueryService createBridisFromQuery;

	public Stream<Bridi> apply(final String definitionName) throws Error {
		String elementDefinition;
		try {
			elementDefinition = loadResource(definitionName);
		} catch (IOException e) {
			throw new Error(e);
		}
		return createBridisFromQuery.apply(elementDefinition);
	}

	private String loadResource(final String definitionName) throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = classloader.getResourceAsStream(definitionName)) {
			return new String(inputStream.readAllBytes());
		}
	}
}
