package the.best.web.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarForm {
    private int id;
    private String name;
    private String photoUrl;
    private String locationId;
    private int carType;
    private boolean isAvailable;

    @Override
    public String toString() {
        return "CarForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", locationId='" + locationId + '\'' +
                ", carType=" + carType +
                ", isAvailable=" + isAvailable +
                '}';
    }
}


