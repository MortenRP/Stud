package User;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by GameMonkey on 26-08-2015.
 */
public class Personnel {

    private HashMap<Integer, User> usersByID;

    private Personnel(){
        usersByID = new HashMap<>();
    }

    //Static make method for a more describing name
    public static Personnel makeFromPersonnelFiles(String personnel, String salary){
        return new Personnel();
    }
    

    //@ToDo Add error handling if user is in system already.
    private void addUser(String name, int ID, JobFunction jobFunction){

    }

    public void updateUserDate(int ID, Date date){

    }

    private int getHoursWorkedOfUser(int ID){
        return 0;
    }

    private JobFunction getGroupOfUser(int ID){
        return null;
    }


}
