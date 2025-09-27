package io.github.magwas.inez;

import java.util.List;

import io.github.magwas.inez.storage.model.SumtiTestData;

public interface InezProblemTestData extends SumtiTestData {

	InezProblem PROBLEM =
			new InezProblem("each element is within a containerID", "each element is within a container", GO1_SUMTI);
	List<InezProblem> PROBLEMS = List.of(PROBLEM);
}
