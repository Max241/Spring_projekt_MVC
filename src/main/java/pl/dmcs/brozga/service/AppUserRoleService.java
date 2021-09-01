package pl.dmcs.brozga.service;


import pl.dmcs.brozga.model.AppUserRole;

import java.util.List;
import java.util.Set;

public interface AppUserRoleService {

    void addAppUserRole(AppUserRole appUserRole);

    List<AppUserRole> listAppUserRole();

    AppUserRole getAppUserRole(Long id);

    AppUserRole getAppUserRoleName(String role);

    Set<AppUserRole> convertStringsToRoles(Set<String> roles);

}