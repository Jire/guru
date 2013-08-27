package guru.inject;

import static guru.Preconditions.checkNotNull;
import static guru.Preconditions.checkState;
import guru.Experimental;

@Experimental
public abstract class AbstractModule implements Module {

	private Binder binder;

	@Override
	public final synchronized void configure(Binder binder) {
		checkState(this.binder == null, "Re-entry is not allowed!");
		this.binder = checkNotNull(binder);

		try {
			configure();
		} finally {
			this.binder = null;
		}
	}

	protected final Binder getBinder() {
		checkState(binder != null,
				"The binder can only be used during configuration.");
		return binder;
	}

	protected abstract void configure();

	protected final <T> LinkedBindingBuilder<T> bind(Class<T> type) {
		return new LinkedBindingBuilder<T>(getBinder(), type);
	}

	protected final ConstantBindingBuilder<?> bind(Object value) {
		return new ConstantBindingBuilder<Object>(getBinder(), value);
	}

}