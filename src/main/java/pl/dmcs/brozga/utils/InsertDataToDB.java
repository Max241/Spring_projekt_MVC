package pl.dmcs.brozga.utils;

import org.springframework.security.access.prepost.PreAuthorize;
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

        AppUserRole patient = new AppUserRole();
        patient.setRole("ROLE_PATIENT");
        roleService.addAppUserRole(patient);

        AppUserRole admin = new AppUserRole();
        //AppUserRole patient = new AppUserRole();
        AppUserRole doctor = new AppUserRole();

        admin.setRole("ROLE_ADMIN");
        // patient.setRole("ROLE_PATIENT");
        doctor.setRole("ROLE_DOCTOR");

        roleService.addAppUserRole(admin);
        // roleService.addAppUserRole(patient);
        roleService.addAppUserRole(doctor);

        AppUser appUserPatient = new AppUser();
        appUserPatient.setEmail("pat@op.pl");
        appUserPatient.setPesel(11111111112L);
        appUserPatient.setName("pat");
        appUserPatient.setSurname("good");
        appUserPatient.setPhoneNumber("55555555");
        appUserPatient.setLogin("pat");
        appUserPatient.setEnabled(true);
        appUserPatient.setPassword("pat");
        // appUserPatient.getAppUserRole().add(patient);
        appUserService.addAppUser(appUserPatient);

        AppUser appUserPatient2 = new AppUser();
        appUserPatient2.setEmail("pat1@op.pl");
        appUserPatient2.setPesel(11111111113L);
        appUserPatient2.setName("pat1");
        appUserPatient2.setSurname("good1");
        appUserPatient2.setPhoneNumber("55555555");
        appUserPatient2.setLogin("pat1");
        appUserPatient2.setEnabled(true);
        appUserPatient2.setPassword("pat1");
        //  appUserPatient2.getAppUserRole().add(patient);
        appUserService.addAppUser(appUserPatient2);


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


        AppUser appUserDoctor1 = new AppUser();
        appUserDoctor1.setEmail("doc1@op.pl");
        appUserDoctor1.setPesel(11111111111L);
        appUserDoctor1.setName("doc1");
        appUserDoctor1.setSurname("good");
        appUserDoctor1.setPhoneNumber("55555555");
        appUserDoctor1.setLogin("doc1");
        appUserDoctor1.setEnabled(true);
        appUserDoctor1.setPassword("doc1");
        appUserDoctor1.getAppUserRole().add(doctor);
        appUserService.addAppUser(appUserDoctor1);

    }
}
