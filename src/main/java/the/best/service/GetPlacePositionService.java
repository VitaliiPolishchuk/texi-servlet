package the.best.service;

import the.best.web.data.LatLong;

public interface GetPlacePositionService {
    LatLong getPosition(String locationId);
}
