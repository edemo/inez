package io.github.magwas.inez.element;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.inez.TestConfig;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class BridiElementEndToEndTest implements BridiTestData {

	@Autowired
	SumtiRepository sumtiRepository;

	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	@Autowired
	BridiElementSystemInitializationService bridiElementSystemInitialization;

	@Autowired
	InezImpl inez;

	@Autowired
	BridiElementFactory bridiElementFactory;

	@BeforeEach
	void setUp() {
		sumtiRepository.findAll().forEach(x -> sumtiRepository.delete(x));
		bridiReferenceRepository.findAll()
				.forEach(x -> bridiReferenceRepository.delete(x));
	}

	@Test
	void test() throws IOException {
        assertSame(inez.getBridiReferenceRepository(), bridiReferenceRepository);
        assertSame(bridiElementSystemInitialization.inez, inez);
		bridiElementSystemInitialization.apply();
		BridiElement elementModel = bridiElementFactory.apply(ELEMENT_MODEL_ID);
		assertTrue(elementModel.getChildren()
				.anyMatch(x -> x.getReferences().map(y -> y.id).toList()
						.contains(ElementConstants.IS_FUNCTION_FOR_ID)));
		BridiElement root = bridiElementFactory.apply(ROOT_ID);
		String rootXml = loadResourceAsString("root.xml");
		String theXml = root.toXml();
		assertEquals(rootXml, theXml);
		inez.createFromdefinitions("mymodel.definition").toArray();

		BridiElement element = bridiElementFactory.apply(CONTAINS_ELEMENT_ID);

		assertEquals(CONTAINS_ELEMENT_ID, element.id);
		assertEquals(CONTAINS_ELEMENT_REPR, element.getRepresentation());

		assertEquals(THING_ID, element.getType().id);
		assertEquals(CONTAINS_ELEMENT_REFERENCES,
				element.getReferences().map(x -> x.id).toList());
		assertEquals(List.of(), element.getChildren().toList());
		assertEquals(MY_MODEL_ID, element.getParent().id);

		element = bridiElementFactory.apply(MY_MODEL_ID);

		assertEquals(MY_MODEL_ID, element.id);
		assertEquals(MY_MODEL_REPR, element.getRepresentation());
		assertEquals(FOLDER_ID, element.getType().id);
		assertEquals(List.of(), element.getReferences().toList());
		assertEquals(MY_FOLDER_CHILDREN,
				element.getChildren().map(x -> x.id).collect(Collectors.toSet()));
		assertEquals(ROOT_ID, element.getParent().id);
		String xml = loadResourceAsString("mymodel.xml");
		assertEquals(xml, element.toXml());
		assertTrue(element.isInstance(CONTAINER_ID));
		assertTrue(element.isInstance(FOLDER_ID));
		assertTrue(element.isInstance(THING_ID));
	}

	private String loadResourceAsString(String definitionName)
			throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = classloader.getResourceAsStream(definitionName)) {
			return new String(inputStream.readAllBytes());
		}
	}

}
