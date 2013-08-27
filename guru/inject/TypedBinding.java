package guru.inject;

import guru.Experimental;

@Experimental
public abstract class TypedBinding<T> implements Binding<T> {

	private final Class<T> type;

	protected TypedBinding(Class<T> type) {
		this.type = type;
	}

	public final Class<T> getType() {
		return type;
	}

}