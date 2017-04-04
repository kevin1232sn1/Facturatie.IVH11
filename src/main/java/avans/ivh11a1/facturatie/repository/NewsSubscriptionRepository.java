package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.NewsLetter.NewsSubscription;
import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.domain.billing.Declaration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by kevin on 10-3-2017.
 */
public interface NewsSubscriptionRepository extends CrudRepository<NewsSubscription, Integer> {
    NewsSubscription findByNewsTypeAndObserverTypeAndObserverId(String newsType, String observerType, int id);



    List<NewsSubscription> findByNewsType(String newsType);

    @Query(
            value = "select * from news_subscription where observer_id = :observer and news_type = :newstype",
            nativeQuery = true)
    NewsSubscription findByNewsTypeAndObserverId(@Param("observer") int observer_id, @Param("newstype") String news_type);

}
