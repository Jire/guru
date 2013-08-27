package guru.inject;

import guru.Experimental;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

@Experimental
final class UniversalInjector implements Injector {

	private final Binder binder;

	UniversalInjector(Binder binder) {
		this.binder = binder;
	}

	@Override
	public Binder getBinder() {
		return binder;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T create(Class<T> type) {
		for (Object binding : getBinder()
				.find((Class<? extends Binding<T>>) ImplementationBinding.class,
						type)) {
			Class<T> implementation = ((ImplementationBinding) binding)
					.getImplementation();
			try {
				for (Constructor<?> constructor : implementation
						.getDeclaredConstructors()) {
					if (!constructor.isAnnotationPresent(Inject.class))
						continue;
					Class<?>[] parameterTypes = constructor.getParameterTypes();
					Object[] parameters = new Object[parameterTypes.length];
					parameters: for (int i = 0; i < parameterTypes.length; i++) {
						Class<?> parameterType = parameterTypes[i];
						Annotation[] parameterAnnotations = constructor
								.getParameterAnnotations()[i];
						if (parameterAnnotations.length > 0) {
							for (Annotation parameterAnnotation : constructor
									.getParameterAnnotations()[i]) {
								Class<? extends Annotation> annotationType = parameterAnnotation
										.annotationType();
								for (Object obinding : getBinder()
										.find((Class<? extends Binding<T>>) ConstantBinding.class,
												type)) {
									ConstantBinding constantBinding = ((ConstantBinding) obinding);
									if (constantBinding.getAnnotation() == annotationType
											&& constantBinding.getType() == parameterType) {
										parameters[i] = constantBinding
												.getValue();
										continue parameters;
									}
								}
							}
						}
					}
					constructor.setAccessible(true);
					return (T) constructor.newInstance(parameters);
				}
				return (T) (implementation.newInstance());
			} catch (Exception e) {
				throw new InjectionFailedException(e);
			}
		}

		return null;
	}

}