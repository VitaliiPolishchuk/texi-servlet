package the.best.service;

import the.best.entity.Location;

import java.net.URISyntaxException;

public interface TimeCalculationService {
    long calculateTime(Location origin, Location destination);
}
