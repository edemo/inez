package io.github.magwas.inez;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BridiRepository extends CrudRepository<Bridi, String> {
	Iterable<Bridi> findAllByRepresentation(String representation);
}
