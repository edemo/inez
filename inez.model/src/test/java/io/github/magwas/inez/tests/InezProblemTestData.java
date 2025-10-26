package io.github.magwas.inez.tests;

import java.util.List;

import io.github.magwas.inez.InezProblem;
import io.github.magwas.inez.storage.model.tests.SumtiTestData;

public interface InezProblemTestData extends SumtiTestData {

	InezProblem PROBLEM =
			new InezProblem("each element is within a containerID", "each element is within a container", GO_SUMTI);
	List<InezProblem> PROBLEMS = List.of(PROBLEM);
}
