package io.music.playlist.exception;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

public class BaseRuntimeException extends RuntimeException {
    protected String code;

    public BaseRuntimeException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
