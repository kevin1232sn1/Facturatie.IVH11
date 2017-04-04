package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.article.*;
import avans.ivh11a1.facturatie.service.ArticleOriginator;

/**
 * Created by Pascal on 03-Apr-17.
 */
public class ArticleOriginatorImpl implements ArticleOriginator {

    private Article article;
   /* lots of memory consumptive private data that is not necessary to define the
    * state and should thus not be saved. Hence the small memento object. */

    @Override
    public void set(Article article) {
        System.out.println("Originator: Setting article to "+article.toString());
        this.article = article;
    }

    @Override
    public Memento saveToMemento() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(article);
    }
    @Override
    public Article restoreFromMemento(Memento m) {
        article = m.getSavedState();
        System.out.println("Originator: State after restoring from Memento: "+article.toString());
        return article;
    }

}
