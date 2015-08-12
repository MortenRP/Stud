package Debug;

import java.io.File;
import java.util.LinkedList;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 * Created by GameMonkey on 12-08-2015.
 */
public class PrintJobFunctions {
    public static void main(String[] args) throws BiffException, java.io.IOException {
        File schedule = new File("src//debug//one_complete_year.xls");
        Workbook workbook = Workbook.getWorkbook(schedule);
        Sheet sheet = workbook.getSheet(0);

        int rows = sheet.getRows();
        int num = 1;

        LinkedList<String> list = new LinkedList<String>();

        while(num < rows){
            String function = sheet.getCell(11, num).getContents();
            if(!list.contains(function)){
                list.add(function);
            }
            ++num;
        }

        for(String e : list){
            System.out.println(e);
        }
    }
}
