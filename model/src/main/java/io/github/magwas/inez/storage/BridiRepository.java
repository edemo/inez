package io.github.magwas.inez.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.magwas.inez.Bridi;

@Repository
interface BridiRepository extends CrudRepository<Bridi, String> {
	Iterable<Bridi> findAllByRepresentation(String representation);
}
