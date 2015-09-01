package User;

import Schedule.SheetWorker;
import jxl.Sheet;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by GameMonkey on 26-08-2015.
 */
public class Personnel {

    private HashMap<Integer, User> usersByID;
    private SheetWorker volunteers;
    private SheetWorker shifts;

    public Personnel(Sheet volunteers){
        this.volunteers = new SheetWorker(volunteers);

        usersByID = new HashMap<>();
        generateUsers();
    }



    //@ToDo Add error handling if user is in system already.
    private void generateUsers(){
        int columnFirstName =  volunteers.getColumnPos("Fornavn");
        int columnLastName =  volunteers.getColumnPos("Efternavn");
        int columnID = volunteers.getColumnPos("Loennummer");
        int columnNumber = volunteers.getColumnPos("Mobil");

        int rows = volunteers.getRows();

        for(int i = 1; i < rows; ++i){
            String name = volunteers.getCellContent(columnFirstName, i)
                    + " "
                    + volunteers.getCellContent(columnLastName, i);

            String userID = volunteers.getCellContent(columnID, i);
            String number = volunteers.getCellContent(columnNumber, i);
            JobFunction jobFunction = getJobFunction(i);

            if(!userID.isEmpty()){
                int id = Integer.parseInt(userID);
                User user;

                if(!number.isEmpty()){
                    user = new User(name, id, jobFunction, number);
                }
                else{
                    user = new User(name, id, jobFunction);
                }

                usersByID.put(id, user);

            }

        }
    }

    public HashMap<Integer, User> getUsers(){
        return usersByID;
    }

    public void updateUserDate(int userID, Date date){

    }

    private JobFunction getJobFunction(int row){
        int columnAdmin = volunteers.getColumnPos("Personalegruppe: Administration");
        int columnBar = volunteers.getColumnPos("Personalegruppe: Bartender");
        int columnMusic = volunteers.getColumnPos("Personalegruppe: Musikfrivillige");
        int columnLight = volunteers.getColumnPos("Personalegruppe: Lysafvikler/Light Technician");

        if(!volunteers.getCellContent(columnAdmin, row).isEmpty()){
            return JobFunction.Other;
        }
        else if(!volunteers.getCellContent(columnBar, row).isEmpty()){
            return JobFunction.Bartender;
        }
        else if(!volunteers.getCellContent(columnMusic, row).isEmpty()){
            return JobFunction.Music;
        }
        else if(!volunteers.getCellContent(columnLight, row).isEmpty()){
            return JobFunction.Light;
        }
        else{
            return JobFunction.Other;
        }
    }

    private int getHoursWorkedOfUser(int ID){
        return 0;
    }

    private JobFunction getGroupOfUser(int ID){
        return null;
    }


}
