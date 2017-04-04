package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.article.Memento;

import java.util.ArrayList;

/**
 * Created by Pascal on 03-Apr-17.
 */
public class ArticleCaretakerImpl {

    private ArrayList<Memento> savedStates = new ArrayList<Memento>();

    public void addMemento(Memento m) { savedStates.add(m); }
    public Memento getMemento(int index) { return savedStates.get(index); }

    public int getSavedStatesSize(){
        return savedStates.size();
    }
}
