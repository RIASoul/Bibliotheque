package exception;

import java.io.IOException;

public class LivreIOException extends LivreException {
    public LivreIOException(String message, IOException cause) {
        super(message);
        initCause(cause);
    }
}
