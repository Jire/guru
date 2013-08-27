package guru.inject;

import guru.Experimental;

import java.lang.annotation.Annotation;

@Experimental
public class ConstantBindingBuilder<T> extends TypedBindingBuilder<T> {

	private final Class<? extends Annotation> annotation;

	protected ConstantBindingBuilder(Binder binder, Class<T> type,
			Class<? extends Annotation> annotation) {
		super(binder, type);
		this.annotation = annotation;
	}

	public final Class<? extends Annotation> getAnnotation() {
		return annotation;
	}

	public void to(T value) {
		getBinder().bind(new ConstantBinding<T>(getType(), annotation, value));
	}

}