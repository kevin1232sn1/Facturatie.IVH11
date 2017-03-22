package avans.ivh11a1.facturatie.domain.administration;

/**
 * Created by kevin on 21-3-2017.
 */
public enum Role {
    ADMIN("Admin"), FINANCE("Finance"), ADMINISTRATION("Administration");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public static Role getRoleByName(String name) {

        for (Role role : Role.values()) {

            if (role.name.equals(name)) {
                return role;
            }
        }

        throw new IllegalArgumentException("No such role exists [" + name + "]");
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
