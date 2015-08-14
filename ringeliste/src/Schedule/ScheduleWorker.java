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

    // Creates a HashMap of the columns by name and give their position.
    // Uses the sheet given to the constructor. 
    private HashMap<String, Integer> findColumns(){
        HashMap<String, Integer> columnsByName = new HashMap<>();
        int columnSize = sheet.getColumns();

        for(int i = 0; i < columnSize; ++i){
            columnsByName.put(sheet.getCell(i, 0).getContents(), i);
        }
        return columnsByName;
    }

    private HashMap<JobFunction, PhoneList> generatePhoneLists(){
        return null;
    }
}
