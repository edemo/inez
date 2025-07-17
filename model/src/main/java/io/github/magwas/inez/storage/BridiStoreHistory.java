package io.github.magwas.inez.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.magwas.inez.StoreCommand;

@Repository
public interface BridiStoreHistory
		extends CrudRepository<StoreCommand, String> {
}