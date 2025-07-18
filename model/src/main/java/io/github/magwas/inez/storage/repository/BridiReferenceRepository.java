package io.github.magwas.inez.storage.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.magwas.inez.storage.model.BridiReference;

@Repository
public interface BridiReferenceRepository
		extends CrudRepository<BridiReference, String> {

	Set<BridiReference> findAllByBridiId(String id);

	Set<BridiReference> findAllBySumtiIdAndPosition(String sumtiId, int position);

	void deleteBybridiIdAndPositionAndSumtiId(String BridiId, Integer Position,
			String SumtiId);

}
