package io.github.magwas.inez;

import io.github.magwas.konveyor.annotations.Glue;

@Glue
public interface BridiStoreChangeListener {
	void listen(StoreCommand operation);
}
