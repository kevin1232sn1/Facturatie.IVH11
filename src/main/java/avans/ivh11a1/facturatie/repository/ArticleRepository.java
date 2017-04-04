package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Pascal on 03-Apr-17.
 */

@Transactional
public interface ArticleRepository  extends CrudRepository<Article, Integer> {

    @Query(value = "select *  from articles group by (article_number)", nativeQuery = true)
    List<Article> findUniqueArticles();

    List<Article> findByarticleNumber(int articleNumber);

    @Query(value = "select max(article_number) from articles", nativeQuery = true)
    int getMaxArticleNumber();


}
