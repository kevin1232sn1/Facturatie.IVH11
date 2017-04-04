package avans.ivh11a1.facturatie.API.V1;

import avans.ivh11a1.facturatie.domain.Person;
import avans.ivh11a1.facturatie.service.NewsService;
import avans.ivh11a1.facturatie.service.NewsSubscriptionService;
import avans.ivh11a1.facturatie.service.PersonFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pascal on 04-Apr-17.
 */

@RestController
@RequestMapping("/api/v1/")
public class NewsAPIController {

    private final NewsService newsService;

    private final PersonFactoryService personFactoryService;

    private final NewsSubscriptionService newsSubscriptionService;

    @Autowired
    public NewsAPIController(NewsService newsService, PersonFactoryService personFactoryService, NewsSubscriptionService newsSubscriptionService) {
        this.newsService = newsService;
        this.personFactoryService = personFactoryService;
        this.newsSubscriptionService = newsSubscriptionService;
    }


    @RequestMapping(value = "person/{type}/{email}/", method = RequestMethod.GET)
    public Person getPerson(Model model, @PathVariable String email, @PathVariable String type){
        System.out.println("Email: " + email + " Type: " + type);
        Person person = personFactoryService.getPerson(type,email);
        return person;
    }

    @RequestMapping(value = "subscribe/{type}/{email}/{subscribetype}", method = RequestMethod.GET)
    public Person PersonSubscribe(Model model,@PathVariable String type,@PathVariable String email,@PathVariable String subscribetype){
        System.out.println("Email: " + email + " Type: " + type);
        Person person = personFactoryService.getPerson(type,email);
        newsSubscriptionService.saveSubscription(person, subscribetype);

        return person;
    }

    @RequestMapping(value = "subscribe/{type}/{email}/{subscribetype}", method = RequestMethod.DELETE)
    public Person PersonUnsubscribe(Model model,@PathVariable String type,@PathVariable String email,@PathVariable String subscribetype){
        System.out.println("Email: " + email + " Type: " + type);
        Person person = personFactoryService.getPerson(type,email);

        newsSubscriptionService.saveUnsubscription(person.getId(), subscribetype);

        return person;
    }



}
