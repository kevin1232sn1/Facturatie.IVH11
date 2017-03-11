package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.domain.NewsLetter.NewsSubscription;
import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.repository.NewsRepository;
import avans.ivh11a1.facturatie.repository.NewsSubscriptionRepository;
import avans.ivh11a1.facturatie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 10-3-2017.
 */
@Service("NewsService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class NewsServiceImpl implements NewsService, Subject {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsSubscriptionRepository newsSubscriptionRepository;

    @Autowired
    private PersonFactoryService factoryService;

    @Override
    public void register(Observer o, String newsType) {
        NewsSubscription subscription = newsSubscriptionRepository.findByNewsTypeAndObserverTypeAndObserverId(newsType, o.getType(), o.getId());
        if (subscription == null) {
            subscription = new NewsSubscription(newsType, o.getType(), o.getId());
            newsSubscriptionRepository.save(subscription);
        }
    }

    @Override
    public void unRegister(Observer o, String newsType) {
        NewsSubscription subscription = newsSubscriptionRepository.findByNewsTypeAndObserverTypeAndObserverId(newsType, o.getType(), o.getId());
        if (subscription != null) {
            newsSubscriptionRepository.delete(subscription);
        }
    }

    @Override
    public void notifyObserver(News news) {
        for (NewsSubscription subscription : newsSubscriptionRepository.findByNewsType(news.getType())) {
            Person p = factoryService.getPerson(subscription.getObserverType(), subscription.getObserverId());
            NotificationService notificationService = new MailNotificationServiceImpl();
            notificationService.setPerson(p);
            notificationService.update(news);
        }
    }

    @Override
    public Iterable<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Boolean save(News news) {
        newsRepository.save(news);
        notifyObserver(news);
        return true;
    }

    @Override
    public Boolean delete(News news) {
        newsRepository.delete(news);
        return true;
    }

    @Override
    public Boolean deleteById(int id) {
        newsRepository.delete(id);
        return true;
    }
}
