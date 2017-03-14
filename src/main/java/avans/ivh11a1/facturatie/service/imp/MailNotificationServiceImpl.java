package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.MailTemplate;
import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.repository.CustomerRepository;
import avans.ivh11a1.facturatie.repository.UserRepository;
import avans.ivh11a1.facturatie.service.NotificationService;
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
public class MailNotificationServiceImpl implements NotificationService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public Boolean sendMessage(News news, Person person) {
        SimpleMailMessage message = new SimpleMailMessage();
        MailTemplate template = news.getMailTemplate();
        message = template.generateMessage(news, person, message);

        javaMailSender.send(message);
        return true;
    }


}
