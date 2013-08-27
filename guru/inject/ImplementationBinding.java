package guru.inject;

import guru.Experimental;

@Experimental
public final class ImplementationBinding<T> extends TypedBinding<T> {

	private final Class<? extends T> implementation;

	public ImplementationBinding(Class<T> type,
			Class<? extends T> implementation) {
		super(type);
		this.implementation = implementation;
	}

	public Class<? extends T> getImplementation() {
		return implementation;
	}

}