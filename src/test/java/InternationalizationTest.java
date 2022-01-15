import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.dmcs.brozga.configuration.SpringConfig;


import java.util.Locale;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {SpringConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InternationalizationTest {

    @Autowired
    private MessageSource messageSource;

    @ParameterizedTest
    @CsvSource(value = {"fr:Se connecter", "pl:Zaloguj się", "en_US:Login"}, delimiter = ':')
    public void testLoginPageTitleEquals(String lang, String expected) {
        Locale.setDefault(new Locale(lang));
        String title = messageSource.getMessage("login.title", null, Locale.getDefault());
        Assertions.assertEquals(expected, title);
    }

    @Test
    public void testNotEqualsRegisterPageTitle() {
        Locale.setDefault(new Locale("en_US"));
        String title = messageSource.getMessage("register.title", null, Locale.getDefault());
        Assertions.assertNotEquals(title, "register.name");
    }
}
