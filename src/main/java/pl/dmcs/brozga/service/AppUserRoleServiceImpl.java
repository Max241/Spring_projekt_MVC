package pl.dmcs.brozga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.brozga.model.AppUserRole;
import pl.dmcs.brozga.repository.AppUserRoleRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("appUserRoleService")
public class AppUserRoleServiceImpl implements AppUserRoleService {

    private AppUserRoleRepo appUserRoleRepo;

    @Autowired
    public AppUserRoleServiceImpl(AppUserRoleRepo appUserRoleRepository) {
        this.appUserRoleRepo = appUserRoleRepository;
    }

    @Override
    public void addAppUserRole(AppUserRole appUserRole) {
        appUserRoleRepo.save(appUserRole);
    }

    @Override
    public List<AppUserRole> listAppUserRole() {
        return appUserRoleRepo.findAll();
    }
    

    @Override
    public AppUserRole getAppUserRole(Long id) {
        return appUserRoleRepo.getOne(id);
    }

    @Override
    public AppUserRole getAppUserRoleName(String role) {
        Optional<AppUserRole> oRole = appUserRoleRepo.findByRole(role);
        return oRole.orElse(null);
    }

    @Override
    public Set<AppUserRole> convertStringsToRoles(Set<String> roles) {
        Set<AppUserRole> roleSet = new HashSet<>(0);
        roles.forEach(role -> roleSet.add(getAppUserRoleName(role)));
        return roleSet;
    }

}
