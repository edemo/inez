package io.github.magwas.inez.query.tests;

import java.util.Set;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.parse.tests.RepresentationTestData;
import io.github.magwas.inez.tests.BridiTestData;

public interface QueryProcessorTestData extends RepresentationTestData, BridiTestData {
	String TEST_TEXT =
			"""
			{{sumti} is a {THING}} is a {THING}
			{{THING} is a {THING}} is a {THING}
			{alice} {{eats} {banana}}
			{alice} {{eats} {chips}}
			{bob} {{eats} {banana}}
			{bob} {{eats} {chips}}
			{cecile} {{eats} {banana}}
			{cecile} {{looks at} {banana}}
			{sumti} is a {THING}
			{THING} is a {THING}""";
	String SAVE_CLASS_NAME = "io.github.magwas.inez.functions.Save";
	Set<Bridi> SIMPLE_QUERY_OUTPUT =
			Set.of(TAUTOLOGY, SUMTI_IS_A_THING_IS_A_THING, TAUTOLOGY_IS_A_THING, SUMTI_IS_A_THING);
}
