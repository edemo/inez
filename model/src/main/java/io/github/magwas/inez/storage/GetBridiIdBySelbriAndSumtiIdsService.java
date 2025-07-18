package io.github.magwas.inez.storage;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.runtime.LogUtil;

@Service
public class GetBridiIdBySelbriAndSumtiIdsService {
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public Stream<String> apply(String selbriId, String sumtiId, int position) {
		Set<String> bridiesWithSumti = bridiReferenceRepository
				.findAllBySumtiIdAndPosition(selbriId, 0).stream()
				.map(ref -> ref.bridiId()).peek(x -> LogUtil.debug("initialset", x))
				.collect(Collectors.toSet());
		return bridiReferenceRepository
				.findAllBySumtiIdAndPosition(sumtiId, position).stream()
				.mapMulti((bridiReference, consumer) -> {
					LogUtil.debug(bridiReference, bridiesWithSumti);
					if (bridiesWithSumti.contains(bridiReference.bridiId())) {
						consumer.accept(bridiReference.bridiId());
					}
				});
	}

}
