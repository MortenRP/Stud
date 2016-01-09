package Debug;

import User.User;
import Workers.PersonnelWorker;
import Writers.xlsWriter;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by GameMonkey on 05-01-2016.
 */
public class xlsWriterTester {
    public static void main(String args[]) throws WriteException, BiffException, IOException {


        //Thanks to this stackoverflow for solving the issue of encoding!
        //http://stackoverflow.com/questions/7449285/character-encoding-in-excel-spreadsheet-and-what-java-charset-to-use-to-decode
        WorkbookSettings ws = new WorkbookSettings();
        ws.setEncoding("Cp1252");

        File usersFile = new File("users.xls");
        Workbook usersWorkbook = Workbook.getWorkbook(usersFile, ws);
        Sheet usersSheet = usersWorkbook.getSheet(0);

        File shiftsFile = new File("shifts.xls");
        Workbook shiftsWorkbook = Workbook.getWorkbook(shiftsFile, ws);
        Sheet shiftsSheet = shiftsWorkbook.getSheet(0);

        Workers.PersonnelWorker personnel = new PersonnelWorker(usersSheet, shiftsSheet);

        List<User> userList = personnel.getUsers();

        String name = "calling_list.xls";

        xlsWriter writer = new xlsWriter(name, userList);
    }
}
