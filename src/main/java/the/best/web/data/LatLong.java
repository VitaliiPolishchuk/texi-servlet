package the.best.web.data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LatLong {
    private double latitude;
    private double longitude;

    public LatLong(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
