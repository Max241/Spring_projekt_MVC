package pl.dmcs.brozga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.brozga.model.AppUserRole;
import pl.dmcs.brozga.repository.AppUserRoleRepo;

import java.util.List;

@Service("appUserRoleService")
public class AppUserRoleServiceImpl implements AppUserRoleService {

    private AppUserRoleRepo appUserRoleRepo;

    @Autowired
    public AppUserRoleServiceImpl(AppUserRoleRepo appUserRoleRepository) {
        this.appUserRoleRepo = appUserRoleRepository;
    }

    @Transactional
    public void addAppUserRole(AppUserRole appUserRole) {
        appUserRoleRepo.save(appUserRole);
    }

    @Transactional
    public List<AppUserRole> listAppUserRole() {
        return appUserRoleRepo.findAll();
    }

    @Transactional
    public List<AppUserRole> getAllAppUserRoles() {
        return appUserRoleRepo.findAll();
    }

    @Transactional
    public AppUserRole getAppUserRole(Long id) {
        return appUserRoleRepo.getOne(id);
    }

}
