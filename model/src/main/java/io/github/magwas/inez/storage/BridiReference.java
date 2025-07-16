package io.github.magwas.inez.storage;

import org.springframework.data.annotation.Id;

public record BridiReference(@Id String id, String bridiId, int position,
		String sumtiId) {
}