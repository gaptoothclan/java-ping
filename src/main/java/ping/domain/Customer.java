package ping.domain;

import java.util.Date;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Customer {

    private int customerId;
    @Size(max=5)
    private String title;
    @Size(max=50)
    private String firstName;
    @Size(max=50)
    private String lastName;
    @Size(max=100)
    private String email;
    // http://stackoverflow.com/questions/12463049/date-format-mapping-to-json-jackson
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date dob;
    @Size(max=12)
    private String homePhone;
    @Size(max=12)
    private String mobilePhone;
    private String employmentStatus;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date employmentStartDate;
    private Double salaryAmount;

    public Customer(){

    }

    public int getCustomerId() { return customerId; }

    public void setCustomerId(int customerId){ this.customerId = customerId; }

    public String getTitle(){
        return title;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public Date getDob(){
        return dob;
    }

    public String getHomePhone(){
        return homePhone;
    }

    public String getMobilePhone(){
        return mobilePhone;
    }

    public String getEmploymentStatus(){
        return employmentStatus;
    }

    public Date getEmploymentStartDate(){
        return employmentStartDate;
    }

    public Double getSalaryAmount(){
        return salaryAmount;
    }

    public String toString(){
        return "{" + 
            "\"title\": \"" + title + "\", " +
            "\"firstName\": \"" + firstName + "\", " +
            "\"lastName\": \"" + lastName + "\", " +
            "\"email\": \"" + email + "\", " +
            "\"dob\": \"" + dob + "\", " +
            "\"homePhone\": \"" + homePhone + "\", " +
            "\"mobilePhone\": \"" + mobilePhone + "\", " +
            "\"employmentStatus\": \"" + employmentStatus + "\", " +
            "\"employmentStartDate\": \"" + employmentStartDate + "\", " +
            "\"salaryAmount\": \"" + salaryAmount + "\" " +
            "}";
    }

}