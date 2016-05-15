package ping.cascade.integration.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ping.cascade.integration.CascadeInterface;
import ping.domain.Cascade;

import java.util.ArrayList;

/**
 * Created by charlesbryant on 03/05/2016.
 */
public class CascadeFactory {

    private static final Logger log = LoggerFactory.getLogger(CascadeFactory.class);

    private ArrayList<String> cascadeList;

    public CascadeFactory(){
        cascadeList = new ArrayList<String>();
        cascadeList.add("PingOne");
        cascadeList.add("PingTwo");
    }

    public CascadeInterface getCascade(Cascade cascade) throws RuntimeException{
        if (!cascadeList.contains(cascade.getCascadeObj()) ){
            throw new RuntimeException("This cascade does not exist");
        }

        switch(cascade.getCascadeObj()){
            case "PingOne":
                return (CascadeInterface) new PingOne();
            case "PingTwo":
                return (CascadeInterface) new PingTwo();
        }

        throw new RuntimeException("This cascade does not exist");
    }
}
