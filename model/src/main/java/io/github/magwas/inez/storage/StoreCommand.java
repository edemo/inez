package io.github.magwas.inez.storage;

import io.github.magwas.inez.Bridi;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreCommand {
	BridiStoreOperation operation;
	Bridi old;
	Bridi now;
}
