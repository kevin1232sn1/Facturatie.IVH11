package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.NewsLetter.NewsSubscription;
import avans.ivh11a1.facturatie.repository.NewsSubscriptionRepository;
import avans.ivh11a1.facturatie.service.NewsSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 14-3-2017.
 */
@Service("NewsSubscriptionService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class NewsSubscriptionServiceImpl implements NewsSubscriptionService {

    @Autowired
    private NewsSubscriptionRepository newsSubscriptionRepository;

    @Override
    public Iterable<NewsSubscription> findAll() {
        return newsSubscriptionRepository.findAll();
    }

    @Override
    public Iterable<NewsSubscription> findByNewsType(String type) {
        return newsSubscriptionRepository.findByNewsType(type);
    }

    @Override
    public NewsSubscription findOne(int id) {
        return newsSubscriptionRepository.findOne(id);
    }

    @Override
    public Boolean save(NewsSubscription subscription) {
        newsSubscriptionRepository.save(subscription);
        return true;
    }

    @Override
    public Boolean delete(NewsSubscription subscription) {
        newsSubscriptionRepository.delete(subscription);
        return true;
    }

    @Override
    public Boolean deleteById(int id) {
        newsSubscriptionRepository.delete(id);
        return true;
    }
}