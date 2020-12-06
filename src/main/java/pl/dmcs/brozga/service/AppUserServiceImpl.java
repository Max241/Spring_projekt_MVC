package pl.dmcs.brozga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.repository.AppUserRepo;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepo appUserRepo;

    @Autowired
    public AppUserServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    public void addAppUser(AppUser appUser) {appUserRepo.save(appUser);}

    public void editAppUser(AppUser appUser) {appUserRepo.save(appUser);}

    public List<AppUser> listAppUser(){return appUserRepo.findAll();}

    public void removeAppUser(long id) {appUserRepo.delete(id);}

    public AppUser getAppUser(long id) {return  appUserRepo.findById(id);}
}
