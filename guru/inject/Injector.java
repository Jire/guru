package guru.inject;

import guru.Experimental;

@Experimental
public interface Injector {

	Binder getBinder();

	<T> T create(Class<T> type);

}