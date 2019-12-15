package the.best.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Discount {
    private String id;
    private double percent;

    public Discount() {

    }

    public Discount(String id, double percent) {
        this.id = id;
        this.percent = percent;
    }
}
