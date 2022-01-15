import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.dmcs.brozga.configuration.SpringConfig;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.service.AppUserService;


import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {SpringConfig.class, HibernatePersistenceProvider.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppUserServiceTest {

    @Mock
    AppUser appUser;

    @Autowired
    AppUserService userService;

    @BeforeEach
    public void setup() {
        appUser = new AppUser();
        appUser.setName("Doctor");
        appUser.setSurname("Good");
        appUser.setEmail("doctor@op.pl");
    }

    @Test
    public void testGetUserByEmail() {
        userService = mock(AppUserService.class);

        Mockito.when(userService.findByEmail("doctor@op.pl")).thenReturn(appUser);
        String testName = userService.findByEmail("doctor@op.pl").toString();

        Assertions.assertEquals("Doctor Good doctor@op.pl", testName);
    }

    @Test
    public void testGetUserByEmailReturnNull() {
        AppUser appUser = userService.findByEmail("dyrektor@op.pl");
        Assertions.assertNull(appUser);
    }

}
