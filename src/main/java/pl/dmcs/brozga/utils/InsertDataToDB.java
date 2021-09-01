package pl.dmcs.brozga.utils;

import org.springframework.stereotype.Component;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.AppUserRole;
import pl.dmcs.brozga.service.AppUserRoleService;
import pl.dmcs.brozga.service.AppUserService;


import javax.annotation.PostConstruct;

@Component
public class InsertDataToDB {

    private AppUserRoleService roleService;

    private AppUserService appUserService;


    public InsertDataToDB(AppUserRoleService roleService, AppUserService appUserService) {
        this.roleService = roleService;
        this.appUserService = appUserService;

    }

    @PostConstruct
    public void InsertData() {

        AppUserRole user = new AppUserRole();
        user.setRole("ROLE_USER");
        roleService.addAppUserRole(user);

        AppUserRole admin = new AppUserRole();
        AppUserRole patient = new AppUserRole();
        AppUserRole doctor = new AppUserRole();

        admin.setRole("ROLE_ADMIN");
        patient.setRole("ROLE_PATIENT");
        doctor.setRole("ROLE_DOCTOR");

        roleService.addAppUserRole(admin);
        roleService.addAppUserRole(patient);
        roleService.addAppUserRole(doctor);

        AppUser appUserDoctor = new AppUser();
        appUserDoctor.setEmail("doc@op.pl");
        appUserDoctor.setPesel(11111111111L);
        appUserDoctor.setName("doc");
        appUserDoctor.setSurname("good");
        appUserDoctor.setPhoneNumber("55555555");
        appUserDoctor.setLogin("doc");
        appUserDoctor.setEnabled(true);
        appUserDoctor.setPassword("doc");
        appUserDoctor.getAppUserRole().add(doctor);
        appUserService.addAppUser(appUserDoctor);


    }
}
