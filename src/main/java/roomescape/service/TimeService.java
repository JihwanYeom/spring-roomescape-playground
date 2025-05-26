package roomescape.service;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import roomescape.dao.TimeDao;
import roomescape.domain.Time;
import roomescape.dto.TimeRequest;
import roomescape.dto.TimeResponse;
import roomescape.exception.DuplicateTimeException;
import roomescape.exception.InvalidTimeFormatException;
import roomescape.exception.NotFoundTimeException;
import roomescape.exception.TimeInUseException;

@Service
public class TimeService {

    private final TimeDao timeDao;

    public TimeService(TimeDao timeDao) {
        this.timeDao = timeDao;
    }

    public List<TimeResponse> findAll() {
        List<Time> times = timeDao.findAll();
        List<TimeResponse> result = new ArrayList<>();
        for (Time time : times) {
            result.add(TimeResponse.from(time));
        }
        return result;
    }

    public TimeResponse create(TimeRequest timeRequest) {
        Time time;
        try {
            time = new Time(null, LocalTime.parse(timeRequest.getTime()));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException();
        }

        if (timeDao.timeIsExists(time.getTime())) {
            throw new DuplicateTimeException();
        }

        Time createdTime = timeDao.create(time);
        return TimeResponse.from(createdTime);
    }

    public void deleteById(Long id) {
        validateTimeIdExists(id);
        try {
            timeDao.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new TimeInUseException();
        }
    }

    private void validateTimeIdExists(Long id) {
        if (!timeDao.idIsExist(id)) {
            throw new NotFoundTimeException(id);
        }
    }

}
