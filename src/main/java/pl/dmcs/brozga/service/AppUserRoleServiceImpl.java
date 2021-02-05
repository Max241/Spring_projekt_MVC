package pl.dmcs.brozga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.brozga.model.AppUserRole;
import pl.dmcs.brozga.repository.AppUserRoleRepo;

import java.util.List;

@Service
public class AppUserRoleServiceImpl implements AppUserRoleService {

    private AppUserRoleRepo appUserRoleRepository;

    @Autowired
    public AppUserRoleServiceImpl(AppUserRoleRepo appUserRoleRepository) {
        this.appUserRoleRepository = appUserRoleRepository;
    }

    @Transactional
    public void addAppUserRole(AppUserRole appUserRole) {
        appUserRoleRepository.save(appUserRole);
    }

    @Transactional
    public List<AppUserRole> listAppUserRole() {
        return appUserRoleRepository.findAll();
    }

    @Transactional
    public AppUserRole getAppUserRole(long id) {
        return appUserRoleRepository.getOne(id);
    }

}
