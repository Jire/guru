package guru.inject;

import guru.Experimental;

import java.lang.annotation.Annotation;

@Experimental
public class ConstantBindingBuilder<T> extends TypedBindingBuilder<T> {

	private final T value;

	@SuppressWarnings("unchecked")
	protected ConstantBindingBuilder(Binder binder, T value) {
		super(binder, (Class<T>) value.getClass());
		this.value = value;
	}

	public final void with(Class<? extends Annotation> annotation) {
		getBinder().bind(new ConstantBinding<T>(getType(), annotation, value));
	}

}