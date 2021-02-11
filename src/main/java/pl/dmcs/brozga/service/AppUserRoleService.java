package pl.dmcs.brozga.service;


import pl.dmcs.brozga.model.AppUserRole;

import java.util.List;

public interface AppUserRoleService {

    void addAppUserRole(AppUserRole appUserRole);
    List<AppUserRole> listAppUserRole();

    AppUserRole getAppUserRole(Long id);

}