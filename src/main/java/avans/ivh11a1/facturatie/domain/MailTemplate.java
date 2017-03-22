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

    protected void setReceiver() {
        message.setTo(person.getEmail());
    }

    protected abstract void fillSubject();

    private void salutation() {
        body += "Dear %Name%,<br/><br/>";
    }

    private void bodyText() {
        body += news.getContent();
    }

    protected abstract void newsMessage();

    private void Closing() {
        body += " <br/> With kind regards, <br/><br/>" +
                "%InsuranceCompanyName%";
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
        fillFields();

        message.setText(body);
        return message;
    }


}
