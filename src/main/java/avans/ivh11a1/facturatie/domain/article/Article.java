package avans.ivh11a1.facturatie.domain.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pascal on 12-Mar-17.
 */

@Entity
@Table(name ="invoices")
@Getter
@Setter
@NoArgsConstructor
public class Article {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "articleNumber")
    private int articleNumber;
    @Column(name = "version")
    private int version;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_posted")
    private Date postedDate;


    public Article(int articleNumber, int version, String title, String content, Date postedDate) {
        this.articleNumber = articleNumber;
        this.version = version;
        this.title = title;
        this.content = content;
        this.postedDate = postedDate;
    }
}
