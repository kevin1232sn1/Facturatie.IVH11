package avans.ivh11a1.facturatie.domain.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pascal on 03-Apr-17.
 */
@Entity
@Table(name ="articles")
@Getter
@Setter
@NoArgsConstructor
public class Article {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "articleNumber")
    private int articleNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    @Basic(fetch = FetchType.LAZY)
    private String content;

    @Column(name = "date")
    private Date date;


    public Article(int articleNumber, String title, String content, Date date) {
        this.articleNumber = articleNumber;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", articleNumber=" + articleNumber +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
