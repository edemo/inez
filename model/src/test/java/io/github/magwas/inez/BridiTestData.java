package io.github.magwas.inez;

import java.util.List;

public interface BridiTestData {
	String THING_REPR = "thing";
	Bridi THING = new Bridi(THING_REPR, THING_REPR);
	String SUMTI_REP = "sumti";
	Bridi SUMTI = new Bridi(SUMTI_REP, SUMTI_REP);
	String BRIDI_REPR = "bridi";
	Bridi BRIDI = new Bridi(BRIDI_REPR, BRIDI_REPR);
	String IS_A_REPR = "{0} is a {1}";
	Bridi IS_A = new Bridi(IS_A_REPR, IS_A_REPR);
	String SUMTI_IS_A_THING_REPR = "{sumti} is a {thing}";
	Bridi SUMTI_IS_A_THING = new Bridi(SUMTI_IS_A_THING_REPR,
			SUMTI_IS_A_THING_REPR, List.of(IS_A_REPR, SUMTI_REP, THING_REPR));
	String RECURSIVE_BRIDI_REPR = "{{sumti} is a {thing}} is a {@thing}";
	String RECURSIVE_BRIDI_REPR_NOREFERENCE = "{{sumti} is a {thing}} is a {thing}";
	String INPUT_BAD = "{bridi is a {thing}";
	String STUFF_ID = "stuff";
	List<String> RECURSIVE_BRIDI_REFERENCES = List.of(IS_A_REPR,
			SUMTI_IS_A_THING_REPR, THING_REPR);
	Bridi RECURSIVE_BRIDI = new Bridi(RECURSIVE_BRIDI_REPR_NOREFERENCE,
			RECURSIVE_BRIDI_REPR_NOREFERENCE, RECURSIVE_BRIDI_REFERENCES);
	List<Bridi> PARSED_INPUT = List.of(SUMTI, THING, IS_A, SUMTI_IS_A_THING,
			RECURSIVE_BRIDI);

	String GO_REPRESENTATION = "go";

	Bridi GO1 = new Bridi("go1", GO_REPRESENTATION);
	Bridi GO2 = new Bridi("go2", GO_REPRESENTATION);

	String DESCRIPTION_SUMTI_IS_A_THING_STRING = "sumti is a thing";

}
