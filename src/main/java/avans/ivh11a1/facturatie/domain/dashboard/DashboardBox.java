package avans.ivh11a1.facturatie.domain.dashboard;

/**
 * Created by Tinne on 30-3-2017.
 */
public class DashboardBox {
    private String Title;
    private Integer Value;

    public DashboardBox(String title, Integer value) {
        Title = title;
        Value = value;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getValue() {
        return Value;
    }

    public void setValue(Integer value) {
        Value = value;
    }
}
