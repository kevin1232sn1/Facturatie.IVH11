package avans.ivh11a1.facturatie.domain.NewsLetter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by kevin on 10-3-2017.
 */
@Entity
@Table(name = "news_subscription")
@Setter
@Getter
@NoArgsConstructor
public class NewsSubscription {

    @Column
    @NotEmpty
    String newsType;
    @Column
    String observerType;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int observerId;

    public NewsSubscription(String newsType, String observerType, int oId) {
        this.newsType = newsType;
        this.observerType = observerType;
        this.observerId = oId;
    }
}
