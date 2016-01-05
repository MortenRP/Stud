package User;

import Schedule.SheetWorker;
import jxl.Sheet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by GameMonkey on 26-08-2015.
 */
public class Personnel {

    private HashMap<Integer, User> usersByID;
    private SheetWorker volunteers;
    private SheetWorker shifts;

    public Personnel(Sheet volunteers, Sheet shifts){
        this.volunteers = new SheetWorker(volunteers);
        this.shifts = new SheetWorker(shifts);

        usersByID = new HashMap<>();
        generateUsers();
        extractHoursWorked();
    }

    public HashMap<Integer, User> getUsers(){
        return usersByID;
    }


    //@ToDo Add error handling if user is in system already.
    private void generateUsers(){
        int columnFirstName =  volunteers.getColumnPos("fornavn");
        int columnLastName =  volunteers.getColumnPos("efternavn");
        int columnID = volunteers.getColumnPos("loennummer");
        int columnNumber = volunteers.getColumnPos("mobil");

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

    private JobFunction getJobFunction(int row){
        int columnAdmin = volunteers.getColumnPos("personalegruppe: administration");
        int columnBar = volunteers.getColumnPos("personalegruppe: bartender");
        int columnMusic = volunteers.getColumnPos("personalegruppe: musikfrivillige");
        int columnLight = volunteers.getColumnPos("personalegruppe: lysafvikler/light technician");

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

    private void extractHoursWorked(){
        int columnID = shifts.getColumnPos("loen nr.");
        int columnDate = shifts.getColumnPos("dato");

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;

        int rows = shifts.getRows();

        for(int i = 1; i < rows; ++i){
            String id = shifts.getCellContent(columnID, i);
            if(!id.isEmpty()){
                User user = usersByID.get(Integer.parseInt(id));
                if(user != null) {
                    try {
                         date = format.parse(shifts.getCellContent(columnDate, i));
                    }
                    catch(ParseException e){
                        System.out.println(e);
                    }
                    user.addShift(date);
                }

            }
        }
    }

}
