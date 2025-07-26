package io.github.magwas.inez.storage.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.magwas.inez.storage.model.BridiReference;

@Repository
public interface BridiReferenceRepository
		extends CrudRepository<BridiReference, String> {

	Set<BridiReference> findAllByBridiId(String id);

	Set<BridiReference> findAllBySelbriIdAndSumtiIdAndPosition(String selbriId,
			String sumtiId, int position);

	void deleteBybridiIdAndPositionAndSumtiId(String BridiId, Integer Position,
			String SumtiId);

	Optional<BridiReference> findByBridiIdAndPosition(String bridiId, int i);

	Set<BridiReference> findAllByBridiIdAndPosition(String bridiId, int i);

}
