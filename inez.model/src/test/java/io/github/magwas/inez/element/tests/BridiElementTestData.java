package io.github.magwas.inez.element.tests;

import java.util.Set;

import io.github.magwas.inez.parse.tests.IdTestData;

public interface BridiElementTestData extends IdTestData {

	Set<String> MY_FOLDER_CHILDREN = Set.of(
			ALICE_ID,
			HUMAN_ID,
			DOG_ID,
			MY_MODEL_IS_A_FOLDER_ID,
			DOG_IS_A_ANIMAL_ID,
			HUMAN_IS_A_ANIMAL_ID,
			ALICE_IS_A_HUMAN_ID);
}
