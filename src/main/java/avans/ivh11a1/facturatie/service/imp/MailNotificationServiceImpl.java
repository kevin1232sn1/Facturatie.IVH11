package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.MailTemplate;
import avans.ivh11a1.facturatie.domain.NewsLetter.MailTemplates.CompanyMailTemplate;
import avans.ivh11a1.facturatie.domain.NewsLetter.MailTemplates.HealthMailTemplate;
import avans.ivh11a1.facturatie.domain.NewsLetter.MailTemplates.InsuranceMailTemplate;
import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.repository.CustomerRepository;
import avans.ivh11a1.facturatie.repository.UserRepository;
import avans.ivh11a1.facturatie.service.NotificationService;
import avans.ivh11a1.facturatie.service.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 10-3-2017.
 */
@Service("MailNotificationService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class MailNotificationServiceImpl implements NotificationService, Observer {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    private Person person;
    private News news;


    @Override
    public Boolean sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        MailTemplate template;
        if (news.getType() == "Health") {
            template = new HealthMailTemplate();
        } else if (news.getType() == "Insurance") {
            template = new InsuranceMailTemplate();
        } else {
            template = new CompanyMailTemplate();
        }

        message = template.generateMessage(news, person, message);

        javaMailSender.send(message);
        return true;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void update(News news) {
        this.news = news;
        sendMail();
        System.out.println("Observer : " + person.getFullName() + " news message " + news.getContent() + " news type: " + news.getType() + " Will send to: " + person.getEmail() + " Role: " + person.getRole());
    }

    @Override
    public String getType() {
        return person.getType();
    }

    @Override
    public int getId() {
        return person.getId();
    }
}
