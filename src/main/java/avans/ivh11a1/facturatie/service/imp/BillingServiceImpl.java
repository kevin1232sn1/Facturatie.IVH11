package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.billing.Declaration;
import avans.ivh11a1.facturatie.domain.billing.Invoice;
import avans.ivh11a1.facturatie.domain.billing.PaymentCondition;
import avans.ivh11a1.facturatie.domain.billing.Vat;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.repository.DeclarationRepository;
import avans.ivh11a1.facturatie.repository.InvoiceRepository;
import avans.ivh11a1.facturatie.repository.PaymentConditionRepository;
import avans.ivh11a1.facturatie.repository.VatRepository;
import avans.ivh11a1.facturatie.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kevin on 7-3-2017.
 */
@Service("billingService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class BillingServiceImpl implements BillingService {
    private final InvoiceRepository invoiceRepository;

    private final PaymentConditionRepository paymentConditionRepository;

    private final DeclarationRepository declarationRepository;

    private final VatRepository vatRepository;

    @Autowired
    public BillingServiceImpl(InvoiceRepository invoiceRepository, PaymentConditionRepository paymentConditionRepository, DeclarationRepository declarationRepository, VatRepository vatRepository) {
        this.invoiceRepository = invoiceRepository;
        this.paymentConditionRepository = paymentConditionRepository;
        this.declarationRepository = declarationRepository;
        this.vatRepository = vatRepository;
    }

    @Override
    public Iterable<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findInvoiceById(int id) {
        return invoiceRepository.findOne(id);
    }

    @Override
    public Boolean saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
        return true;
    }

    @Override
    public Boolean deleteInvoiceById(int id) {
        Invoice invoice = findInvoiceById(id);
        if (invoice == null)
            return false;
        invoiceRepository.delete(invoice);
        return true;
    }

    @Override
    public Iterable<Invoice> findInvoicesByCustomer(Customer customer) {
        return  invoiceRepository.findByCustomer(customer);
    }


    @Override
    public Iterable<PaymentCondition> findAllPaymentConditions() {
        return paymentConditionRepository.findAll();
    }

    @Override
    public PaymentCondition findPaymentConditionById(int id) {
        return paymentConditionRepository.findOne(id);
    }

    @Override
    public boolean savePaymentCondition(PaymentCondition paymentCondition) {
        paymentConditionRepository.save(paymentCondition);
        return false;
    }

    @Override
    public boolean deletePaymentCondition(PaymentCondition paymentCondition) {
        paymentConditionRepository.delete(paymentCondition);
        return false;
    }

    @Override
    public boolean deletePaymentConditionById(int id) {
        paymentConditionRepository.delete(id);
        return false;
    }

    @Override
    public Iterable<Declaration> findAllDeclarations() {
        return declarationRepository.findAll();
    }

    @Override
    public Iterable<Declaration> findDeclarationByCustomer(Customer customer) {
        return declarationRepository.findByCustomer(customer);
    }

    @Override
    public Iterable<Declaration> findOpenDeclarations(Customer customer) {
        return  declarationRepository.findOpenDeclarations(customer.getCsn());
    }

    @Override
    public Iterable<Declaration> findClosedDeclarations(Customer customer) {
        return declarationRepository.findByCustomerAndInvoiceNotNullOrderById(customer);
    }

    @Override
    public Boolean saveDeclaration(Declaration declaration) {
        declarationRepository.save(declaration);
        return true;
    }

    @Override
    public boolean deleteDeclaration(Declaration declaration) {
        declarationRepository.delete(declaration);
        return false;
    }

    @Override
    public boolean deleteDeclarationById(int id) {
        declarationRepository.delete(id);
        return false;
    }

    @Override
    public Iterable<Vat> findAllVat() {
        return vatRepository.findAll();
    }

    @Override
    public Vat findVatById(int id) {
        return vatRepository.findOne(id);
    }

    @Override
    public boolean saveVat(Vat vat) {
        vatRepository.save(vat);
        return false;
    }

    @Override
    public boolean deleteVat(Vat vat) {
        vatRepository.delete(vat);
        return false;
    }

    @Override
    public boolean deleteVatById(int id) {
        vatRepository.delete(id);
        return false;
    }
}
