package guru.inject;

import guru.Experimental;

@Experimental
public interface Module {

	void configure(Binder binder);

}