package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.repository.ArticleRepository;
import avans.ivh11a1.facturatie.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Pascal on 12-Mar-17.
 */
public class ArticleCaretakerImpl {


    @Autowired
    ArticleRepository ArticleRepository;

    public void addMemento(Article a){
        ArticleRepository.save(a);
    }

    public Article getArticle(int ArticleNumber, int version){
        return ArticleRepository.findByarticleNumberAndversion(ArticleNumber, version);
    }




}
