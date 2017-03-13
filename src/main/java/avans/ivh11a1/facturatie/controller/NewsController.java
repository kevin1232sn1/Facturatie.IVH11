package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.NewsLetter.CompanyNews;
import avans.ivh11a1.facturatie.domain.NewsLetter.HealthNews;
import avans.ivh11a1.facturatie.domain.NewsLetter.InsuranceNews;
import avans.ivh11a1.facturatie.domain.NewsLetter.News;
import avans.ivh11a1.facturatie.service.CustomerService;
import avans.ivh11a1.facturatie.service.NewsService;
import avans.ivh11a1.facturatie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    public void TestMailing(Model theModel) {

        News news = new CompanyNews();
        news.setContent("Test important mailing!2");
        news.setTitle("Company news2");
        newsService.save(news);

    }

    @RequestMapping("")
    public String listNews(Model theModel) {
        Iterable<News> newsList = newsService.findAll();
        theModel.addAttribute("newsItems", newsList);
        return "news/index";
    }

    @GetMapping(value = "/create")
    String create(Model model) {
        CompanyNews news = new CompanyNews();
        news.setType("");
        model.addAttribute("newsItem", news);

        return "news/edit";
    }

    @PostMapping(value = "/create")
    String save(@ModelAttribute CompanyNews news, BindingResult bindingResult, Model model) {
        String type = news.getType();
        if (type.equals("Health")) {
            newsService.save(new HealthNews(news));
        } else if (type.equals("Insurance")) {
            newsService.save(new InsuranceNews(news));
        } else if (type.equals("Company")) {
            newsService.save(new CompanyNews(news));
        }
        model.addAttribute("success", "Policy successfully saved");

        return this.listNews(model);
    }

    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("newsItem", newsService.findOne(id));
        return "news/edit";
    }

    @GetMapping(value = "/delete/{id}")
    String delete(Model model, @PathVariable int id) {
        newsService.deleteById(id);
        model.addAttribute("success", "Policy successfully removed");

        return this.listNews(model);
    }

    @ModelAttribute("page")
    public String module() {
        return "news";
    }

}
