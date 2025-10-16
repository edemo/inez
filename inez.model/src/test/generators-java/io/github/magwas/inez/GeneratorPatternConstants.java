package io.github.magwas.inez;

public interface GeneratorPatternConstants {
	String GET_RELATIVE_FOR_BRIDI_ELEMENT_PATTERN = "\twhen(mock.apply({0}_ID, {1}_ID, {2}, {3})).thenReturn(Stream.of({4}_ID));\n";
	String BRIDI_TEST_DATA_PATTERN = "\tBridi {0} = new Bridi({1}, {2},{3});\n";
	String BRIDI_TEST_DATA_HEADER = """
			import java.util.List;
			import io.github.magwas.inez.parse.IdTestData;
			import io.github.magwas.inez.parse.IdUtil;
			import io.github.magwas.inez.parse.ParserConstants;
			import io.github.magwas.inez.parse.ReferenceTestData;

			""";
	String SUMTI_REPOSITORY_STUB_PATTERN = "\twhen(mock.findById({0}_ID)).thenReturn(Optional.of({0}_SUMTI));\n";
	String SUMTI_REPOSITORY_STUB_EXTRA = "\twhen(mock.findAllByRepresentation(GO_REPR)).thenReturn(Set.of(GO_SUMTI, GO2_SUMTI));\n";
	String SUMTI_REPOSITORY_STUB_HEADER = """
			import java.util.Set;
			import java.util.Optional;
			import io.github.magwas.inez.storage.model.SumtiTestData;
			""";
	String SUMTI_TESTDATA_PATTERN = "\tSumti {0}_SUMTI = new Sumti({1}, {2});\n";
	String BRIDI_REFERENCE_PATTERN = """
			BridiReference {0}_REFERENCE_0 = new BridiReference(
					IdUtil.createID({0}_ID+0), {0}_ID, {1}_ID, 0, {1}_ID);
			BridiReference {0}_REFERENCE_1 = new BridiReference(
					IdUtil.createID({0}_ID+1), {0}_ID, {1}_ID, 1, {2}_ID);
			BridiReference {0}_REFERENCE_2 = new BridiReference(
					IdUtil.createID({0}_ID+2), {0}_ID, {1}_ID, 2, {3}_ID);
			List<BridiReference> {0}_REFERENCELIST = List.of(
					{0}_REFERENCE_0, {0}_REFERENCE_1,
					{0}_REFERENCE_2);
			""";
	String BRIDI_REFERENCE_HEADER = """
			import io.github.magwas.inez.parse.IdTestData;
			import io.github.magwas.inez.parse.IdUtil;
			import java.util.List;
			""";
	String FIND_ALL_BY_REPRESENTATION_STUB_HEADER = """
			import java.util.stream.Stream;

			import io.github.magwas.inez.BridiTestData;
			import io.github.magwas.inez.parse.ParserConstants;
			""";
	String FIND_ALL_ID_BY_REPRESENTATION_STUB_PATTERN = "\t\twhen(mock.apply({0})).thenAnswer((args) -> Stream.of({1}));\n";
	String FIND_ALL_BY_REPRESENTATION_STUB_PATTERN = "\t\twhen(mock.apply({0})).thenAnswer((args) -> Stream.of({1}));\n";

}
