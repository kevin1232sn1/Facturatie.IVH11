package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.service.ArticleService;
import avans.ivh11a1.facturatie.service.imp.ArticleCaretakerImpl;
import avans.ivh11a1.facturatie.service.imp.ArticleOriginatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pascal on 03-Apr-17.
 */


@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService ArticleService;

    @Autowired
    public ArticleController(avans.ivh11a1.facturatie.service.ArticleService articleService) {
        ArticleService = articleService;
    }

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("Articles", ArticleService.getDistinctArticlesByArticleNumber().iterator());
        return "article/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ViewArticle(Model model, @PathVariable int id){

        Article article = ArticleService.getArticle(id, -1);

        model.addAttribute("Article", article);
        model.addAttribute("ArticleVerisons", ArticleService.getArrayVersion().iterator());

        return "article/view";
    }


    @RequestMapping(value = "/{id}/{version}", method = RequestMethod.GET)
    public String ViewArticle(Model model, @PathVariable int id, @PathVariable int version){
        Article article = ArticleService.getArticle(id, version);

        model.addAttribute("Article", article);
        model.addAttribute("ArticleVerisons", ArticleService.getArrayVersion().iterator());

        return "article/view";

    }


    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String CreateArticle(Model model){
        model.addAttribute("article", new Article());
        return "article/create";
    }



    @RequestMapping(value = "create/{id}", method = RequestMethod.GET)
    public String CreateArticle(Model model,@PathVariable int id){
        Article article = ArticleService.getArticle(id, -1);

        model.addAttribute("article", article);
        return "article/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String CreateArticle(Model model, Article article){
        System.out.println(article.toString());
        article.setDate(new Date());

        ArticleService.NewArticle(article);
        return "redirect:/article/";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String DeleteArticle(Model model,@PathVariable int id){
        ArticleService.DeleteArticle(id);
        return "redirect:/article/";
    }


}
