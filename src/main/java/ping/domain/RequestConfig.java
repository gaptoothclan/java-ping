package ping.domain;

public class RequestConfig {
    private String responseFormat;
    private String apiKey;

    public RequestConfig(){

    }

    public String getResponseFormat(){
        return responseFormat;
    }

    public String getApiKey(){
        return apiKey;
    }

    public String toString(){
        return "{" + 
            "\"responseFormat\": \"" + responseFormat + "\", " +
            "\"apiKey\": \"" + apiKey + "\" " +
            "}";
    }

}