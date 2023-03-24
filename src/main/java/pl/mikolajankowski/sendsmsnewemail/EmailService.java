package pl.mikolajankowski.sendsmsnewemail;

import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    @Value("${email.account}")
    String emailAccount;

    @Value("${email.password}")
    String emailPassword;

    public Folder getInboxFolder() throws MessagingException {
        Properties properties = System.getProperties();
        properties.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(properties, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", emailAccount, emailPassword);

        return store.getFolder("Inbox");
    }
}