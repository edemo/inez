package io.github.magwas.inez.storage.model;

import org.springframework.data.annotation.Id;

public record BridiReference(@Id String id, String bridiId, String selbriId, int position, String sumtiId) {}
