package ping.cascade.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ping.PingController;
import ping.cascade.integration.method.IntegrationMethodFactory;
import ping.cascade.integration.method.IntegrationMethodInterface;
import ping.domain.PingRequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Integration implements CascadeInterface {

    protected PingRequestBody pingRequestBody;
    protected IntegrationResponse integrationResponse;
    protected Map<String, String> formattedData;
    protected String response;

    private static final Logger log = LoggerFactory.getLogger(PingController.class);

    public IntegrationResponse sendLead(PingRequestBody pingRequestBody) {
        this.pingRequestBody = pingRequestBody;
        log.info("Sending a lead through "+ getName() +"!");

        this.integrationResponse = new IntegrationResponse();
        this.integrationResponse.setName(this.getName());

        // Check the integration criteria if it fails then exit
        List<String> criteria = this.checkCriteria();

        if (criteria.size() > 0){
            this.integrationResponse.setCriteria(criteria);
            log.info(this.integrationResponse.toString());
            return this.integrationResponse;
        }

        this.formattedData = formatData();

        log.info("Formatted Data: " + formattedData.toString() );

        IntegrationMethodFactory integrationMethodFactory = new IntegrationMethodFactory();
        IntegrationMethodInterface method;
        try {
            method = integrationMethodFactory.getIntegrationMethod( getType() );
        } catch (RuntimeException e){
            List<String> factoryCriteria = new ArrayList<String>();
            factoryCriteria.add("Cascade method does not exist");

            this.integrationResponse.setCriteria(factoryCriteria);

            return this.integrationResponse;
        }


        try {
            this.response = method.sendRequest(getUrl(), this.formattedData);
        } catch (Exception e) {
            List<String> factoryCriteria = new ArrayList<String>();
            factoryCriteria.add("Cascade failed to send");

            this.integrationResponse.setCriteria(factoryCriteria);

            return this.integrationResponse;
        }

        log.info("Cascade Response: " + this.response);

        this.integrationResponse = this.processData(this.integrationResponse);

        log.info("Integration Response: "+this.integrationResponse.toString());

        return this.integrationResponse;
    }

}
