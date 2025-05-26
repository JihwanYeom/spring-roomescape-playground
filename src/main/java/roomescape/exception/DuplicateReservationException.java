package roomescape.exception;

public class DuplicateReservationException extends RuntimeException {
    public DuplicateReservationException() {
        super("이미 해당 날짜와 시간에 예약이 존재합니다.");
    }
}
