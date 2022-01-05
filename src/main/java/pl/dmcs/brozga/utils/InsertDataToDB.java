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

        AppUserRole patient = new AppUserRole();
        patient.setRole("ROLE_PATIENT");
        roleService.addAppUserRole(patient);

        AppUserRole admin = new AppUserRole();
        AppUserRole doctor = new AppUserRole();

        admin.setRole("ROLE_ADMIN");
        doctor.setRole("ROLE_DOCTOR");

        roleService.addAppUserRole(admin);
        roleService.addAppUserRole(doctor);


        AppUser appUserPatient = new AppUser();
        appUserPatient.setEmail("patient@op.pl");
        appUserPatient.setPesel(11111111112L);
        appUserPatient.setName("patient");
        appUserPatient.setSurname("good");
        appUserPatient.setPhoneNumber("55555555");
        appUserPatient.setLogin("pat");
        appUserPatient.setEnabled(true);
        appUserPatient.setPassword("pat");
        appUserService.addAppUser(appUserPatient);

        AppUser appUserPatient2 = new AppUser();
        appUserPatient2.setEmail("patient1@op.pl");
        appUserPatient2.setPesel(11111111113L);
        appUserPatient2.setName("patient1");
        appUserPatient2.setSurname("good1");
        appUserPatient2.setPhoneNumber("55555555");
        appUserPatient2.setLogin("pat1");
        appUserPatient2.setEnabled(true);
        appUserPatient2.setPassword("pat1");
        appUserService.addAppUser(appUserPatient2);


        AppUser appUserDoctor = new AppUser();
        appUserDoctor.setEmail("doctor@op.pl");
        appUserDoctor.setPesel(11111111111L);
        appUserDoctor.setName("doctor");
        appUserDoctor.setSurname("good");
        appUserDoctor.setPhoneNumber("55555555");
        appUserDoctor.setLogin("doc");
        appUserDoctor.setEnabled(true);
        appUserDoctor.setPassword("doc");
        appUserDoctor.getAppUserRole().add(doctor);
        appUserService.addAppUser(appUserDoctor);

/*        AppUser appUserAdmin = new AppUser();
        appUserAdmin.setEmail("admin@op.pl");
        appUserAdmin.setPesel(11111111113L);
        appUserAdmin.setName("admin");
        appUserAdmin.setSurname("admin");
        appUserAdmin.setPhoneNumber("55555555");
        appUserAdmin.setLogin("adm");
        appUserAdmin.setEnabled(true);
        appUserAdmin.setPassword("adm");
        appUserAdmin.getAppUserRole().add(admin);
        appUserService.addAppUser(appUserAdmin);*/

/*        AppUser appUserDoctor = new AppUser();
        appUserDoctor.setEmail("doctor@op.pl");
        appUserDoctor.setPesel(11111111111L);
        appUserDoctor.setName("doctor");
        appUserDoctor.setSurname("good");
        appUserDoctor.setPhoneNumber("55555555");
        appUserDoctor.setLogin("doc");
        appUserDoctor.setEnabled(true);
        appUserDoctor.setPassword("doc");
        appUserDoctor.getAppUserRole().add(doctor);
        appUserService.addAppUser(appUserDoctor);*/

/*
        AppUser appUserPatient = new AppUser();
        appUserPatient.setEmail("patient@op.pl");
        appUserPatient.setPesel(11111111112L);
        appUserPatient.setName("patient");
        appUserPatient.setSurname("good");
        appUserPatient.setPhoneNumber("55555555");
        appUserPatient.setLogin("pat");
        appUserPatient.setEnabled(true);
        appUserPatient.setPassword("pat");
        appUserService.addAppUser(appUserPatient);
*/



  /*      AppUser appUserAdmin = new AppUser();
        appUserAdmin.setEmail("admin@op.pl");
        appUserAdmin.setPesel(11111111113L);
        appUserAdmin.setName("admin");
        appUserAdmin.setSurname("admin");
        appUserAdmin.setPhoneNumber("55555555");
        appUserAdmin.setLogin("adm");
        appUserAdmin.setEnabled(true);
        appUserAdmin.setPassword("adm");
        appUserAdmin.getAppUserRole().add(admin);
        appUserService.addAppUser(appUserAdmin);*/

/*        AppUser appUserPatient2 = new AppUser();
        appUserPatient2.setEmail("patient1@op.pl");
        appUserPatient2.setPesel(11111111113L);
        appUserPatient2.setName("patient1");
        appUserPatient2.setSurname("good1");
        appUserPatient2.setPhoneNumber("55555555");
        appUserPatient2.setLogin("pat1");
        appUserPatient2.setEnabled(true);
        appUserPatient2.setPassword("pat1");
        appUserService.addAppUser(appUserPatient2);*/


/*
        AppUser appUserDoctor = new AppUser();
        appUserDoctor.setEmail("doctor@op.pl");
        appUserDoctor.setPesel(11111111111L);
        appUserDoctor.setName("doctor");
        appUserDoctor.setSurname("good");
        appUserDoctor.setPhoneNumber("55555555");
        appUserDoctor.setLogin("doc");
        appUserDoctor.setEnabled(true);
        appUserDoctor.setPassword("doc");
        appUserDoctor.getAppUserRole().add(doctor);
        appUserService.addAppUser(appUserDoctor);
*/


/*        AppUser appUserDoctor1 = new AppUser();
        appUserDoctor1.setEmail("doc1@op.pl");
        appUserDoctor1.setPesel(11111111111L);
        appUserDoctor1.setName("doc1");
        appUserDoctor1.setSurname("good");
        appUserDoctor1.setPhoneNumber("55555555");
        appUserDoctor1.setLogin("doc1");
        appUserDoctor1.setEnabled(true);
        appUserDoctor1.setPassword("doc1");
        appUserDoctor1.getAppUserRole().add(doctor);
        appUserService.addAppUser(appUserDoctor1);*/


    }
}
