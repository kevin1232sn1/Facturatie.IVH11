package avans.ivh11a1.facturatie.repository;

import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kevin on 10-3-2017.
 */
public interface NewsRepository extends CrudRepository<News, Integer> {
}
