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

        }
    }

    public void updateUserDate(int ID, Date date){

    }

    private JobFunction getJobFunction(int row){
        //int columnAdmin = personnelSheet.getColumnPos("Personalegruppe: Administration");


        //if()
    }

    private int getHoursWorkedOfUser(int ID){
        return 0;
    }

    private JobFunction getGroupOfUser(int ID){
        return null;
    }


}
