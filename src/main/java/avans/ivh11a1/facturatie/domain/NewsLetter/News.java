package avans.ivh11a1.facturatie.domain.NewsLetter;

import avans.ivh11a1.facturatie.domain.MailTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by kevin on 10-3-2017.
 */

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
public abstract class News {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_published")
    private java.sql.Date datePublished;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column
    private String type;

    public abstract MailTemplate getMailTemplate();
}
