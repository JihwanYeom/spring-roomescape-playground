package roomescape.exception;

public class InvalidTimeIdException extends RuntimeException {
    public InvalidTimeIdException() {
        super("유효하지 않은 시간 ID 입니다.");
    }
}
