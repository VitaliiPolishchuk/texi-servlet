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
}
