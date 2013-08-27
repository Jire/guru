package guru.inject;

import guru.Experimental;

import java.util.List;

@Experimental
public interface Binder {

	<T> void bind(Binding<T> binding);

	<B, T> List<B> find(Class<? extends Binding<T>> binding,
			Class<? extends T> type);

	void install(Module module);

}