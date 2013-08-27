package guru.inject;

public final class InjectionFailedException extends RuntimeException {

	private static final long serialVersionUID = -4766784979818900297L;

	public InjectionFailedException(Throwable cause) {
		super(cause);
	}

}