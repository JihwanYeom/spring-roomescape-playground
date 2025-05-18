package roomescape.exception;

public class NotFoundReservationException extends RuntimeException {
    public NotFoundReservationException(Long id) {
        super("ID : " + id + " 에 해당하는 예약이 존재하지 않습니다.");
    }
}
