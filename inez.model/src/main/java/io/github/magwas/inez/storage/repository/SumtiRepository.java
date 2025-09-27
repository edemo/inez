package io.github.magwas.inez.storage.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.magwas.inez.storage.model.Sumti;

@Repository
public interface SumtiRepository extends CrudRepository<Sumti, String> {
	Set<Sumti> findAllByRepresentation(String representation);
}
