package roomescape.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.dao.TimeDao;
import roomescape.domain.Time;
import roomescape.dto.TimeRequest;
import roomescape.dto.TimeResponse;
import roomescape.exception.NotFoundTimeException;

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
        Time time = new Time(
                null,
                timeRequest.getTime()
        );
        Time createdTime = timeDao.create(time);
        return TimeResponse.from(createdTime);
    }

    public void deleteById(Long id) {
        validateTimeIdExists(id);
        try {
            timeDao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateTimeIdExists(Long id) {
        if (!timeDao.idIsExist(id)) {
            throw new NotFoundTimeException(id);
        }
    }

}
