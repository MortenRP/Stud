package Schedule;

import User.JobFunction;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;

import java.util.HashMap;

/**
 * Created by GameMonkey on 14-08-2015.
 */
public class ScheduleWorker {

    private HashMap<String, Integer> columnsByName;
    private HashMap<JobFunction, PhoneList> phoneListsByJobFunction;
    private WritableSheet sheet;
    
    public ScheduleWorker(WritableSheet sheet){
        this.sheet = sheet;

        findColumns();
        mapJobs();
        generatePhoneLists();
    }

    public PhoneList getPhoneListByJobFunction(JobFunction jobFunction){
        return phoneListsByJobFunction.get(jobFunction);
    }

    // Creates a HashMap of the columns by name and give their position.
    // Uses the sheet given to the constructor.
    private void findColumns(){
        columnsByName = new HashMap<String, Integer>();
        int columnSize = sheet.getColumns();

        for(int i = 0; i < columnSize; ++i){
            columnsByName.put(sheet.getCell(i, 0).getContents(), i);
        }

    }

    // Maps the functions from the original sheet to the groups Bartender, Music and Light.
    // Uses regex to match.
    // @ToDo Find out if afryder/Busboy is bar or music.
    private void mapJobs(){
        String bar = ".*(B|b)ar.*";
        String light = ".*(L|l)ight.*";
        int column = columnsByName.get("Jobfunktion");
        int rows = sheet.getRows();

        for(int i = 1; i < rows; ++i){
            WritableCell job = sheet.getWritableCell(column, i);
            Label label = (Label) job;

            if(!job.getContents().isEmpty()){
                if(job.getContents().matches(bar)){
                    label.setString("Bartender");
                }
                else if(job.getContents().matches(light)){
                    label.setString("Light");
                }
                else{
                    label.setString("Music");
                }
            }

        }
    }

    private void generatePhoneLists(){

    }
}
