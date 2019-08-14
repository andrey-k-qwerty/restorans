package graduation.raitrest.service;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.model.entities.User;
import graduation.raitrest.repository.MenuDetailsRepository;
import graduation.raitrest.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import static graduation.raitrest.util.ValidationUtil.checkNotFoundWithId;

public class MenuDetailsService {
    @Autowired
    private final MenuDetailsRepository  menuDetailsRepository;

    public MenuDetailsService(MenuDetailsRepository menuDetailsRepository) {
        this.menuDetailsRepository = menuDetailsRepository;
    }

    public MenuDetails get(int id) throws NotFoundException {
        return checkNotFoundWithId(menuDetailsRepository.get(id), id);
    }



}
