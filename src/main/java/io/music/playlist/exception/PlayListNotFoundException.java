package io.music.playlist.exception;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

public class PlayListNotFoundException extends BaseRuntimeException {

    public PlayListNotFoundException(String message, String code) {
        super(message, code);
    }
}
