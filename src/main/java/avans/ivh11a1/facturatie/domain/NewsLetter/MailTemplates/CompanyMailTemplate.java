package avans.ivh11a1.facturatie.domain.NewsLetter.MailTemplates;

import avans.ivh11a1.facturatie.domain.MailTemplate;

/**
 * Created by kevin on 10-3-2017.
 */
public class CompanyMailTemplate extends MailTemplate {

    @Override
    protected void newsMessage() {
        body += news.getContent();
    }

    @Override
    protected void fillSubject() {
        message.setSubject("Company news: " + news.getTitle());
    }
}
