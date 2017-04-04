package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.repository.NewsRepository;
import avans.ivh11a1.facturatie.repository.NewsSubscriptionRepository;
import avans.ivh11a1.facturatie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 10-3-2017.
 */
@Service("NewsService")
@Repository
@Transactional(rollbackFor = StateException.class)
@Scope("singleton")
public class NewsServiceImpl implements NewsService, Subject {

    private final NewsRepository newsRepository;
    private NewsSubscriptionRepository newsSubscriptionRepository;
    private PersonFactoryService factoryService;
    private NotificationService notificationService;
    private List<Observer> observerList;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, NewsSubscriptionRepository newsSubscriptionRepository, PersonFactoryService factoryService, NotificationService notificationService) {
        this.newsSubscriptionRepository = newsSubscriptionRepository;
        this.factoryService = factoryService;
        this.notificationService = notificationService;
        this.newsRepository = newsRepository;
        observerList = new ArrayList<>();
        register(new NewsObserverImpl(newsSubscriptionRepository, factoryService, notificationService));
    }

    @Override
    public void register(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unRegister(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObserver(Object object) {
        for (Observer o : observerList) {
            o.update(object);
        }
    }

    @Override
    public Iterable<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findOne(int id) {
        return newsRepository.findOne(id);
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
