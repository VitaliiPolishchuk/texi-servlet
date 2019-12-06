package the.best.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

@Slf4j
public class HttpServiceImpl implements HttpService {

    private static final HttpClient httpClient = new HttpClient();
    private static final HttpService httpService = new HttpServiceImpl();

    @Override
    public byte[] executeUrl(String url) {

        GetMethod method = new GetMethod(url);

        byte[] res = null;

        try {
            int statusCode = httpClient.executeMethod(method);
            if(statusCode == HttpStatus.SC_OK){
                return method.getResponseBody();
            }
        } catch (IOException e) {
            log.error("Error during execution url: " + url + ".\nWith message " + e.getMessage());
        }

        return res;
    }

    public static HttpService getInstance(){
        return httpService;
    }
}
