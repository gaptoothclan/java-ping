package ping.cascade.integration.method;

import ping.lib.HttpClient;

import java.util.Map;

public class GetMethod implements IntegrationMethodInterface {

    private HttpClient httpClient;

    public GetMethod(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public String sendRequest(String url, Map<String, String> params) throws Exception {
        return httpClient.setUrl(url).setParams(params).get();
    }
}
