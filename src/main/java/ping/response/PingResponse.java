package ping.response;

import java.util.ArrayList;

public class PingResponse {

    private int customerId;
    private boolean success;
    private String redirect;
    private Double commission;
    private Double timeTaken;
    private ArrayList<String> errors;

    public PingResponse(){
        // Anything to construct ??
        this.customerId = 1;
        this.success = false;
        this.redirect = null;
        this.commission = 0.00;
        this.timeTaken = 1.0;
        this.errors = new ArrayList<String>();
        this.errors.add("No errors");
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public void setRedirect(String redirect){
        this.redirect = redirect;
    }

    public void setCommission(Double commission){
        this.commission = commission;
    }

    public void setTimeTaken(Double timeTaken){
        this.timeTaken = timeTaken;
    }

    public void addError(String error){
        this.errors.add( error );
    }


    public int getCustomerId(){
        return customerId;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getRedirect(){
        return redirect;
    }

    public Double getCommission(){
        return commission;
    }

    public Double getTimeTaken(){
        return timeTaken;
    }

    public ArrayList<String> getErrors(){
        return errors;
    }
}