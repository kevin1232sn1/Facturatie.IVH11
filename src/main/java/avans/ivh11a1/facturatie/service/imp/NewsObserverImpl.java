package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.domain.NewsLetter.NewsSubscription;
import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.repository.NewsSubscriptionRepository;
import avans.ivh11a1.facturatie.service.NotificationService;
import avans.ivh11a1.facturatie.service.Observer;
import avans.ivh11a1.facturatie.service.PersonFactoryService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 13-3-2017.
 */
@Service("NewsObserver")
@Repository
@Transactional(rollbackFor = StateException.class)
@NoArgsConstructor
public class NewsObserverImpl implements Observer {

    private NewsSubscriptionRepository newsSubscriptionRepository;

    private PersonFactoryService factoryService;

    private NotificationService notificationService;

    @Autowired
    public NewsObserverImpl(NewsSubscriptionRepository newsSubscriptionRepository, PersonFactoryService factoryService, NotificationService notificationService) {
        this.newsSubscriptionRepository = newsSubscriptionRepository;
        this.factoryService = factoryService;
        this.notificationService = notificationService;
    }

    @Override
    public void update(News news) {
        for (NewsSubscription subscription : newsSubscriptionRepository.findByNewsType(news.getType())) {
            Person person = factoryService.getPerson(subscription.getObserverType(), subscription.getObserverId());
            notificationService.sendMessage(news, person);
            System.out.println("Observer : " + person.getFullName() + " news message " + news.getContent() + " news type: " + news.getType() + " Will send to: " + person.getEmail() + " Type: " + person.getType());
        }
    }
}
