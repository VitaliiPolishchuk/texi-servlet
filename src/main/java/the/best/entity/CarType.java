package the.best.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class CarType {
    private int id;
    private String typeName;
    private double priceBooking;
    private double pricePerKm;
    private int capacity;

    public CarType() {
    }

    public CarType(int id, String typeName, double priceBooking, double pricePerKm, int capacity) {
        this.id = id;
        this.typeName = typeName;
        this.priceBooking = priceBooking;
        this.pricePerKm = pricePerKm;
        this.capacity = capacity;
    }
}
