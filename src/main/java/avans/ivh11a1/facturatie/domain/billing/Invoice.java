package avans.ivh11a1.facturatie.domain.billing;

import avans.ivh11a1.facturatie.domain.billing.State.InvoiceState;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import lombok.*;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


@Entity
@Table(name ="invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "vat_id")
    private Vat vat;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_created")
    private Date dateCreated;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_payed")
    private Date datePayed;
    @Column(name = "state")
    private InvoiceState state;
    @OneToOne
    @JoinColumn(name = "payment_condition_id")
    private PaymentCondition paymentCondition;
    @Column
    private Boolean approved;

    public String getUserFriendlyDatePayed() {
        if (this.datePayed == null) {
            return "";
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.datePayed);
    }

    public PaymentCondition getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(PaymentCondition paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public boolean isOverdue() {
        Date start = new Date();

        if (this.datePayed != null) {
            start = this.datePayed;
        }

        Date created = this.dateCreated;

        long duration  = start.getTime() - created.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

        return diffInDays > this.paymentCondition.getPeriodInDays();
    }

    public String getDateToBePayed() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateCreated);
        cal.add(Calendar.DATE, paymentCondition.getPeriodInDays());
        return df.format(cal.getTime());
    }
}
