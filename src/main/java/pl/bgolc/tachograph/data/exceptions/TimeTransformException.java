package pl.bgolc.tachograph.data.exceptions;

import java.time.DateTimeException;

public class TimeTransformException extends Exception{

    public TimeTransformException() {

    }

    public TimeTransformException(String exception) {
        super(exception);
    }
}
