package pl.dmcs.brozga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.AppUserEditData;
import pl.dmcs.brozga.repository.AppUserRepo;
import pl.dmcs.brozga.repository.AppUserRoleRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepo appUserRepo;
    private AppUserRoleRepo appUserRoleRepo;

    @Autowired
    public AppUserServiceImpl(AppUserRepo appUserRepo, AppUserRoleRepo appUserRoleRepo) {
        this.appUserRoleRepo = appUserRoleRepo;
        this.appUserRepo = appUserRepo;
    }

    @Transactional
    public void addAppUser(AppUser appUser) {
        appUser.getAppUserRole().add(appUserRoleRepo.findByRole("ROLE_USER").orElse(null));
        appUser.setPassword(hashPassword(appUser.getPassword()));
        appUserRepo.save(appUser);
    }

    @Override
    public void editAppUser(AppUser appUser) {
        appUser.getAppUserRole().add(appUserRoleRepo.findByRole("ROLE_USER").orElse(null));
        appUser.setPassword(hashPassword(appUser.getPassword()));
        appUserRepo.save(appUser);
    }

    public void editAppUserDetails(AppUser appUser, AppUserEditData editedAppUser) {
        appUser.setName(editedAppUser.getName());
        appUser.setSurname(editedAppUser.getSurname());
        appUser.setPhoneNumber(editedAppUser.getPhoneNumber());
        appUser.setPesel(editedAppUser.getPesel());
        appUserRepo.save(appUser);


    }

    @Transactional
    public AppUser findByEmail(String email) {
        return appUserRepo.findByEmail(email);
    }

    @Transactional
    public List<AppUser> listAppUser() {
        return appUserRepo.findAll();
    }

    @Transactional
    public void removeAppUser(long id) {
        appUserRepo.delete(id);
    }

    @Transactional
    public AppUser getAppUser(long id) {
        return appUserRepo.findById(id);
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Transactional
    public boolean existByEmail(String email) {
        return appUserRepo.existsByEmail(email);
    }

    @Transactional
    public boolean existByLogin(String login) {
        return appUserRepo.existsByLogin(login);
    }

    @Transactional
    public AppUser findByLogin(String login) {
        return appUserRepo.findByLogin(login);
    }


}
