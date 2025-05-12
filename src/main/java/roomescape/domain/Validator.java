package roomescape.domain;

import java.util.List;
import roomescape.exception.EmptyDataException;
import roomescape.exception.NotFoundReservationException;

public class Validator {

    public void validateEmptyData(Reservation reservation) {
        if(reservation.getDate() == null || reservation.getDate().isEmpty())
            throw new EmptyDataException("날짜 정보가 입력되지 않았습니다");
        if(reservation.getTime() == null || reservation.getTime().isEmpty())
            throw new EmptyDataException("시간 정보가 입력되지 않았습니다");
        if(reservation.getName() == null || reservation.getName().isEmpty())
            throw new EmptyDataException("이름이 입력되지 않았습니다");
    }

    public void validateReservationIdExists(List<Reservation> reservations, Long id) {
        boolean exists = reservations.stream()
                .anyMatch(reservation -> reservation.getId().equals(id));

        if (!exists) {
            throw new NotFoundReservationException("ID " + id + "에 해당하는 예약이 존재하지 않습니다.");
        }
    }

}
