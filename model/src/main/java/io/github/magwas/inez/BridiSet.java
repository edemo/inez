package io.github.magwas.inez;

import java.util.Map;

import lombok.Data;

@Data
public class BridiSet {
	final String topId;
	final Map<String, Bridi> bridis;
}