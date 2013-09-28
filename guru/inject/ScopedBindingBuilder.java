package guru.inject;

import guru.Experimental;

@Experimental
public final class ScopedBindingBuilder<T> extends TypedBindingBuilder<T> {

	protected ScopedBindingBuilder(Binder binder, Class<T> type) {
		super(binder, type);
	}

	public void in(Scope scope) {
		getBinder().bind(new ScopedBinding<T>(getType(), scope));
	}

}