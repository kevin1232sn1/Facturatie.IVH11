package avans.ivh11a1.facturatie.domain.NewsLetter.MailTemplates;

import avans.ivh11a1.facturatie.domain.MailTemplate;

/**
 * Created by kevin on 10-3-2017.
 */
public class HealthMailTemplate extends MailTemplate {

    @Override
    protected void newsMessage() {
        body += "This is a news message about an important Health Issue. Please read the message carefully ";
        body += news.getContent();
    }

    @Override
    protected void fillSubject() {
        message.setSubject("Health news: " + news.getTitle());
    }
}

