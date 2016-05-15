package ping.domain;

import ping.domain.Address;
import ping.domain.Customer;
import ping.domain.RequestConfig;


public class PingRequestBody {

    private Address address;
    private Customer customer;
    private RequestConfig requestConfig;

    public PingRequestBody(){

    }

    public Address getAddress(){
        return address;
    }

    public Customer getCustomer(){
        return customer;
    }

    public RequestConfig getRequestConfig(){
        return requestConfig;
    }

    public String toString(){
        return "{\"address\": " + address.toString() 
            + ", \"customer\": " + customer.toString()
            + ", \"requestConfig\": " + requestConfig.toString() + "}";
    }
}