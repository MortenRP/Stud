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

    private Personnel(Sheet personnel){
        this.personnelSheet = new SheetWorker(personnel);

        usersByID = new HashMap<>();
    }



    //@ToDo Add error handling if user is in system already.
    private void generateUsers(){
        int columnFirstName =  personnelSheet.getColumnPos("Fornavn");
        int columnLastName =  personnelSheet.getColumnPos("Efternavn");
        int columnID = personnelSheet.getColumnPos("Løn nr");

        int rows = personnelSheet.getRows();

        for(int i = 1; i < rows; ++i){
            String name = personnelSheet.getCellContent(i, columnFirstName)
                    + " "
                    + personnelSheet.getCellContent(i, columnLastName);

            int userID = Integer.parseInt(personnelSheet.getCellContent(i, columnID));

            JobFunction jobFunction = getJobFunction(i);
            User user = new User(name, userID, jobFunction);
            usersByID.put(userID, user);
        }
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
    }

    private int getHoursWorkedOfUser(int ID){
        return 0;
    }

    private JobFunction getGroupOfUser(int ID){
        return null;
    }


}
