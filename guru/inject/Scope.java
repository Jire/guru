package guru.inject;

import guru.Experimental;

@Experimental
public interface Scope {

	<T> T apply(Class<T> type, T object);

	<T> T resolve(Class<T> type);

}