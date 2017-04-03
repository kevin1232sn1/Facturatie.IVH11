package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.domain.article.Memento;

/**
 * Created by Pascal on 03-Apr-17.
 */
public interface ArticleOriginator {
    void set(Article article);

    Memento saveToMemento();

    Article restoreFromMemento(Memento m);
}
