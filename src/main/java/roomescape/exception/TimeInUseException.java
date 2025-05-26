package roomescape.exception;

public class TimeInUseException extends RuntimeException {
    public TimeInUseException() {
        super("해당 시간은 예약에 사용 중이므로 삭제할 수 없습니다.");
    }
}
