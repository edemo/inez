open module inez.model.test {
	exports io.github.magwas.inez.tests;
	exports io.github.magwas.inez.element.tests;
	exports io.github.magwas.inez.storage.repository.tests;
	exports io.github.magwas.inez.query.tests;
	exports io.github.magwas.inez.storage.tests;
	exports io.github.magwas.inez.storage.model.tests;

	requires transitive inez.model;
	requires transitive inez.parser.test;
	requires transitive spring.data.commons;
	requires spring.test;
	requires konveyor.testing;
}
