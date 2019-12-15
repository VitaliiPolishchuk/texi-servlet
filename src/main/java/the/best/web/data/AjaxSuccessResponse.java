package the.best.web.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxSuccessResponse {
    private String url;
    private String message;
    private boolean success;
}
