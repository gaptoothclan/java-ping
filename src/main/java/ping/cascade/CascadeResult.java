package ping.cascade;

public class CascadeResult {
    
    private int cascadeResult;
    private int cascadeCount;
    private Double cascadeCommission;
    private String redirectUrl;
    private String cascadeName;

    public CascadeResult(){
        this.cascadeCount = 0;
    }

    public int getCascadeResult(){
        return cascadeResult;
    }

    public int getCascadeCount(){
        return cascadeCount;
    }

    public Double getCascadeCommission(){
        return cascadeCommission;
    }

    public String getRedirectUrl(){
        return redirectUrl;
    }

    public String getCascadeName() { return cascadeName; }

    public void setCascadeResult(int cascadeResult){ this.cascadeResult = cascadeResult; }

    public void setCascadeCount(int cascadeCount){ this.cascadeCount = cascadeCount; }

    public void setCascadeCommission(Double cascadeCommission){ this.cascadeCommission = cascadeCommission; }

    public void setRedirectUrl(String redirectUrl){ this.redirectUrl = redirectUrl; }

    public void setCascadeName(String cascadeName){ this.cascadeName = cascadeName; }

    public void incrementCascadeCount(){ this.cascadeCount ++; }

}