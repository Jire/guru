package guru.inject;

import guru.Experimental;

@Experimental
public final class ScopedBinding<T> extends TypedBinding<T> {

	private final Scope scope;

	protected ScopedBinding(Class<T> type, Scope scope) {
		super(type);
		this.scope = scope;
	}

	public Scope getScope() {
		return scope;
	}

}