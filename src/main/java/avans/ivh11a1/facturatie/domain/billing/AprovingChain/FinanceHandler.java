package avans.ivh11a1.facturatie.domain.billing.AprovingChain;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

/**
 * Created by kevin on 4-4-2017.
 */
public class FinanceHandler extends Chain {

    @Override
    public void Handle(Invoice i, String role) {
        if (role.equals("finance")) {
            i.setApproved(true);
        } else super.Handle(i, role);
    }

}
