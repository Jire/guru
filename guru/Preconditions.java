package guru;

import static java.lang.String.format;

@Experimental
public final class Preconditions extends NotConstructable {

	public static <T> T checkNotNull(@Nullable T reference) {
		return checkNotNull(reference, null);
	}

	public static <T> T checkNotNull(@Nullable T reference,
			String errorMessage, Object... errorArguments) {
		if (reference == null) {
			if (errorMessage != null) {
				throw new NullPointerException(format(errorMessage,
						errorArguments));
			}
			throw new NullPointerException();
		}
		return reference;
	}

	public static void checkArgument(boolean expression) {
		checkArgument(expression, null);
	}

	public static void checkArgument(boolean expression, String errorMessage,
			Object... errorArguments) {
		if (!expression) {
			if (errorMessage != null) {
				throw new IllegalArgumentException(format(errorMessage,
						errorArguments));
			}
			throw new IllegalArgumentException();
		}
	}

	public static void checkState(boolean expression) {
		checkState(expression, null);
	}

	public static void checkState(boolean expression, String errorMessage,
			Object... errorArguments) {
		if (!expression) {
			if (errorMessage != null) {
				throw new IllegalStateException(format(errorMessage,
						errorArguments));
			}
			throw new IllegalStateException();
		}
	}

	private Preconditions() {
		super();
	}

}