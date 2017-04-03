package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.Builders.*;
import avans.ivh11a1.facturatie.domain.billing.Declaration;
import avans.ivh11a1.facturatie.domain.customers.Customer;
import avans.ivh11a1.facturatie.service.imp.InvoiceGenerationImpl;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;


/**
 * Created by kevin on 3-4-2017.
 */
@RunWith(SpringRunner.class)
public class InvoiceGenerationTest {
    Map<String, Object> model;
    @Mock
    private BillingService billingService;
    @Mock
    private InsuranceService insuranceService;
    @Mock
    private CustomerService customerService;
    private InvoiceGeneration service;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(customerService.findByCsn(1)).thenReturn(CustomerBuilder.builder().build());
        Mockito.when(insuranceService.findPolicyByCustomer(any(Customer.class))).thenReturn(PolicyBuilder.builder().build());
        Mockito.when(billingService.findPaymentConditionById(1)).thenReturn(PaymentConditionBuilder.builder().build());
        Mockito.when(billingService.findInvoiceById(1)).thenReturn(InvoiceBuilder.builder().build());

        ArrayList<Declaration> declarations = new ArrayList<>();
        declarations.add(DeclarationBuilder.builder().build());
        declarations.add(DeclarationBuilder.builder().price(120).declaredAt(new Date(2017, 1, 1)).build());
        declarations.add(DeclarationBuilder.builder().price(130).compensated(0).treatment(TreatmentBuilder.builder().name("Hip injury").duration(30).price(80).build()).build());

        Mockito.when(billingService.findOpenDeclarations(any(Customer.class))).thenReturn(declarations);
        Mockito.when(billingService.findClosedDeclarations(any(Customer.class))).thenReturn(declarations);
        Mockito.when(billingService.findDeclarationByCustomer(any(Customer.class))).thenReturn(declarations);

        this.service = new InvoiceGenerationImpl(this.billingService, this.insuranceService, this.customerService);
        model = new HashedMap();
    }

    @Test
    public void printInvoiceWhenNoDeclarationsExists() throws Exception {
        Mockito.when(billingService.findDeclarationByCustomer(any(Customer.class))).thenReturn(null);
        model = this.service.printInvoice(1, model);
        assertThat(model.containsKey("Message")).isEqualTo(true);
        assertThat(model.get("Message")).isEqualTo("There was an error printing the invoice");
    }

    @Test
    public void printInvoiceWhenDeclarationsExists() throws Exception {
        model = this.service.printInvoice(1, model);
        assertThat(model.containsKey("Message")).isEqualTo(false);
        assertThat(model.get("SubTotal")).isEqualTo("€ 150,00");
        assertThat(model.get("VatAmount")).isEqualTo("€ 31,50");
    }

    @Test
    public void generateInvoiceWhenDeclarationsExists() throws Exception {
        model = this.service.generateInvoice(InvoiceBuilder.builder().build(), model);
        assertThat(model.containsKey("failure")).isEqualTo(false);
    }

    @Test
    public void generateInvoiceWhenNoDeclarationsExists() throws Exception {
        Mockito.when(billingService.findOpenDeclarations(any(Customer.class))).thenReturn(new ArrayList<>());
        model = this.service.generateInvoice(InvoiceBuilder.builder().build(), model);
        assertThat(model.containsKey("failure")).isEqualTo(true);
        assertThat(model.get("failure")).isEqualTo("No declarations for this customer at this moment");
    }

    @Test
    public void generateInvoiceWhenNoPolicyExists() throws Exception {
        Mockito.when(insuranceService.findPolicyByCustomer(any(Customer.class))).thenReturn(null);
        model = this.service.generateInvoice(InvoiceBuilder.builder().build(), model);
        assertThat(model.containsKey("failure")).isEqualTo(true);
        assertThat(model.get("failure")).isEqualTo("No policy for this customer at this moment");
    }
}
