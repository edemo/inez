package io.github.magwas.inez;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreCommand {
	BridiStoreOperation operation;
	String id;
}
