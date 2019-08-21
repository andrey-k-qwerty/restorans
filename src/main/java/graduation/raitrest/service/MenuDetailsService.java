package graduation.raitrest.service;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.repository.MenuDetailsRepository;
import graduation.raitrest.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static graduation.raitrest.util.DateTimeUtil.adjustEndDateTime;
import static graduation.raitrest.util.DateTimeUtil.adjustStartDateTime;
import static graduation.raitrest.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuDetailsService {
    @Autowired
    private final MenuDetailsRepository menuDetailsRepository;

    public MenuDetailsService(MenuDetailsRepository menuDetailsRepository) {
        this.menuDetailsRepository = menuDetailsRepository;
    }

    public MenuDetails get(int id) throws NotFoundException {
        return checkNotFoundWithId(menuDetailsRepository.get(id), id);
    }

    public MenuDetails get(int id, int managerId) throws NotFoundException {
        return checkNotFoundWithId(menuDetailsRepository.get(id, managerId), id);
    }

    public List<MenuDetails> getAll() {
        return menuDetailsRepository.getAll();
    }

    public List<MenuDetails> getAll(int managerId) {
        return menuDetailsRepository.getAll( managerId);
    }

    protected List<MenuDetails> getFilterByDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return menuDetailsRepository. getAllByDateTime(startDateTime, endDateTime);
    }

    public List<MenuDetails> getFilterByDate( LocalDate startDate,  LocalDate endDate) {
        return getFilterByDateTimes(adjustStartDateTime(startDate), adjustEndDateTime(endDate));
    }
}
