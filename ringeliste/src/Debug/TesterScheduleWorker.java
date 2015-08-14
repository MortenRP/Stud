package Debug;

/**
 * Created by GameMonkey on 14-08-2015.
 */

import java.io.File;
import java.io.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import Schedule.ScheduleWorker;

public class TesterScheduleWorker {
    public static void main(String args[]) throws BiffException, IOException{

        File item = new File("src//Debug//heey.xls");
        Workbook workbook = Workbook.getWorkbook(item);
        Sheet sheet = workbook.getSheet(0);

        ScheduleWorker scheduleWorker = new ScheduleWorker(sheet);
    }
}
