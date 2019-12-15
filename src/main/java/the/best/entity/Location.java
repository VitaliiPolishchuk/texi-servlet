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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        return locationName.equals(location.locationName);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationName='" + locationName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return locationName.hashCode();
    }
}
