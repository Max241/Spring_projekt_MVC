package pl.dmcs.brozga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.AppUserDTO;
import pl.dmcs.brozga.model.AppUserRole;
import pl.dmcs.brozga.model.Token;
import pl.dmcs.brozga.repository.AppUserRepo;
import pl.dmcs.brozga.repository.AppUserRoleRepo;
import pl.dmcs.brozga.repository.TokenRepo;


import javax.transaction.Transactional;
import java.util.*;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepo appUserRepo;
    private AppUserRoleRepo appUserRoleRepo;
    private EmailService emailService;
    private MessageSource messageSource;
    private TokenRepo tokenRepo;
    private AppUserRoleService appUserRoleService;

    @Autowired
    public AppUserServiceImpl(AppUserRepo appUserRepo, AppUserRoleRepo appUserRoleRepo, EmailService emailService, MessageSource messageSource, TokenRepo tokenRepo, AppUserRoleService appUserRoleService) {
        this.appUserRoleRepo = appUserRoleRepo;
        this.appUserRepo = appUserRepo;
        this.emailService = emailService;
        this.messageSource = messageSource;
        this.tokenRepo = tokenRepo;
        this.appUserRoleService = appUserRoleService;
    }

    @Override
    public void addAppUser(AppUser appUser) {
        //appUser.getAppUserRole().add(appUserRoleRepo.findByRole("ROLE_USER").orElse(null));
        appUser.getAppUserRole().add(appUserRoleRepo.findByRole("ROLE_PATIENT").orElse(null));
        appUser.setPassword(hashPassword(appUser.getPassword()));
        Token token = new Token();
        token.setToken(createToken());
        tokenRepo.save(token);
        appUser.setToken(token);
        appUserRepo.save(appUser);
        emailService.sendEmail(
                appUser.getEmail(),
                messageSource.getMessage("register.subject", null, LocaleContextHolder.getLocale()),
                messageSource.getMessage("register.text", null, LocaleContextHolder.getLocale())
                        + "\n" + "http://localhost:8080/activateAccount?token="
                        + appUser.getToken().getToken()
        );

    }

    @Override
    public void editAppUser(AppUser appUser) {
        appUser.getAppUserRole().add(appUserRoleRepo.findByRole("ROLE_PATIENT").orElse(null));
        appUser.setPassword(hashPassword(appUser.getPassword()));
        appUserRepo.save(appUser);
    }


    public void editAppUserDetails(AppUserDTO editedAppUser, AppUser appUser) {
        appUser.setName(editedAppUser.getName());
        appUser.setSurname(editedAppUser.getSurname());
        appUser.setPhoneNumber(editedAppUser.getPhoneNumber());
        appUser.setPesel(editedAppUser.getPesel());
        appUserRepo.save(appUser);


    }

    @Override
    public Page<AppUser> getUsersWhereIdIsNot(Long id, Pageable pageable) {
        return appUserRepo.findAllByIdIsNot(id, pageable);
    }


    @Override
    public void activateUser(String token) {
        if (appUserRepo.findByTokenToken(token).isPresent()) {
            AppUser appUser = appUserRepo.findByTokenToken(token).get();
            appUser.setEnabled(true);
            appUserRepo.save(appUser);
        }
    }

    public void editUserForAdmin(AppUserDTO appUserDTO, AppUser appUser) {
        appUser.setName(appUserDTO.getName());
        appUser.setSurname(appUserDTO.getSurname());
        appUser.setPhoneNumber(appUserDTO.getPhoneNumber());
        appUser.setPesel(appUserDTO.getPesel());
        appUserRepo.save(appUser);
    }

    private AppUser getUserByActivationToken(String token) {
        Optional<AppUser> appUser = appUserRepo.findByTokenToken(token);
        return appUser.orElse(null);
    }

    @Override
    public List<AppUser> getDoctorsList() {
        return appUserRepo.findAllByAppUserRole(appUserRoleService.getAppUserRoleName("ROLE_DOCTOR"));
    }

    private String createToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public AppUser findByEmail(String email) {
        AppUser appUser = appUserRepo.findByEmail(email);
        return appUser;
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
        Optional<AppUser> appUser = appUserRepo.findById(id);
        return appUser.orElse(null);
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
