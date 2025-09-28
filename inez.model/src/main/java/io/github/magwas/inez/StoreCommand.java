package io.github.magwas.inez;

import org.springframework.data.annotation.Id;

public record StoreCommand(@Id Integer id, BridiStoreOperation operation, Bridi old, Bridi now) {}
