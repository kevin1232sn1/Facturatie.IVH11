package avans.ivh11a1.facturatie.domain.NewsLetter;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by kevin on 10-3-2017.
 */
@Getter
@Setter
@Entity
public class InsuranceNews extends News {
    private static final long serialVersionUID = 1L;

    public InsuranceNews() {
        super.setType("Insurance");
    }
}
