package roomescape.exception;

public class InvalidTimeFormatException extends RuntimeException {
    public InvalidTimeFormatException() {
        super("시간 형식이 올바르지 않습니다");
    }
}
