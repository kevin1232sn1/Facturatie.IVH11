package avans.ivh11a1.facturatie.Builders;

import avans.ivh11a1.facturatie.domain.customers.Customer;

/**
 * Created by kevin on 3-4-2017.
 */
public class CustomerBuilder {
    public static Customer.CustomerBuilder builder() {
        return Customer.builder()
                .firstName("Kevin")
                .lastName("Bos")
                .email("knggbos@student.avans.nl")
                .city("Breda")
                .csn(1)
                .dateOfBirth("26/04/1996")
                .iban("NL37RABO0139552706")
                .streetName("Vijfhagen")
                .houseNumber("269")
                .phoneNumber("0681141787");
    }
}
