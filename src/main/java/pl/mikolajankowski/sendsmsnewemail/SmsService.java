package pl.mikolajankowski.sendsmsnewemail;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${phone}")
    String phone;

    @Autowired
    VonageClient vonageClient;

    public void sendSms(String messageContent) {
        TextMessage message = new TextMessage("Info_Email", phone, messageContent);

        SmsSubmissionResponse response = vonageClient.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Nowa wiadomość na e-mail");
        } else {
            System.out.println("Błąd wysyłania wiadomości: " + response.getMessages().get(0).getErrorText());
        }
    }
}