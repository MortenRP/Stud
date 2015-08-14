package Schedule;

import User.JobFunction;
import User.User;
import jxl.Sheet;

import java.util.HashMap;

/**
 * Created by GameMonkey on 14-08-2015.
 */
public class ScheduleWorker {

    private HashMap<String, Integer> columnsByName;
    private HashMap<JobFunction, PhoneList> phoneListsByJobFunction;
    private Sheet sheet;
    
    public ScheduleWorker(Sheet sheet){
        this.sheet = sheet;

        columnsByName = findColumns();
        phoneListsByJobFunction = generatePhoneLists();
    }

    public PhoneList getPhoneListForFunction(JobFunction jobFunction){
        return null;
    }

    private HashMap<String, Integer> findColumns(){
        return null;
    }

    private HashMap<JobFunction, PhoneList> generatePhoneLists(){
        return null;
    }
}
