package io.github.magwas.inez.storage;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface BridiReferenceRepository
		extends CrudRepository<BridiReference, String> {

	Set<BridiReference> findAllByBridiId(String id);

	Set<BridiReference> findAllBySumtiIdAndPosition(String sumtiId, int position);

}
