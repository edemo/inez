package io.github.magwas.inez.storage.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import io.github.magwas.inez.storage.model.BridiReference;

public interface BridiReferenceRepository
		extends CrudRepository<BridiReference, String> {

	Set<BridiReference> findAllByBridiId(String id);

	Set<BridiReference> findAllBySumtiIdAndPosition(String sumtiId, int position);

	void deleteBybridiIdAndPositionAndSumtiId(String BridiId, Integer Position,
			String SumtiId);

}
