package ping.cascade.integration;

import ping.cascade.CascadeResult;
import ping.domain.PingRequestBody;

import java.util.List;
import java.util.Map;

public interface CascadeInterface {
    public IntegrationResponse sendLead(PingRequestBody pingRequestBody);

    // Get the type of the integration get or post
    public String getType();

    // Get the name of the integration
    public String getName();

    // Get the service url
    public String getUrl();

    // Check the criteria, return any errors
    public List<String> checkCriteria();

    // Format the data and return a map of key values
    public Map<String, String> formatData();

    // Process the returned result and return a cascade result
    public IntegrationResponse processData(IntegrationResponse integrationResponse);
}
