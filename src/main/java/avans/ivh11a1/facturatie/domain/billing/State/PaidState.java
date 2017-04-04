package avans.ivh11a1.facturatie.domain.billing.State;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

/**
 * Created by kevin on 3-4-2017.
 */
public class PaidState implements State {
    @Override
    public void doAction(Invoice i) {

    }

    @Override
    public String currentState() {
        return "The invoice is paid";
    }
}
