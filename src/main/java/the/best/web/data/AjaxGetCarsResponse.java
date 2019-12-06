package the.best.web.data;

import lombok.Getter;
import lombok.Setter;
import the.best.entity.Car;

@Getter
@Setter
public class AjaxGetCarsResponse {
    private Car car;
    private LatLong latLong;
}
