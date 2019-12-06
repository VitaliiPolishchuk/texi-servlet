package the.best.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String id;
    private String locationName;

    public Location(String locationName) {
        this.locationName = locationName;
    }
}
