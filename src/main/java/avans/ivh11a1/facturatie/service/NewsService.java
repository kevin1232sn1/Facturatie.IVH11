package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.NewsLetter.News;

/**
 * Created by kevin on 10-3-2017.
 */
public interface NewsService {
    void register(Observer o, String newsType);

    void unRegister(Observer o, String newsType);

    void notifyObserver(News news);

    Iterable<News> findAll();

    News findOne(int id);

    Boolean save(News news);

    Boolean delete(News news);

    Boolean deleteById(int id);
}
