package pl.bgolc.tachograph.data.exceptions;

import java.time.DateTimeException;

public class DateTransformException extends Exception {

    public DateTransformException() {

    }

    public DateTransformException(String exception) {
        super(exception);
    }
}
