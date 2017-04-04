package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.billing.Invoice;

import java.util.Map;

/**
 * Created by kevin on 28-3-2017.
 */
public interface InvoiceGeneration {
    Map<String, Object> generateInvoice(Invoice invoice, Map<String, Object> model);

    Map<String, Object> printInvoice(int invoiceId, Map<String, Object> model);
}
