package io.github.magwas.inez.element.tests;

import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.Inez;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.inez.element.BridiElementSystemInitializationService;
import io.github.magwas.inez.element.ElementConstants;
import io.github.magwas.konveyor.runtime.LoggerService;
import io.github.magwas.konveyor.testing.TestBase;
import io.github.magwas.konveyor.testing.TestUtil;

class BridiElementSystemInitializationServiceTest extends TestBase implements ElementConstants {

	@InjectMocks
	public BridiElementSystemInitializationService bridiElementSystemInitialization;

	Inez inez;
	LoggerService logger;

	@BeforeEach
	@Override
	public void setUp() throws Throwable {
		super.setUp();
		inez = TestUtil.dependency(bridiElementSystemInitialization, InezImpl.class);
		logger = TestUtil.dependency(bridiElementSystemInitialization, LoggerService.class);
		bridiElementSystemInitialization.apply();
	}

	@Test
	@DisplayName("creates sumti IS_A")
	void test() throws IOException {
		verify(inez).createSumti(IS_A_ID, IS_A_REPR);
	}

	@Test
	@DisplayName("creates sumti CONTAINS")
	void test1() throws IOException {
		verify(inez).createSumti(CONTAINS_ID, CONTAINS_REPR);
	}

	@Test
	@DisplayName("creates sumti ROOT")
	void test2() throws IOException {
		verify(inez).createSumti(ROOT_ID, ROOT_ID);
	}

	@Test
	@DisplayName("creates sumti THING")
	void test3() throws IOException {
		verify(inez).createSumti(THING_ID, THING_ID);
	}

	@Test
	@DisplayName("creates sumti CONTAINER")
	void test4() throws IOException {
		verify(inez).createSumti(CONTAINER_ID, CONTAINER_ID);
	}

	@Test
	@DisplayName("creates sumti UNPLACED")
	void test5() throws IOException {
		verify(inez).createSumti(UNPLACED_ID, UNPLACED_ID);
	}

	@Test
	@DisplayName("creates sumti TRUE")
	void test6() throws IOException {
		verify(inez).createSumti(TRUE_ID, TRUE_ID);
	}

	@Test
	@DisplayName("creates sumti FALSE")
	void test7() throws IOException {
		verify(inez).createSumti(FALSE_ID, FALSE_ID);
	}

	@Test
	@DisplayName("creates sumti IS_FUNCTION_FOR")
	void test8() throws IOException {
		verify(inez).createSumti(IS_FUNCTION_FOR_ID, IS_FUNCTION_FOR_REPR);
	}

	@Test
	@DisplayName("creates sumti SAVE_FUNCTION_REF")
	void test9() throws IOException {
		verify(inez).createSumti(SAVE_FUNCTION_REF_ID, SAVE_FUNCTION_REF_ID);
	}

	@Test
	@DisplayName("creates sumti DOSAVE")
	void test10() throws IOException {
		verify(inez).createSumti(DOSAVE_ID, DOSAVE_REPR);
	}

	@Test
	@DisplayName("creates sumti DIAGRAM")
	void test11() throws IOException {
		verify(inez).createSumti(DIAGRAM_ID, DIAGRAM_ID);
	}

	@Test
	@DisplayName("creates sumti DIAGRAM_ELEMENT")
	void test12() throws IOException {
		verify(inez).createSumti(DIAGRAM_ELEMENT_ID, DIAGRAM_ELEMENT_REPR);
	}

	@Test
	@DisplayName("creates bridis from the definition file")
	void test13() throws IOException {
		verify(inez).createFromdefinitions(ELEMENT_DEFINITIONS_RESOURCE);
	}

	@Test
	@DisplayName("logs success")
	void test14() throws IOException {
		verify(logger).info("BridiElement system initialized");
	}
}
