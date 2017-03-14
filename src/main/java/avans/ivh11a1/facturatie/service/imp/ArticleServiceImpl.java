package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.repository.ArticleRepository;
import avans.ivh11a1.facturatie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Pascal on 12-Mar-17.
 */


@Service("ArticleService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;


    @Override
    public Article findByarticleNumberAndversion(int articleNumber, int version) {
        return articleRepository.findByarticleNumberAndversion(articleNumber, version);
    }

    @Override
    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void save(Article Article) {
        articleRepository.save( Article);
    }
}
