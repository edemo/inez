package io.github.magwas.inez.storage;

public interface BridiStoreChangeListener {
	void listen(StoreCommand operation);
}