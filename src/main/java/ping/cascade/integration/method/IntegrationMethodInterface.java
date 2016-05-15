package ping.cascade.integration.method;

import java.util.Map;

public interface IntegrationMethodInterface {
    public String sendRequest(String url, Map<String, String> params) throws Exception;
}
