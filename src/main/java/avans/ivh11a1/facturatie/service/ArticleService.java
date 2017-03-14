package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.domain.customers.Customer;

import java.util.Iterator;

/**
 * Created by Pascal on 12-Mar-17.
 */
public interface ArticleService {

    Article findByarticleNumberAndversion(int articleNumber, int version);
    Iterable<Article> findAll();
    void save(Article Article);

}
