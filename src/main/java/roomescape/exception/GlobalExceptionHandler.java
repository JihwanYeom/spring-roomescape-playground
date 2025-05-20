package roomescape.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyDataException.class)
    public ResponseEntity<Void> handleEmptyDataException(EmptyDataException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(NotFoundReservationException.class)
    public ResponseEntity<Void> handleNotFoundReservationException(NotFoundReservationException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NotFoundTimeException.class)
    public ResponseEntity<Void> handleNotFoundTimeException(NotFoundTimeException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.notFound().build();
    }

}
