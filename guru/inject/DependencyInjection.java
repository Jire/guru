package guru.inject;

import guru.Experimental;

@Experimental
public final class DependencyInjection {

	public static Injector createInjector(Module... modules) {
		Injector injector = createInjector();
		if (modules != null)
			for (Module module : modules)
				injector.getBinder().install(module);
		return injector;
	}

	public static Injector createInjector() {
		return new UniversalInjector(new UniversalBinder());
	}

}