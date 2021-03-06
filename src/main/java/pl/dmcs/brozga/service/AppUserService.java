package pl.dmcs.brozga.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.AppUserDTO;


import java.util.List;

public interface AppUserService {


    void addAppUser(AppUser user);

    boolean existByEmail(String email);

    boolean existByLogin(String login);


    @PreAuthorize("hasRole('ROLE_ADMIN') OR (#appUser.email == principal.username)")
    void editAppUser(AppUser user);

    List<AppUser> listAppUser();

    AppUser getAppUser(long id);

    AppUser findByLogin(String login);

    AppUser findByEmail(String email);

    //@PreAuthorize("hasRole('ROLE_ADMIN') OR (#appUser.email == principal.username)")
    void editAppUserDetails(AppUserDTO editedAppUser, AppUser appUser);

    @Secured("ROLE_ADMIN")
    void removeAppUser(long id);


    void activateUser(String token);

    List<AppUser> getDoctorsList();

    Page<AppUser> getUsersWhereIdIsNot(Long id, Pageable pageable);

    void editUserForAdmin(AppUserDTO appUserDTO, AppUser appUser);
}
