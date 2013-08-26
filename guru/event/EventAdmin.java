package guru.event;

import guru.Experimental;

@Experimental
public interface EventAdmin {

	void dispatch(Event event);

	void register(EventListener listener);

}