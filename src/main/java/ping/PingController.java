package ping;

import org.springframework.beans.factory.annotation.Autowired;
import ping.cascade.CascadeResult;
import ping.repositories.AddressRepository;
import ping.repositories.CustomerRepository;
import ping.response.PingResponse;
import ping.domain.PingRequestBody;
import ping.cascade.CascadeControl;
import ping.domain.Customer;
import ping.domain.Address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PingController {

    private static final Logger log = LoggerFactory.getLogger(PingController.class);
    private DataSource dataSource;

    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counter1 = new AtomicLong();

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping(value="/cascade", method = RequestMethod.POST)
    public PingResponse pingCustomer(@RequestBody PingRequestBody pingRequestBody, 
        @RequestHeader("Content-Type") String contentType){

        log.info(pingRequestBody.toString());

        CustomerRepository customerRepository = new CustomerRepository(dataSource);
        Customer customer = customerRepository.create(pingRequestBody.getCustomer());

        pingRequestBody.getAddress().setCustomerId(customer.getCustomerId());
        AddressRepository addressRepository = new AddressRepository(dataSource);
        Address address = addressRepository.create(pingRequestBody.getAddress());

        // Use request config api key to verify and get list of integrations
        CascadeControl cascadeControl = new CascadeControl(dataSource, pingRequestBody);

        CascadeResult cascadeResult = cascadeControl.runCascade();

        PingResponse pingResponse = new PingResponse();

        pingResponse.setCommission(cascadeResult.getCascadeCommission());
        pingResponse.setRedirect(cascadeResult.getRedirectUrl());

        if (cascadeResult.getCascadeResult() == 1){
            pingResponse.setSuccess(true);
        }

        return pingResponse;
    }

    @RequestMapping(value="/pingOne", method= RequestMethod.POST, produces={"application/json"})
    public PingResponse pingOne(){

        Long curCounter = counter.incrementAndGet();
        PingResponse pingResponse = new PingResponse();

        if ((int)(curCounter % 2) == 0){
            pingResponse.setSuccess(true);
            pingResponse.setRedirect("http://www.pingone.com/redirect");
            pingResponse.setCommission(2.50);
        }

        return pingResponse;
    }

    @RequestMapping(value="/pingTwo", method= RequestMethod.GET, produces={"application/json"})
    public PingResponse pingTwo(){

        Long curCounter = counter1.incrementAndGet();
        PingResponse pingResponse = new PingResponse();

        if ((int)(curCounter % 2) == 1){
            pingResponse.setSuccess(true);
            pingResponse.setRedirect("http://www.pingtwo.com/redirect");
            pingResponse.setCommission(1.25);
        }

        return pingResponse;
    }
/*
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public PingResponse handleRequestError(HttpServletRequest request, Exception exception){
        PingResponse pingResponse = new PingResponse();

        pingResponse.addError("A test error");
        pingResponse.addError(exception.getMessage());

        //http://stackoverflow.com/questions/14525982/getting-request-payload-from-post-request-in-java-servlet

        return pingResponse;
    }
*/
}