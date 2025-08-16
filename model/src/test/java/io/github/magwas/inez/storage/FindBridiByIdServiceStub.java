package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.parse.ParserConstants;

public class FindBridiByIdServiceStub implements BridiTestData {
	public static FindBridiByIdService stub() {
		FindBridiByIdService mock = mock(FindBridiByIdService.class);
		when(mock.apply(GO1_ID)).thenReturn(Optional.of(GO1));
		when(mock.apply(GO2_ID)).thenReturn(Optional.of(GO2));
		when(mock.apply(SUMTI_IS_A_THING_IS_A_THING_ID))
				.thenReturn(Optional.of(SUMTI_IS_A_THING_IS_A_THING));
		when(mock.apply(TAUTOLOGY_IS_A_THING_ID))
				.thenReturn(Optional.of(TAUTOLOGY_IS_A_THING));
		when(mock.apply(THING_ID)).thenReturn(Optional.of(THING));
		when(mock.apply("thung")).thenReturn(Optional.of(THING_CHANGED));
		when(mock.apply(SUMTI_ID)).thenReturn(Optional.of(SUMTI));
		when(mock.apply(BRIDI_ID)).thenReturn(Optional.of(BRIDI));
		when(mock.apply(IS_A_ID)).thenReturn(Optional.of(IS_A));
		when(mock.apply(ParserConstants.QUERY_BRIDI_ID))
				.thenReturn(Optional.of(ANY));
		when(mock.apply(SUMTI_IS_A_THING_ID))
				.thenReturn(Optional.of(SUMTI_IS_A_THING));
		when(mock.apply(TAUTOLOGY_ID)).thenReturn(Optional.of(TAUTOLOGY));
		when(mock.apply(THING_IS_A_SUMTI_ID))
				.thenReturn(Optional.of(THING_IS_A_SUMTI));
		when(mock.apply(GOD_ID)).thenReturn(Optional.of(GOD));
		when(mock.apply(HUMAN_ID)).thenReturn(Optional.of(HUMAN));
		when(mock.apply(MY_MODEL_ID)).thenReturn(Optional.of(MY_MODEL));
		when(mock.apply(ALICE_ID)).thenReturn(Optional.of(ALICE));
		return mock;
	}
}
