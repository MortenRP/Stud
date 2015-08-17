package Debug;

/**
 * Created by GameMonkey on 14-08-2015.
 */

import java.io.File;
import java.io.*;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.read.biff.BiffException;
import Schedule.ScheduleWorker;

public class TesterScheduleWorker {
    public static void main(String args[]) throws BiffException, IOException{

        File item = new File("src//Debug//heey.xls");
        Workbook workbook = Workbook.getWorkbook(item);
        WritableWorkbook copy = Workbook.createWorkbook(new File("updated_schedule.xls"), workbook);
        WritableSheet sheet = copy.getSheet(0);

        ScheduleWorker scheduleWorker = new ScheduleWorker(sheet);
    }
}
