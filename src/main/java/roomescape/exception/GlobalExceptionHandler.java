package roomescape.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        throw new InvalidTimeIdException();
    }

    @ExceptionHandler(InvalidTimeIdException.class)
    public ResponseEntity<String> handleInvalidTimeIdException(InvalidTimeIdException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(EmptyDataException.class)
    public ResponseEntity<String> handleEmptyDataException(EmptyDataException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(EmptyDateException.class)
    public ResponseEntity<String> handleEmptyDateException(EmptyDateException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(EmptyNameException.class)
    public ResponseEntity<String> handleEmptyNameException(EmptyNameException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(EmptyTimeException.class)
    public ResponseEntity<String> handleEmptyTimeException(EmptyTimeException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(NotFoundReservationException.class)
    public ResponseEntity<String> handleNotFoundReservationException(NotFoundReservationException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(NotFoundTimeIdException.class)
    public ResponseEntity<String> handleNotFoundTimeException(NotFoundTimeIdException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(InvalidTimeFormatException.class)
    public ResponseEntity<String> handleInvalidTimeFormatException(InvalidTimeFormatException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(DuplicateReservationException.class)
    public ResponseEntity<String> handleDuplicateReservationException(DuplicateReservationException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(DuplicateTimeException.class)
    public ResponseEntity<String> handleDuplicateTimeException(DuplicateTimeException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(TimeInUseException.class)
    public ResponseEntity<String> handleTimeInUseException(TimeInUseException e) {
        System.out.println(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}
