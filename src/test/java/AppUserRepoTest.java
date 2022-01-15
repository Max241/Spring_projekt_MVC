import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.dmcs.brozga.configuration.SpringConfig;
import pl.dmcs.brozga.repository.AppUserRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {SpringConfig.class, HibernatePersistenceProvider.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppUserRepoTest {
    
    @Autowired
    AppUserRepo appUserRepo;

    @Test
    public void testNullCheck() {
        assertNotNull(appUserRepo);
    }

    @Test
    void testAppUserExist() {
        String email = "doctor@op.pl";
        Boolean userExists = appUserRepo.existsByEmail(email);
        assertEquals(userExists, true);
    }

}

