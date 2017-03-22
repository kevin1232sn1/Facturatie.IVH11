package avans.ivh11a1.facturatie.domain.NewsLetter;

import avans.ivh11a1.facturatie.domain.MailTemplate;
import avans.ivh11a1.facturatie.domain.NewsLetter.MailTemplates.CompanyMailTemplate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by kevin on 10-3-2017.
 */

@Getter
@Setter
@Entity
public class CompanyNews extends News {
    private static final long serialVersionUID = 1L;

    public CompanyNews() {
        super.setType("Company");
    }

    public CompanyNews(News news) {
        super.setType("Company");
        super.setTitle(news.getTitle());
        super.setDatePublished(news.getDatePublished());
        super.setContent(news.getContent());
    }

    @Override
    public MailTemplate getMailTemplate() {
        return new CompanyMailTemplate();
    }
}
