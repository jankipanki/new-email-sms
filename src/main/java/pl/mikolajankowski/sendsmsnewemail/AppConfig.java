package pl.mikolajankowski.sendsmsnewemail;

import com.vonage.client.VonageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${vonage.key}")
    private String vonageKey;

    @Value("${vonage.secret}")
    private String vonageSecret;

    @Bean
    public VonageClient vonageClient() {
        return VonageClient.builder().apiKey(vonageKey).apiSecret(vonageSecret).build();
    }
}
