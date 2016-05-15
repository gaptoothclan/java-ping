package ping.cascade.integration;

import java.util.ArrayList;
import java.util.List;

public class IntegrationResponse {
    
    private int integrationResponse;
    private Double commission;
    private String redirectUrl;
    private List<String> criteria;
    private String name;

    public IntegrationResponse(){
        this.commission = 0.00;
        this.criteria = new ArrayList<String>();
        this.integrationResponse = 0;
        this.name = "";
    }

    public void setCriteria(List<String> criteria){
        this.criteria = criteria;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRedirectUrl(String redirectUrl){ this.redirectUrl = redirectUrl; }

    public void setCommission(Double commission){ this.commission = commission; }

    public void setIntegrationResponse(int integrationResponse){ this.integrationResponse = integrationResponse; }

    public int getIntegrationResponse(){ return integrationResponse; }

    public Double getCommission(){ return commission; }

    public String getRedirectUrl(){ return redirectUrl; }

    public List<String> getCriteria(){ return criteria; }

    public String getName(){ return name; }

    public String toString(){
        String format = "{ \"integrationResponse\": \"%s\", \"commission\": \"%s\", "+
                "\"redirectUrl\": \"%s\", \"criteria\": \"%s\", \"name\": \"%s\", }";


        return String.format(format,
                Integer.toString(integrationResponse),
                Double.toString(commission),
                redirectUrl,
                String.join(", ", criteria),
                name);
    }

}