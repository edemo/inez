package io.github.magwas.inez.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.junit5.service.ServiceExtension;

@ExtendWith(ServiceExtension.class)
@Tag("plugin")
public class ParseTextPluginTest {

	@InjectService
	ParseTextService parseText;

	@Test
	void test() {
		ParserOutput actual = parseText.apply("alice").toList().get(0);
		assertEquals(new ParserOutput("alice", Map.of()), actual);
	}
}
