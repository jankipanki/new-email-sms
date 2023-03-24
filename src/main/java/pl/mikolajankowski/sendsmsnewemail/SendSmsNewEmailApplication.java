package pl.mikolajankowski.sendsmsnewemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SendSmsNewEmailApplication implements CommandLineRunner{

    @Autowired
    MainService mainService;

    public static void main(String[] args) {
        SpringApplication.run(SendSmsNewEmailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainService.start();
    }
}