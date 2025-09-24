package io.github.magwas.inez.storage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.magwas.inez.InezProblem;

@Repository
public interface ProblemRepository extends CrudRepository<InezProblem, String> {

}
