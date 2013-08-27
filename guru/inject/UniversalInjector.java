package guru.inject;

import guru.Experimental;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

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
											&& areComparable(
													constantBinding.getType(),
													parameterType)) {
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

	private static boolean areComparable(Class<?> a, Class<?> b) {
		return a == b || primitiveToBoxed.get(a) == b
				|| a == primitiveToBoxed.get(b);
	}

	private static final Map<Class<?>, Class<?>> primitiveToBoxed = new HashMap<Class<?>, Class<?>>();

	static {
		// Very ugly fix... but I did research and this happened to be the
		// [only] recommended way.
		primitiveToBoxed.put(byte.class, Byte.class);
		primitiveToBoxed.put(short.class, Integer.class);
		primitiveToBoxed.put(int.class, Integer.class);
		primitiveToBoxed.put(long.class, Integer.class);
		primitiveToBoxed.put(float.class, Integer.class);
		primitiveToBoxed.put(double.class, Integer.class);
		primitiveToBoxed.put(char.class, Integer.class);
		primitiveToBoxed.put(boolean.class, Integer.class);
	}

}