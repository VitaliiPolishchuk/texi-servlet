package the.best.request;

import org.json.simple.JSONObject;

public interface HttpService {
    byte[] executeUrl(String url);
}
