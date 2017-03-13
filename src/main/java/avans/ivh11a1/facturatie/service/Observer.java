package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.domain.Person;

/**
 * Created by kevin on 10-3-2017.
 */
public interface Observer {
    void update(News news);

    String getType();

    int getId();

    void setPerson(Person person);

}
