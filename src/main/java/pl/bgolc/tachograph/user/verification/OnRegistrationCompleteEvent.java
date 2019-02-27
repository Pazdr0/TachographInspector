package pl.bgolc.tachograph.user.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import pl.bgolc.tachograph.user.User;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private Logger log = LoggerFactory.getLogger(OnRegistrationCompleteEvent.class);

    private String appUrl;
    private Locale locale;
    private User user;

    /*
    * Constructor
    * */
    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    /*
    * Getters
    * */
    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }

    /*
    * Setters
    * */
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
