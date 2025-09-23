package io.github.magwas.inez.element;

import java.util.List;
import java.util.Set;

import io.github.magwas.inez.parse.BridiFieldTestData;
import io.github.magwas.inez.parse.IdUtil;

public interface BridiElementTestData extends BridiFieldTestData {
	String DOG_ID = "id:dog";
	String CONTAINS_ID = "CONTAINS";
	String ROOT_ID = "ROOT";
	String ELEMENT_MODEL_ID = "id:elementModel";
	String CONTAINER_ID = "CONTAINER";
	String CONTAINS_ELEMENT_REPR = "{my model} contains {alice}";
	String CONTAINS_ELEMENT_ID = IdUtil.createID(CONTAINS_ELEMENT_REPR);
	String MY_MODEL_REPR = "my model";
	String MY_MODEL_ID = IdUtil.createID(MY_MODEL_REPR);
	String FOLDER_ID = IdUtil.createID("folder");
	String ALICE_IS_A_HUMAN_ID = "id:{alice} is a {human}";
	String HUMAN_IS_A_ANIMAL_ID = "id:{human} is a {animal}";
	String DOG_IS_A_ANIMAL_ID = "id:{dog} is a {animal}";
	String MY_MODEL_IS_A_FOLDER_ID = "id:{my model} is a {folder}";
	List<String> CONTAINS_ELEMENT_REFERENCES = List.of(CONTAINS_ID, MY_MODEL_ID,
			ALICE_ID);
	String HUMAN_REPR = "human";
	String HUMAN_ID = IdUtil.createID(HUMAN_REPR);
	Set<String> MY_FOLDER_CHILDREN = Set.of(ALICE_ID, HUMAN_ID, DOG_ID,
			MY_MODEL_IS_A_FOLDER_ID, DOG_IS_A_ANIMAL_ID, HUMAN_IS_A_ANIMAL_ID,
			ALICE_IS_A_HUMAN_ID);

}
