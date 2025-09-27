package io.github.magwas.inez;

import java.util.List;

import org.springframework.data.annotation.Id;

public record Bridi(@Id String id, String representation, List<String> references) {}
