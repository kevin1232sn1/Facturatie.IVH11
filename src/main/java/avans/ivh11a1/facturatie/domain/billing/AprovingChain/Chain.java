package avans.ivh11a1.facturatie.domain.billing.AprovingChain;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

/**
 * Created by kevin on 3-4-2017.
 */
public class Chain {

    Chain next;

    public void Add(Chain c) {
        if (next == null) {
            next = c;
        } else {
            next.Add(c);
        }
    }

    public void Handle(Invoice i, String role) {
        if (next != null) {
            next.Handle(i, role);
        } else {
            System.out.println("Can't process");
        }
    }
}
