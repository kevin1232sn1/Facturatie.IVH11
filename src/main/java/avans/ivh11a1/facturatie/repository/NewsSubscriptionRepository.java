package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.NewsLetter.NewsSubscription;
import avans.ivh11a1.facturatie.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by kevin on 10-3-2017.
 */
public interface NewsSubscriptionRepository extends CrudRepository<NewsSubscription, Integer> {
    NewsSubscription findByNewsTypeAndObserverTypeAndObserverId(String newsType, String observerType, int id);

    List<NewsSubscription> findByNewsType(String newsType);
    NewsSubscription findByNewsTypeAndObserverId(String newsType, int observerId);
}
