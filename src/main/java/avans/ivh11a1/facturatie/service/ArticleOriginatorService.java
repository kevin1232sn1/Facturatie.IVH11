package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.article.Article;

import java.util.Date;

/**
 * Created by Pascal on 12-Mar-17.
 */
public interface ArticleOriginatorService {
    void set(int articleNumber, int version, String title, String content, Date postedDate);

    Article saveToMemento();

    void restoreFromMemento(Article a);
}
