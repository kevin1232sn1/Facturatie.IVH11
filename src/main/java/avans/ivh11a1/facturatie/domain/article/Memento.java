package avans.ivh11a1.facturatie.domain.article;

/**
 * Created by Pascal on 03-Apr-17.
 */
public class Memento {
    private Article article;

    public Memento(Article articleToSave) { article = articleToSave; }
    public Article getSavedState() { return article; }
}
