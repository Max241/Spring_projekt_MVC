import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import pl.dmcs.brozga.service.EmailService;
import pl.dmcs.brozga.service.EmailServiceImpl;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmailTest {

    @Rule
    static GreenMail greenMail = new GreenMail(new ServerSetup(25, "localhost", "smtp"));

    @Autowired
    EmailService emailService;

    @BeforeEach
    public void startServer() {
        greenMail.start();
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        emailService = new EmailServiceImpl(javaMailSender);
    }

    @Test
    public void testSendEmail() throws MessagingException, IOException {
        String to = "bartek130@gmail.com";
        String subject = "Subject";
        String body = "Body";
        emailService.sendEmail(to, subject, body);

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertEquals(receivedMessages.length, 1);
        MimeMessage receivedMessage = receivedMessages[0];
        assertEquals(receivedMessage.getAllRecipients()[0].toString(), to);
        assertEquals(receivedMessage.getSubject(), subject);
        assertTrue(receivedMessage.getContent().toString().contains(body));
    }
}

