package avans.ivh11a1.facturatie.domain.billing.State;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

import java.util.Date;

/**
 * Created by kevin on 3-4-2017.
 */
public class OverdueState implements State {
    @Override
    public void doAction(Invoice i) {
        i.setState(InvoiceState.PAID);
        i.setDatePayed(new Date());
    }

    @Override
    public String currentState() {
        return "The invoice was overdue";
    }
}
