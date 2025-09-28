package io.github.magwas.inez;

public interface InezPlugin {
	/*
	 * A plugin probably does some of the followings:
	 * - adds a model to the "system-meta" container.
	 * the initialize method returns the id of it, or null
	 *
	 * - publishes some OSGI services referenced in the metamodel
	 *
	 */
	String initialize(Inez inez);
}
