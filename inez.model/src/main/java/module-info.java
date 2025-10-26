open module inez.model {
	exports io.github.magwas.inez;
	exports io.github.magwas.inez.element;
	exports io.github.magwas.inez.storage.model;
	exports io.github.magwas.inez.storage.repository;
	exports io.github.magwas.inez.functions;
	exports io.github.magwas.inez.query;
	exports io.github.magwas.inez.storage;

	requires transitive inez.parser;
	requires transitive spring.data.keyvalue;
	requires transitive spring.data.commons;
}
