package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.repository.ArticleRepository;
import avans.ivh11a1.facturatie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;

/**
 * Created by Pascal on 12-Mar-17.
 */

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    public String overview(Model model){
        Iterable<Article> Artilces = articleService.findAll();
        model.addAttribute("article", Artilces);
        return "article/index";


    }

}
