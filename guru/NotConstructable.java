package guru;

import static java.lang.String.format;

@Experimental
public abstract class NotConstructable {

	protected NotConstructable() {
		throw new UnsupportedOperationException();
	}

	protected NotConstructable(String message, Object... messageArgs) {
		throw new UnsupportedOperationException(format(message, messageArgs));
	}

}