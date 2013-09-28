package guru.inject;

import guru.Experimental;

import java.util.HashMap;
import java.util.Map;

@Experimental
public enum Scopes implements Scope {

	DEFAULT,

	SINGLETON {

		private final Map<String, Object> singletons = new HashMap<String, Object>();

		@Override
		public <T> T apply(Class<T> type, T object) {
			if (!singletons.containsKey(type.getName()))
				singletons.put(type.getName(), object);
			return object;
		}

		@Override
		@SuppressWarnings("unchecked")
		public <T> T resolve(Class<T> type) {
			Object object = singletons.get(type.getName());
			return object == null ? null : (T) object;
		}

	};

	@Override
	public <T> T apply(Class<T> type, T object) {
		return object;
	}

	@Override
	public <T> T resolve(Class<T> type) {
		return null;
	}

}