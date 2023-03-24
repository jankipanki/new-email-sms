package pl.mikolajankowski.sendsmsnewemail;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    EmailService emailService;

    @Autowired
    SmsService smsService;

    public void start() throws MessagingException {
        Folder inbox = emailService.getInboxFolder();
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        int messageCount = messages.length;

        while (true) {
            int newMessageCount = inbox.getMessageCount();
            if (newMessageCount > messageCount) {
                Message[] newMessages = inbox.getMessages(messageCount + 1, newMessageCount);
                for (Message messagess : newMessages) {
                    String information = "Nowa wiadomosc od: " + messagess.getFrom()[0] + "\n" + "Temat: " + messagess.getSubject() + "\n" + "Data: " + messagess.getReceivedDate() + "\n";
                    smsService.sendSms(information);
                }
                messageCount = newMessageCount;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
