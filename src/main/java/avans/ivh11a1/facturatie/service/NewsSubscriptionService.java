package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.NewsLetter.NewsSubscription;

/**
 * Created by kevin on 14-3-2017.
 */
public interface NewsSubscriptionService {
    Iterable<NewsSubscription> findAll();

    Iterable<NewsSubscription> findByNewsType(String type);

    NewsSubscription findOne(int id);

    Boolean save(NewsSubscription news);

    Boolean delete(NewsSubscription news);

    Boolean deleteById(int id);
}
