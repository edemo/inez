package io.github.magwas.inez;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.junit5.service.ServiceExtension;

import io.github.magwas.inez.storage.model.Sumti;

@ExtendWith(ServiceExtension.class)
@Tag("plugin")
class InezPluginTest {

	@InjectService
	Inez inez;

	@Test
	void test() {
		Sumti actual = inez.createSumti("hello", "world");
		assertEquals(new Sumti("hello", "world"), actual);
	}
}
