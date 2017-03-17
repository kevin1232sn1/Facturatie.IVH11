package avans.ivh11a1.facturatie.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by kevin on 14-3-2017.
 */
@Entity
@Table(name = "insurances")
@Getter
@Setter
@NoArgsConstructor
public class log {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "error")
    private String error;

    @Column(name = "date")
    private java.sql.Date date;
}
