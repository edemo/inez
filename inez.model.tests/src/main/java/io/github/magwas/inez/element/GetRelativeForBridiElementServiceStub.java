package io.github.magwas.inez.element;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

public class GetRelativeForBridiElementServiceStub
		implements BridiElementTestData {
	public static GetRelativeForBridiElementService stub() {
		GetRelativeForBridiElementService mock = mock(
				GetRelativeForBridiElementService.class);
		when(mock.apply(HUMAN_ID, IS_A_ID, 1, 2)).thenReturn(Stream.of(THING_ID));
		when(mock.apply(ROOT_ID, IS_A_ID, 1, 2))
				.thenReturn(Stream.of(CONTAINER_ID));
		when(mock.apply(MY_MODEL_ID, IS_A_ID, 1, 2))
				.thenReturn(Stream.of(FOLDER_ID));
		when(mock.apply(FOLDER_ID, IS_A_ID, 1, 2))
				.thenReturn(Stream.of(CONTAINER_ID));
		return mock;
	}

}
