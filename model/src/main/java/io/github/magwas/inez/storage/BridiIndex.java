package io.github.magwas.inez.storage;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class BridiIndex {
	@Id
	final String id;
	Set<String> references = new HashSet<>();

}
