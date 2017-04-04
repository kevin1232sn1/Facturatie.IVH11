package avans.ivh11a1.facturatie.domain.billing.State;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

/**
 * Created by kevin on 3-4-2017.
 */
public class CreatedState implements State {
    @Override
    public void doAction(Invoice i) {
        i.setState(InvoiceState.APPROVING);
    }

    @Override
    public String currentState() {
        return "The invoice was created";
    }
}
