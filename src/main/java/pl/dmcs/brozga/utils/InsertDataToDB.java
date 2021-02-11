package pl.dmcs.brozga.utils;

import org.springframework.stereotype.Component;
import pl.dmcs.brozga.model.AppUserRole;
import pl.dmcs.brozga.service.AppUserRoleService;


import javax.annotation.PostConstruct;

@Component
public class InsertDataToDB {

    private final AppUserRoleService roleService;

    public InsertDataToDB(AppUserRoleService roleService) {
        this.roleService = roleService;

    }

    @PostConstruct
    public void InsertData() {

        AppUserRole user = new AppUserRole();
        user.setRole("ROLE_USER");
        roleService.addAppUserRole(user);
    }
}
