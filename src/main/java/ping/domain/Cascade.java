package ping.domain;

/**
 * Created by charlesbryant on 03/05/2016.
 */
public class Cascade {
    private int cascadeId;
    private String cascadeName;
    private String cascadeObject;

    public Cascade(int cascadeId, String cascadeName, String cascadeObject){
        this.cascadeId = cascadeId;
        this.cascadeName = cascadeName;
        this.cascadeObject = cascadeObject;
    }

    public int getCascadeId(){
        return cascadeId;
    }

    public String getCascadeName(){
        return cascadeName;
    }

    public String getCascadeObj(){
        return cascadeObject;
    }

    public String toString(){
        return "cascadeId: " + Integer.toString(cascadeId) +
                " cascadeName: " + cascadeName +
                " cascadeObject: " + cascadeObject;
    }

}
