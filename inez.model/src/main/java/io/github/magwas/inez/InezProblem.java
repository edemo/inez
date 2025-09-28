package io.github.magwas.inez;

import org.springframework.data.annotation.Id;

import io.github.magwas.inez.storage.model.Sumti;

public record InezProblem(@Id String id, String category, Sumti sumti) {}
