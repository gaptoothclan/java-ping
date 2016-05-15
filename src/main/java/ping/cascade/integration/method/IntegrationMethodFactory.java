package ping.cascade.integration.method;

import ping.lib.HttpClient;

public class IntegrationMethodFactory {

    public IntegrationMethodInterface getIntegrationMethod(String type) throws RuntimeException {

        HttpClient httpClient = new HttpClient();
        if (type == "Post") {
            return new PostMethod(httpClient);
        } else if (type == "Get"){
            return new GetMethod(httpClient);
        }

        throw new RuntimeException("Can not find a IntegrationMethod of type ("+type+")");
    }

}
