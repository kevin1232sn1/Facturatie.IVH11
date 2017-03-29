package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.billing.Invoice;
import org.springframework.ui.Model;

/**
 * Created by kevin on 28-3-2017.
 */
public interface InvoiceGeneration {
    Model generateInvoice(Invoice invoice, Model model);

    Model printInvoice(int invoiceId, Model model);
}
