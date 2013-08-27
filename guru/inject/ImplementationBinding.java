package guru.inject;

import guru.Experimental;

@Experimental
public final class ImplementationBinding<T> implements Binding<T> {

	private final Class<T> type;
	private final Class<? extends T> implementation;

	public ImplementationBinding(Class<T> type,
			Class<? extends T> implementation) {
		this.type = type;
		this.implementation = implementation;
	}

	Class<T> getType() {
		return type;
	}

	Class<? extends T> getImplementation() {
		return implementation;
	}

}