package avans.ivh11a1.facturatie.domain.administration;


import avans.ivh11a1.facturatie.domain.billing.Vat;
import avans.ivh11a1.facturatie.domain.validators.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Table(name = "insurance_company")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class InsuranceCompany {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min=1, max=255)
    private String companyName;

    @Column(name = "street_name")
    @Size(min=1, max=255)
    private String streetname;

    @Column(name = "house_number")
    @HouseNumber
    private String houseNumber;

    @Column(name = "zipcode")
    @Zipcode
    private String zipcode;

    @Column(name = "city")
    @Size(min=1, max=255)
    private String city;

    @Column(name = "phonenumber")
    @PhoneNumber
    private String phoneNumber;

    @Column(name = "email")
    @EmailAddress
    private String email;

    @Column(name = "kvk_number")
    @KVKNumber
    private Integer kvkNumber;

    @OneToOne
    @JoinColumn(name = "vat_id")
    private Vat vat;

    @Column(name = "btw_number")
    @BTWNumber
    private String btw;

    @Column(name = "iban")
    @IBAN
    private String iban;

    private InsuranceCompany(Builder builder) {
        this.id = builder.id;
        this.companyName = builder.companyName;
        this.streetname = builder.streetname;
        this.houseNumber = builder.houseNumber;
        this.zipcode = builder.zipcode;
        this.city = builder.city;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.kvkNumber = builder.kvkNumber;
        this.vat = builder.vat;
        this.btw = builder.btw;
        this.iban = builder.iban;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String companyName;
        private String phoneNumber;
        private String email;
        private String streetname;
        private String houseNumber;
        private String zipcode;
        private String city;
        private Integer kvkNumber;
        private Vat vat;
        private String btw;
        private String iban;

        public Builder id(final int id) {
            this.id = id;
            return this;
        }

        public Builder companyName(final String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder phoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }

        public Builder streetname(final String streetname) {
            this.streetname = streetname;
            return this;
        }

        public Builder houseNumber(final String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder zipcode(final String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public Builder city(final String city) {
            this.city = city;
            return this;
        }

        public Builder kvkNumber(final Integer kvkNumber) {
            this.kvkNumber = kvkNumber;
            return this;
        }

        public Builder vat(final Vat vat) {
            this.vat = vat;
            return this;
        }

        public Builder btw(final String btw) {
            this.btw = btw;
            return this;
        }

        public Builder iban(final String iban) {
            this.iban = iban;
            return this;
        }

        public InsuranceCompany build() {
            return new InsuranceCompany(this);
        }
    }
}
