package guru.inject;

import guru.Experimental;

import java.lang.annotation.Annotation;

@Experimental
public final class LinkedBindingBuilder<T> extends TypedBindingBuilder<T> {

	protected LinkedBindingBuilder(Binder binder, Class<T> type) {
		super(binder, type);
	}

	public void to(Class<? extends T> implementation) {
		getBinder().bind(
				new ImplementationBinding<T>(getType(), implementation));
	}

	public ConstantBindingBuilder<T> annotatedWith(
			Class<? extends Annotation> annotation) {
		return new ConstantBindingBuilder<T>(getBinder(), getType(), annotation);
	}

}