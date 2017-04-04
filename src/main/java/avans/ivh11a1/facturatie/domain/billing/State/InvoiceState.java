package avans.ivh11a1.facturatie.domain.billing.State;

/**
 * Created by kevin on 4-4-2017.
 */
public enum InvoiceState {
    CREATED("created"), APPROVING("approving"), PENDING("pending"), OVERDUE("overdue"), PAID("paid");

    private String name;

    InvoiceState(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public State getState() {
        switch (name) {
            case "created":
                return new CreatedState();
            case "approving":
                return new ApprovedState();
            case "pending":
                return new PendingState();
            case "overdue":
                return new OverdueState();
            case "paid":
                return new PaidState();
            default:
                return new CreatedState();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

