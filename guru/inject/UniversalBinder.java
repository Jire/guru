package guru.inject;

import guru.Experimental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Experimental
final class UniversalBinder implements Binder {

	private final Map<Class<? extends Binding<?>>, List<Binding<?>>> bindings;

	UniversalBinder(Map<Class<? extends Binding<?>>, List<Binding<?>>> bindings) {
		this.bindings = bindings;
	}

	UniversalBinder() {
		this(new HashMap<Class<? extends Binding<?>>, List<Binding<?>>>());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> void bind(Binding<T> binding) {
		List<Binding<?>> list = bindings.get(binding.getClass());
		if (list == null)
			list = new ArrayList<Binding<?>>();
		list.add(binding);
		bindings.put((Class<? extends Binding<?>>) binding.getClass(), list);
	}

	@Override
	public void install(Module module) {
		module.configure(this);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <B, T> List<B> find(Class<? extends Binding<T>> binding,
			Class<? extends T> type) {
		List<Binding<T>> list = new ArrayList<Binding<T>>();
		for (Binding<?> bind : bindings.get(binding))
			list.add((Binding<T>) bind);
		return (List<B>) bindings.get(binding);
	}

}