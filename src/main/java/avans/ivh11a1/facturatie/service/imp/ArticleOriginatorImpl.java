package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.article.Article;
import avans.ivh11a1.facturatie.service.ArticleOriginatorService;

import java.util.Date;

/**
 * Created by Pascal on 12-Mar-17.
 */
public class ArticleOriginatorImpl implements ArticleOriginatorService {

    private int articleNumber;
    private int version;
    private String title;
    private String content;
    private Date postedDate;


    @Override
    public void set(int articleNumber, int version, String title, String content, Date postedDate) {
        this.articleNumber = articleNumber;
        this.version = version;
        this.title = title;
        this.content = content;
        this.postedDate = postedDate;
    }

    @Override
    public Article saveToMemento(){
        return new Article(articleNumber,version,title,content,postedDate);

    }

    @Override
    public void restoreFromMemento(Article a){
        this.articleNumber = a.getArticleNumber();
        this.version = a.getVersion();
        this.title = a.getTitle();
        this.content = a.getContent();
        this.postedDate = a.getPostedDate();
    }

}
