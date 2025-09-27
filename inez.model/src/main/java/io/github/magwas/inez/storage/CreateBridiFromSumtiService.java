package io.github.magwas.inez.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.runtime.LogUtil;

@Service
public class CreateBridiFromSumtiService {

	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public Bridi apply(final Sumti sumti) {
		final Map<Integer, BridiReference> map = new HashMap<>();
		Set<BridiReference> refs = bridiReferenceRepository.findAllByBridiId(sumti.id());
		LogUtil.debug("refs:" + refs);
		refs.forEach(ref -> map.put(ref.position(), ref));
		LogUtil.debug("map:" + map);
		List<String> parts = new ArrayList<>();
		int i = 0;
		while (map.containsKey(i)) {
			parts.add(map.get(i).sumtiId());
			i++;
		}
		Bridi bridi = new Bridi(sumti.id(), sumti.representation(), parts);
		LogUtil.debug("bridi:" + bridi);
		return bridi;
	}
}
