package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.domain.article.ArticleVersion;
import avans.ivh11a1.facturatie.repository.ArticleRepository;
import avans.ivh11a1.facturatie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pascal on 03-Apr-17.
 */


@Service("ArticleService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class ArticleServiceImpl implements ArticleService {

    private ArticleCaretakerImpl ArticleCaretakerImpl = new ArticleCaretakerImpl();
    private ArticleOriginatorImpl ArticleOriginatorImpl = new ArticleOriginatorImpl();
    private ArrayList<ArticleVersion> ArrayVersion = new ArrayList<>();
    private int CurrentArticleId = 0;

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getDistinctArticlesByArticleNumber(){
        return articleRepository.findUniqueArticles();
    }


    private void BuildMemento(int articleNumber){
        if(articleNumber!=CurrentArticleId){
            ArticleCaretakerImpl = new ArticleCaretakerImpl();
            ArticleOriginatorImpl = new ArticleOriginatorImpl();

            List<Article> articles = articleRepository.findByarticleNumber(articleNumber);
            System.out.println("articles size: " + articles.size());

            for (int i = 0; i < articles.size(); i++) {
                ArticleOriginatorImpl.set(articles.get(i));
                ArticleCaretakerImpl.addMemento(ArticleOriginatorImpl.saveToMemento());
            }
            DifferentVersions();
        }
    }

    public Article getArticle(int articleNumber, int index){
        BuildMemento(articleNumber);
        if(index==-1 || index>(ArticleCaretakerImpl.getSavedStatesSize()-1)){
            return ArticleOriginatorImpl.restoreFromMemento(ArticleCaretakerImpl.getMemento(ArticleCaretakerImpl.getSavedStatesSize()-1));
        }else{
            return ArticleOriginatorImpl.restoreFromMemento(ArticleCaretakerImpl.getMemento(index));
        }

    }

    public void DifferentVersions(){
        ArrayVersion = new ArrayList<>();
        for (int i = 0; i < ArticleCaretakerImpl.getSavedStatesSize(); i++) {
            Article article = ArticleOriginatorImpl.restoreFromMemento(ArticleCaretakerImpl.getMemento(i));
            ArrayVersion.add(new ArticleVersion(i, article.getDate().toString()));
        }

    }

    public ArrayList<ArticleVersion> getArrayVersion() {
        return ArrayVersion;
    }

    private int newArticleNumber(){
        return articleRepository.getMaxArticleNumber()+1;
    }

    public void NewArticle(Article article){
        if(article.getArticleNumber()==0){
            article.setArticleNumber(newArticleNumber());
        }
        articleRepository.save(article);
    }

    public void DeleteArticle(int articleNumber){
        List<Article> articles = articleRepository.findByarticleNumber(articleNumber);
        articleRepository.delete(articles);

    }

}
