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
    private SheetWorker personnelSheet;

    public Personnel(Sheet personnel){
        this.personnelSheet = new SheetWorker(personnel);

        usersByID = new HashMap<>();
        generateUsers();
    }



    //@ToDo Add error handling if user is in system already.
    private void generateUsers(){
        int columnFirstName =  personnelSheet.getColumnPos("Fornavn");
        int columnLastName =  personnelSheet.getColumnPos("Efternavn");
        int columnID = personnelSheet.getColumnPos("Loennummer");

        int rows = personnelSheet.getRows();

        for(int i = 1; i < rows; ++i){
            String name = personnelSheet.getCellContent(columnFirstName, i)
                    + " "
                    + personnelSheet.getCellContent(columnLastName, i);

            int userID = Integer.parseInt(personnelSheet.getCellContent(columnID, i));

            JobFunction jobFunction = getJobFunction(i);
            User user = new User(name, userID, jobFunction);
            usersByID.put(userID, user);
        }
    }

    public HashMap<Integer, User> getUsers(){
        return usersByID;
    }

    public void updateUserDate(int ID, Date date){

    }

    private JobFunction getJobFunction(int row){
        int columnAdmin = personnelSheet.getColumnPos("Personalegruppe: Administration");
        int columnBar = personnelSheet.getColumnPos("Personalegruppe: Bartender");
        int columnMusic = personnelSheet.getColumnPos("Personalegruppe: Musikfrivillige");
        int columnLight = personnelSheet.getColumnPos("Personalegruppe: Lysafvikler/Light Technician");

        if(!personnelSheet.getCellContent(row, columnAdmin).isEmpty()){
            return JobFunction.Other;
        }
        else if(!personnelSheet.getCellContent(row, columnBar).isEmpty()){
            return JobFunction.Bartender;
        }
        else if(!personnelSheet.getCellContent(row, columnMusic).isEmpty()){
            return JobFunction.Music;
        }
        else if(!personnelSheet.getCellContent(row, columnLight).isEmpty()){
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
