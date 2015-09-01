package Debug;

import User.Personnel;
import User.User;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by GameMonkey on 01-09-2015.
 */
public class PersonnelTester {
    public static void main(String args[]) throws WriteException, BiffException, IOException {
        File item = new File("src//Debug//userinfo.xls");
        Workbook workbook = Workbook.getWorkbook(item);
        Sheet sheet = workbook.getSheet(0);

        Personnel personnel = new Personnel(sheet);

        HashMap<Integer, User> users = personnel.getUsers();

        for(User user : users.values()){
            System.out.println("Name: "
                    + user.getName()
                    + " Id: "
                    + user.getID()
                    + " Job: "
                    + user.getJobFunction().toString());
        }
    }

}
