package guru.inject;

import guru.Experimental;

@Experimental
public abstract class BindingBuilder {

	private final Binder binder;

	protected BindingBuilder(Binder binder) {
		this.binder = binder;
	}

	public final Binder getBinder() {
		return binder;
	}

}