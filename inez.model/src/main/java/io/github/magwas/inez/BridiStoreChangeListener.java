package io.github.magwas.inez;

import io.github.magwas.kodekonveyorannotations.Glue;

@Glue
public interface BridiStoreChangeListener {
	void listen(StoreCommand operation);
}