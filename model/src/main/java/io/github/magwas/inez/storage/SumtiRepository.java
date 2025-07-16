package io.github.magwas.inez.storage;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SumtiRepository extends CrudRepository<Sumti, String> {
	Set<Sumti> findAllByRepresentation(String representation);

}
