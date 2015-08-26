package User;

import java.util.HashMap;

/**
 * Created by GameMonkey on 26-08-2015.
 */
public class Personnel {

    private HashMap<Integer, User> usersByID;

    private Personnel(){

    }

    public static Personnel makeFromPersonnelFiles(String personnel, String salary){
        return new Personnel();
    }
}
