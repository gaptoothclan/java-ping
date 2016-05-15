package ping.domain;

public class Address {
    private int addressId;
    private int customerId;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String town;
    private String county;
    private String postCode;

    public Address(){

    }

    public int getAddressId(){ return customerId; }

    public void setAddressId(int addressId){ this.addressId = addressId; }

    public int getCustomerId(){ return customerId; }

    public void setCustomerId(int customerId){ this.customerId = customerId; }

    public String getAddressLine1(){
        return addressLine1;
    }

    public String getAddressLine2(){
        return addressLine2;
    }

    public String getAddressLine3(){
        return addressLine3;
    }

    public String getTown(){
        return town;
    }

    public String getCounty(){
        return county;
    }

    public String getPostCode(){
        return postCode;
    }

    public String toString(){
        return "{\"addressLine1\": \"" +addressLine1+
            "\", \"addressLine2\": \"" +addressLine2+
            "\", \"addressLine3\": \"" +addressLine3+
            "\", \"town\": \""+county+
            "\", \"postCode\": \""+postCode+"\"}";
    }
}