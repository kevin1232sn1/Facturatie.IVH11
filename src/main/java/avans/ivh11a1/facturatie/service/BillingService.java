package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.billing.Declaration;
import avans.ivh11a1.facturatie.domain.billing.Invoice;
import avans.ivh11a1.facturatie.domain.billing.PaymentCondition;
import avans.ivh11a1.facturatie.domain.billing.Vat;
import avans.ivh11a1.facturatie.domain.customers.Customer;

/**
 * Created by kevin on 7-3-2017.
 */
public interface BillingService {

    Iterable<Invoice> findAllInvoices();
    Invoice findInvoiceById(int id);

    Boolean saveInvoice(Invoice invoice, boolean updateState);
    Boolean deleteInvoiceById(int id);
    Iterable<Invoice> findInvoicesByCustomer(Customer customer);

    Iterable<PaymentCondition> findAllPaymentConditions();
    PaymentCondition findPaymentConditionById(int id);
    boolean savePaymentCondition(PaymentCondition paymentCondition);
    boolean deletePaymentCondition(PaymentCondition paymentCondition);
    boolean deletePaymentConditionById(int id);


    Iterable<Declaration> findAllDeclarations();
    Iterable<Declaration> findDeclarationByCustomer(Customer customer);
    Iterable<Declaration> findOpenDeclarations(Customer customer);
    Iterable<Declaration> findClosedDeclarations(Customer customer);
    Boolean saveDeclaration(Declaration declaration);
    boolean deleteDeclaration(Declaration declaration);
    boolean deleteDeclarationById(int id);

    Iterable<Vat> findAllVat();
    Vat findVatById(int id);
    boolean saveVat(Vat vat);
    boolean deleteVat(Vat vat);
    boolean deleteVatById(int id);

}
