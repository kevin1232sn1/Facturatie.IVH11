package avans.ivh11a1.facturatie.domain.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Pascal on 03-Apr-17.
 */
@Setter
@Getter
@NoArgsConstructor
public class ArticleVersion {

    private int index;
    private String date;

    public ArticleVersion(int index, String date) {
        this.index = index;
        this.date = date;
    }
}
