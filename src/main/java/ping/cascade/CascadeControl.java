package ping.cascade;

import ping.cascade.integration.IntegrationResponse;
import ping.domain.PingRequestBody;
import ping.cascade.CascadeResult;
import ping.cascade.integration.CascadeInterface;
import ping.repositories.CascadeListRepository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CascadeControl {
    
    private PingRequestBody pingRequestBody;
    private List<CascadeInterface> cascadeList;
    private CascadeResult cascadeResult;
    private DataSource dataSource;

    public CascadeControl(DataSource dataSource, PingRequestBody pingRequestBody){
        this.dataSource = dataSource;
        this.pingRequestBody = pingRequestBody;
        this.cascadeResult = new CascadeResult();
    }

    public CascadeResult runCascade(){

        return setCascadeList().sendLead();

    }

    private CascadeControl setCascadeList(){

        // Load Cascade List from supplied credentials

        CascadeListRepository cascadeListRepository = new CascadeListRepository(dataSource, pingRequestBody);

        this.cascadeList = cascadeListRepository.getCascadeList();

        return this;
    }

    private CascadeResult sendLead(){

        // Loop over each cascade object until we finish or have an accept

        // Exit this loop if we get a valid response

        // Need a cascade result

        for(CascadeInterface ci : cascadeList){
            IntegrationResponse integrationResponse = ci.sendLead(pingRequestBody);

            this.cascadeResult.incrementCascadeCount();

            if (integrationResponse.getIntegrationResponse() == 1){
                this.cascadeResult.setCascadeResult(integrationResponse.getIntegrationResponse());
                this.cascadeResult.setRedirectUrl(integrationResponse.getRedirectUrl());
                this.cascadeResult.setCascadeCommission(integrationResponse.getCommission());
                this.cascadeResult.setCascadeName(integrationResponse.getName());
                break;
            }

        }

        /*cascadeList.forEach((cascadeInterface)-> {
            IntegrationResponse integrationResponse = cascadeInterface.sendLead(pingRequestBody);

            this.cascadeResult.incrementCascadeCount();

            if (integrationResponse.getIntegrationResponse() == 1){
                this.cascadeResult.setCascadeResult(integrationResponse.getIntegrationResponse());
                this.cascadeResult.setRedirectUrl(integrationResponse.getRedirectUrl());
                this.cascadeResult.setCascadeCommission(integrationResponse.getCommission());
                this.cascadeResult.setCascadeName(integrationResponse.getName());
                break;
            }
        });*/

        /*cascadeList.forEach(
                cascadeInterface -> cascadeInterface.sendLead(pingRequestBody)
        );*/

        return cascadeResult;
    }

}