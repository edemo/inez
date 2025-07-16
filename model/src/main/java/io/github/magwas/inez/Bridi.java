package io.github.magwas.inez;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Bridi {
	@Id
	final String id;
	final String representation;
	@Builder.Default
	List<String> references = new ArrayList<>();
}
