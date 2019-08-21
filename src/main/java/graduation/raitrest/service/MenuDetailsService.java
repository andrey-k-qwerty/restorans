package graduation.raitrest.service;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.repository.MenuDetailsRepository;
import graduation.raitrest.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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



}
