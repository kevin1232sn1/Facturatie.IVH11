package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.NewsLetter.CompanyNews;
import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.service.CustomerService;
import avans.ivh11a1.facturatie.service.NewsService;
import avans.ivh11a1.facturatie.service.UserService;
import avans.ivh11a1.facturatie.service.imp.NewsObserverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kevin on 11-3-2017.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public void TestMailing(Model theModel) {
        Customer p1 = new Customer("Kevin1", "Bos", "Vijfhagen", "269", "4812XT", "Breda",
                "26-04-1996", "0681131786", "k.bos@gmx.com", "1234"  );
        Customer p2 = new Customer("Kevin2", "Bos", "Vijfhagen", "269", "4812XT", "Breda",
                "26-04-1996", "0681131786", "k.bos@gmx.com", "1234"  );
        Customer p3 = new Customer("Kevin3", "Bos", "Vijfhagen", "269", "4812XT", "Breda",
                "26-04-1996", "0681131786", "k.bos@gmx.com", "1234"  );
        Customer p4 = new Customer("Kevin4", "Bos", "Vijfhagen", "269", "4812XT", "Breda",
                "26-04-1996", "0681131786", "k.bos@gmx.com", "1234"  );


        User u1 = new User("kevin.bos@gmx.com", "Kevin", "Worker1");
        User u2 = new User("kevin.bos@gmx.com", "Kevin", "Worker2");
        User u3 = new User("kevin.bos@gmx.com", "Kevin", "Worker3");

        customerService.save(p1);
        customerService.save(p2);
        customerService.save(p3);
        customerService.save(p4);

        userService.save(u1);
        userService.save(u2);
        userService.save(u3);

        NewsObserverImpl notificationObserver = new NewsObserverImpl();
        NewsObserverImpl notificationObserver1 = new NewsObserverImpl();
        NewsObserverImpl notificationObserver2 = new NewsObserverImpl();
        NewsObserverImpl notificationObserver3 = new NewsObserverImpl();
        NewsObserverImpl notificationObserver4 = new NewsObserverImpl();
        NewsObserverImpl notificationObserver5 = new NewsObserverImpl();
        NewsObserverImpl notificationObserver6 = new NewsObserverImpl();

        notificationObserver.setPerson(p1);
        notificationObserver1.setPerson(p2);
        notificationObserver2.setPerson(p3);
        notificationObserver3.setPerson(p4);
        notificationObserver4.setPerson(u1);
        notificationObserver5.setPerson(u2);
        notificationObserver6.setPerson(u3);

        newsService.register(notificationObserver, "Health");
        newsService.register(notificationObserver1, "Health");
        newsService.register(notificationObserver2, "Company");
        newsService.register(notificationObserver3, "Insurance");
        newsService.register(notificationObserver4, "Health");
        newsService.register(notificationObserver5, "Company");
        newsService.register(notificationObserver6, "Company");

        News news = new CompanyNews();
        news.setContent("Test important mailing!2");
        news.setTitle("Company news2");
        newsService.save(news);


    }
}
