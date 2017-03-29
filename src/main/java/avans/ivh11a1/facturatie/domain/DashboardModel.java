package avans.ivh11a1.facturatie.domain;

import javafx.util.Pair;

/**
 * Created by Tinne on 27-3-2017.
 */
public class DashboardModel {
    private Pair<String, Integer> box1;
    private Pair<String, Integer> box2;
    private Pair<String, Integer> box3;
    private Pair<String, Integer> box4;

    public Pair<String, Integer> getBox1() {
        return box1;
    }

    public void setBox1(Pair<String, Integer> box1) {
        this.box1 = box1;
    }

    public Pair<String, Integer> getBox2() {
        return box2;
    }

    public void setBox2(Pair<String, Integer> box2) {
        this.box2 = box2;
    }

    public Pair<String, Integer> getBox3() {
        return box3;
    }

    public void setBox3(Pair<String, Integer> box3) {
        this.box3 = box3;
    }

    public Pair<String, Integer> getBox4() {
        return box4;
    }

    public void setBox4(Pair<String, Integer> box4) {
        this.box4 = box4;
    }
}
