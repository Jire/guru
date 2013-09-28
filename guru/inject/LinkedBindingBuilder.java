package guru.inject;

import guru.Experimental;

@Experimental
public final class LinkedBindingBuilder<T> extends TypedBindingBuilder<T> {

	protected LinkedBindingBuilder(Binder binder, Class<T> type) {
		super(binder, type);
	}

	public ScopedBindingBuilder<T> to(Class<? extends T> implementation) {
		getBinder().bind(
				new ImplementationBinding<T>(getType(), implementation));
		return new ScopedBindingBuilder<T>(getBinder(), getType());
	}

}