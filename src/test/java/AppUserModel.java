import org.junit.jupiter.api.Test;
import pl.dmcs.brozga.model.AppUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserModel {

    @Test
    void testAppUserEqualsToString() {
        AppUser appUser = new AppUser();
        appUser.setName("Doctor");
        appUser.setSurname("Good");
        appUser.setEmail("doctor@op.pl");

        assertEquals("Doctor Good doctor@op.pl", appUser.toString());
    }
}

