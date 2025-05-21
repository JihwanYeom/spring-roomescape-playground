package roomescape.dto;

import roomescape.exception.InvalidTimeIdException;

public class ReservationRequest {

    private String name;
    private String date;
    private String time;

    public ReservationRequest(String name, String date, String time) {
        validateTimeFormat(time);
        this.name = name;
        this.date = date;
        this.time = time;
    }

    private void validateTimeFormat(String time) throws InvalidTimeIdException {
        try {
            Long.parseLong(time);
        } catch (NumberFormatException e) {
            throw new InvalidTimeIdException();
        }
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
