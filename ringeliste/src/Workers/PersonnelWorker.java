package Workers;

import User.User;
import User.JobFunction;
import User.UserComparator;
import jxl.Sheet;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by GameMonkey on 26-08-2015.
 */
public class PersonnelWorker {

    private HashMap<Integer, User> usersByID;
    private SheetWorker volunteers;
    private SheetWorker shifts;
    private static final Logger logger = Logger.getRootLogger();
    private ColumnNameWorker columnNameWorker = new ColumnNameWorker();

    public PersonnelWorker(Sheet volunteers, Sheet shifts){
        this.volunteers = new SheetWorker(volunteers);
        this.shifts = new SheetWorker(shifts);

        usersByID = new HashMap<>();
        generateUsers();
        extractHoursWorked();
    }

    public List<User> getUsers(){
        List<User> userList = new ArrayList<>(usersByID.values());
        Collections.sort(userList, new UserComparator());
        return userList;
    }


    //@ToDo Add error handling if user is in system already.
    private void generateUsers(){
        int columnFirstName =  volunteers.getColumnPos(columnNameWorker.firstName);
        int columnLastName =  volunteers.getColumnPos(columnNameWorker.lastName);
        int columnID = volunteers.getColumnPos(columnNameWorker.id);
        int columnNumber = volunteers.getColumnPos(columnNameWorker.number);

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
        int columnAdmin = volunteers.getColumnPos(columnNameWorker.adm);
        int columnBar = volunteers.getColumnPos(columnNameWorker.bar);
        int columnMusic = volunteers.getColumnPos(columnNameWorker.music);
        int columnLight = volunteers.getColumnPos(columnNameWorker.light);

        if(!volunteers.getCellContent(columnAdmin, row).isEmpty()){
            return JobFunction.Other;
        }
        else if(isChief(row)){
            return JobFunction.Chief;
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

    private boolean isChief(int row){
        boolean isChief = false;

        int columnBarchief = volunteers.getColumnPos(columnNameWorker.barchief);
        int columnMusicchief  = volunteers.getColumnPos(columnNameWorker.musicchief);
        int columnLightchief = volunteers.getColumnPos(columnNameWorker.lightchief);

        if(!volunteers.getCellContent(columnBarchief, row).isEmpty()){
            isChief = true;
        }
        else if(!volunteers.getCellContent(columnLightchief, row).isEmpty()){
            isChief = true;
        }
        else if(!volunteers.getCellContent(columnMusicchief, row).isEmpty()){
            isChief = true;
        }

        return isChief;
    }

    private void extractHoursWorked(){
        int columnID = shifts.getColumnPos(columnNameWorker.id);
        int columnDate = shifts.getColumnPos(columnNameWorker.date);

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
                        logger.error("Error parsing date", e);
                        System.exit(1);
                    }
                    user.addShift(date);
                }

            }
        }
    }

}
