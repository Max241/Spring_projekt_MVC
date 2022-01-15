import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dmcs.brozga.model.AppUserRole;
import pl.dmcs.brozga.repository.AppUserRoleRepo;
import pl.dmcs.brozga.service.AppUserRoleServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppRoleServiceTest {

    @Mock
    AppUserRoleRepo roleRepository;
    @InjectMocks
    AppUserRoleServiceImpl roleService;

    @Test
    public void testReturnRoleName() {
        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setRole("Dyrektor");

        when(roleRepository.findByRole(appUserRole.getRole())).thenReturn(Optional.of((appUserRole)));
        Assertions.assertEquals(appUserRole.getRole(), roleService.getAppUserRoleName(appUserRole.getRole()).getRole());
    }
}
