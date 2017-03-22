package avans.ivh11a1.facturatie.domain.administration;

/**
 * Created by kevin on 21-3-2017.
 */
public class UserHolder {
    private Role userRole;

    public UserHolder(Role userRole) {
        this.userRole = userRole;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
