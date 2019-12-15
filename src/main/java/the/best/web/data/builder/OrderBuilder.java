package the.best.web.data.builder;

import lombok.Getter;
import the.best.entity.Car;
import the.best.entity.CarType;
import the.best.entity.Location;


@Getter
public class OrderBuilder {
    private CarType carType;
    private Car car;
    private Location origin;
    private Location destination;
    private Location carLocation;
    private int timeToReachDestination;
    private int timeToReachOrigin;
    private double wholePrice;
    private double discountPrice;

    public OrderBuilder setCarType(CarType carType) {
        this.carType = carType;
        return this;
    }

    public OrderBuilder setCar(Car car) {
        this.car = car;
        return this;
    }

    public OrderBuilder setOrigin(Location origin) {
        this.origin = origin;
        return this;
    }

    public OrderBuilder setDestination(Location destination) {
        this.destination = destination;
        return this;
    }

    public OrderBuilder setCarLocation(Location location) {
        this.carLocation = location;
        return this;
    }

    public OrderBuilder setTimeToReachDestination(int timeToReachDestination) {
        this.timeToReachDestination = timeToReachDestination;
        return this;
    }

    public OrderBuilder setTimeToReachOrigin(int timeToReachOrigin) {
        this.timeToReachOrigin = timeToReachOrigin;
        return this;
    }

    public OrderBuilder setWholePrice(double wholePrice) {
        this.wholePrice = wholePrice;
        return this;
    }

    public OrderBuilder setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }
}
