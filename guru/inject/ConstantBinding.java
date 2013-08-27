package guru.inject;

import guru.Experimental;

import java.lang.annotation.Annotation;

@Experimental
public final class ConstantBinding<T> implements Binding<T> {

	private final Class<T> type;
	private final Class<? extends Annotation> annotation;
	private final T value;

	public ConstantBinding(Class<T> type,
			Class<? extends Annotation> annotation, T value) {
		this.type = type;
		this.annotation = annotation;
		this.value = value;
	}

	public Class<T> getType() {
		return type;
	}

	public Class<? extends Annotation> getAnnotation() {
		return annotation;
	}

	public T getValue() {
		return value;
	}

}