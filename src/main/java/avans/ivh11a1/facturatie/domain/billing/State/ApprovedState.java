package avans.ivh11a1.facturatie.domain.billing.State;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

/**
 * Created by kevin on 3-4-2017.
 */
public class ApprovedState implements State {


    @Override
    public void doAction(Invoice i) {
        if (i.isApproved())
            i.setState(InvoiceState.PENDING);
    }

    @Override
    public String currentState() {
        return "The invoice needs to be approved by an user in the right role!";
    }
}
