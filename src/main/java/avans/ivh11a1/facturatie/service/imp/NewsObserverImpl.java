package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.service.NotificationService;
import avans.ivh11a1.facturatie.service.Observer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kevin on 13-3-2017.
 */
public class NewsObserverImpl implements Observer {
    @Autowired
    private NotificationService notificationService;

    private Person person;
    private News news;

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void update(News news) {
        this.news = news;
        //notificationService.sendMessage(news, person);
        System.out.println("Observer : " + person.getFullName() + " news message " + news.getContent() + " news type: " + news.getType() + " Will send to: " + person.getEmail() + " Role: " + person.getRole());
    }

    @Override
    public String getType() {
        return person.getType();
    }

    @Override
    public int getId() {
        return person.getId();
    }
}
