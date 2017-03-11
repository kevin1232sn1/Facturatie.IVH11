package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.NewsLetter.News;

/**
 * Created by kevin on 10-3-2017.
 */
public interface Subject {
    void register(Observer o, String newsType);

    void unRegister(Observer o, String newsType);

    void notifyObserver(News news);
}
