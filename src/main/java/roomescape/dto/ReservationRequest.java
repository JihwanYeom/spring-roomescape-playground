package roomescape.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationRequest {

    private final String name;
    private final String date;
    @JsonProperty("time")
    private final Long timeId;;

    public ReservationRequest(String name, String date, Long timeId) {

        this.name = name;
        this.date = date;
        this.timeId = timeId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Long getTimeId() {
        return timeId;
    }

}
