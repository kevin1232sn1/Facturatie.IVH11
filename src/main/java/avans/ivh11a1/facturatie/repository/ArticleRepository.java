package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.NewsLetter.NewsSubscription;
import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Pascal on 12-Mar-17.
 */
@Transactional
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    Article findByarticleNumberAndversion(int articleNumber, int version);

}
