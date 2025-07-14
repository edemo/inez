package io.github.magwas.inez.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BridiIndexRepository
		extends CrudRepository<BridiIndex, String> {

}
