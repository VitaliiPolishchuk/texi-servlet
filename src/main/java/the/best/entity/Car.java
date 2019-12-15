package the.best.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

@Slf4j
@Getter
@Setter
public class Car {
    private int id;
    private String carName;
    private String photoUrl;
    private int carTypeId;
    private String carLocationId;
    private Boolean isActive;

    public Car() {

    }

    public Car(int id, String carName, String photoUrl, int carTypeId, String carLocationId, int isActive) {
        this.id = id;
        this.carName = carName;
        this.photoUrl = photoUrl;
        this.carTypeId = carTypeId;
        this.carLocationId = carLocationId;
        this.isActive = isActive == 0 ? false : true;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", carTypeId=" + carTypeId +
                ", carLocationId='" + carLocationId + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        return id == car.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}