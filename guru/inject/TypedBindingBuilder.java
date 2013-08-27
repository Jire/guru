package guru.inject;

import guru.Experimental;

@Experimental
public abstract class TypedBindingBuilder<T> extends BindingBuilder {

	private final Class<T> type;

	protected TypedBindingBuilder(Binder binder, Class<T> type) {
		super(binder);
		this.type = type;
	}

	public final Class<T> getType() {
		return type;
	}

}