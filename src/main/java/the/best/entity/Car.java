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

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", CarTypeId=" + carTypeId +
                '}';
    }
}