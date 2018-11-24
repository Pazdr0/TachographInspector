package pl.bgolc.tachograph.user.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.bgolc.tachograph.user.User;
import pl.bgolc.tachograph.user.UserService;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private Logger log = LoggerFactory.getLogger(RegistrationListener.class);
    private UserService userService;
    private MessageSource messageSource;
    private JavaMailSender mailSender;

    @Autowired
    public RegistrationListener(UserService userService, MessageSource messageSource, JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.messageSource = messageSource;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        log.debug("Token has been created");

        userService.createVerificationToken(user, token, new VerificationToken().calculateExpiryDate());
        String recipientAddress = user.getEmail();
        String subject = "Potwierdzenie rejestracji";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("pazdr0@pazdr0.nazwa.pl");
        mailMessage.setTo(recipientAddress);
        mailMessage.setSubject(subject);
        //TODO zmienic na pazdr0sf jak bede wrzucal na serwer
        mailMessage.setText("Link " + "http://localhost:8080" /*"http://pazdr0sf.nazwa.pl:8080"*/ + confirmationUrl
                + " \n wiadomosc zostala wygenerowana automatycznie prosimy na nia nie odpowiadac");
        log.debug("Sending an email...");
        mailSender.send(mailMessage);
    }
}
