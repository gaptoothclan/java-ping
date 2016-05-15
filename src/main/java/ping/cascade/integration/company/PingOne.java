package ping.cascade.integration.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import ping.cascade.CascadeResult;
import ping.cascade.integration.Integration;
import ping.cascade.integration.IntegrationResponse;
import ping.domain.Address;
import ping.domain.Customer;
import ping.response.PingResponse;

import java.io.IOException;
import java.util.*;

public class PingOne extends Integration {

    private String name;
    private String url;
    
    public PingOne(){
        name = "PingOne";
        url = "http://localhost:8080/pingOne";
    }

    @Override
    public String getType() {
        return "Post";
    }

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

    @Override
    public List<String> checkCriteria() {
        return new ArrayList<String>();
    }

    @Override
    public Map<String, String> formatData() {
        Map<String, String> formattedData = new HashMap<String, String>();

        Customer customer = pingRequestBody.getCustomer();
        Address address = pingRequestBody.getAddress();

        formattedData.put("t", customer.getTitle());
        formattedData.put("e", customer.getEmail());
        formattedData.put("es", customer.getEmploymentStatus());
        formattedData.put("fn", customer.getFirstName());
        formattedData.put("ln", customer.getLastName());
        formattedData.put("hp", customer.getHomePhone());
        formattedData.put("mp", customer.getMobilePhone());
        formattedData.put("add1", address.getAddressLine1());
        formattedData.put("add2", address.getAddressLine2());
        formattedData.put("add3", address.getAddressLine3());
        formattedData.put("tn", address.getTown());
        formattedData.put("pc", address.getPostCode());

        return formattedData;
    }

    @Override
    public IntegrationResponse processData(IntegrationResponse integrationResponse) {

        ObjectMapper objectMapper = new ObjectMapper();
        PingResponse pingResponse;
        try {
            pingResponse = objectMapper.readValue(this.response, PingResponse.class);
        } catch (IOException e) {
            integrationResponse.setIntegrationResponse(0);
            return integrationResponse;
        }

        if (pingResponse.getSuccess() == true){
            integrationResponse.setIntegrationResponse(1);
            integrationResponse.setCommission(pingResponse.getCommission());
            integrationResponse.setRedirectUrl(pingResponse.getRedirect());
        }

        // Process this.response;
        return integrationResponse;
    }


}