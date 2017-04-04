package avans.ivh11a1.facturatie.domain.billing.State;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

/**
 * Created by kevin on 3-4-2017.
 */
public interface State {
    void doAction(Invoice i);

    String currentState();
}
