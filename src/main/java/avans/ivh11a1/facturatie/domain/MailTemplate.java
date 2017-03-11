package avans.ivh11a1.facturatie.domain;

import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by kevin on 10-3-2017.
 */
public abstract class MailTemplate {
    protected Person person;
    protected News news;
    protected SimpleMailMessage message;


    protected String body = "";

    private void salutation() {
        body += "Hey";
    }

    private void bodyText() {
        body += news.getContent();
    }

    protected abstract void newsMessage();

    private void Closing() {
        body += " Ending";
    }

    private void fillFields() {

    }

    public final SimpleMailMessage generateMessage(News news, Person person, SimpleMailMessage message) {
        this.news = news;
        this.person = person;
        this.message = message;

        body = "";

        message.setFrom("Info@facturatie.com");

        fillSubject();

        setReceiver();

        salutation();

        bodyText();

        newsMessage();

        Closing();

        if (mailTagsUsed())
            fillFields();

        return message;
    }

    protected void setReceiver() {
        message.setTo(person.getEmail());
    }

    protected abstract void fillSubject();

    protected boolean mailTagsUsed() {
        return true;
    }
}
