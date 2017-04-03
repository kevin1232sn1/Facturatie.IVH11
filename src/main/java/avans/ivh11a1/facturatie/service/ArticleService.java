package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.domain.article.ArticleVersion;
import avans.ivh11a1.facturatie.service.imp.ArticleCaretakerImpl;
import avans.ivh11a1.facturatie.service.imp.ArticleOriginatorImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pascal on 03-Apr-17.
 */
public interface ArticleService {
    List<Article> getDistinctArticlesByArticleNumber();

    ArrayList<ArticleVersion> getArrayVersion();

    Article getArticle(int articleNumber, int index);

    void NewArticle(Article article);

    void DeleteArticle(int articleNumber);
}
