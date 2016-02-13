package Workers;

import User.User;
import Writers.Log4jUncaughtExceptionHandler;
import Writers.xlsWriter;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by GameMonkey on 13-02-2016.
 */
public class MainWorker {

    private static final Logger logger = Logger.getRootLogger();

    public static void main(String args[]) {
        //Setting runtime exception handler.
        Thread.setDefaultUncaughtExceptionHandler(new Log4jUncaughtExceptionHandler());

        //Setting up logging configuration
        InputStream is;
        try {
            is = new FileInputStream("log4j.conf");
            PropertyConfigurator.configure(is);
            is.close();
        }
        catch (java.io.IOException e){
            System.out.println("Error in setting conf up in main.");
            System.exit(1);
        }

        //Thanks to this stackoverflow for solving the issue of encoding!
        //http://stackoverflow.com/questions/7449285/character-encoding-in-excel-spreadsheet-and-what-java-charset-to-use-to-decode
        WorkbookSettings ws = new WorkbookSettings();
        ws.setEncoding("Cp1252");

        //Getting users
        File usersFile = new File("users.xls");
        Workbook usersWorkbook = null;
        try {
            usersWorkbook = Workbook.getWorkbook(usersFile, ws);
        }
        catch (java.io.IOException | jxl.read.biff.BiffException e){
            logger.error("Error with users.xls", e);
            System.exit(1);
        }
        Sheet usersSheet = usersWorkbook.getSheet(0);

        //Getting shifts
        File shiftsFile = new File("shifts.xls");
        Workbook shiftsWorkbook = null;
        try {
            shiftsWorkbook = Workbook.getWorkbook(shiftsFile, ws);
        }
        catch (java.io.IOException | jxl.read.biff.BiffException e){
            logger.error("Error with shifts.xls", e);
            System.exit(1);
        }
        Sheet shiftsSheet = shiftsWorkbook.getSheet(0);

        //Getting sorted personnel and write to file.
        Workers.PersonnelWorker personnel = new PersonnelWorker(usersSheet, shiftsSheet);
        List<User> userList = personnel.getUsers();

        String name = "calling_list.xls";
        xlsWriter writer = new xlsWriter(name, userList);

        //Deleting used files and also the error.log if it is empty.
        try{
            Files.delete(Paths.get("users.xls"));
            Files.delete(Paths.get("shifts.xls"));
        }
        catch (java.io.IOException e){
            logger.error("Error deleting files", e);
            System.exit(1);
        }
    }

}
