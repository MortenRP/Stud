package Debug;

import User.Personnel;
import User.User;
import User.JobFunction;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by GameMonkey on 01-09-2015.
 */
public class PersonnelTester {
    public static void main(String args[]) throws WriteException, BiffException, IOException {
        File usersFile = new File("src//Debug//userinfo.xls");
        Workbook usersWorkbook = Workbook.getWorkbook(usersFile);
        Sheet usersSheet = usersWorkbook.getSheet(0);

        File shiftsFile = new File("src//Debug//shifts_spring_15.xls");
        Workbook shiftsWorkbook = Workbook.getWorkbook(shiftsFile);
        Sheet shiftsSheet = shiftsWorkbook.getSheet(0);

        Personnel personnel = new Personnel(usersSheet, shiftsSheet);

        HashMap<Integer, User> users = personnel.getUsers();
/*
        for(User user : users.values()){
            System.out.println("Name: "
                    + user.getName()
                    + " Id: "
                    + user.getID()
                    + " Job: "
                    + user.getJobFunction().toString()
                    + " Worked: "
                    + user.getHoursWorked());
        }
        */

        String name = "calling_list.xls";
        WritableWorkbook callingList = Workbook.createWorkbook(new File(name));
        callingList.createSheet("Bar", 0);
        callingList.createSheet("Music-light", 1);

        for(WritableSheet sheet : callingList.getSheets()){
            Label label1 = new Label(0, 0, "Name");
            Label label2 = new Label(1, 0, "Hours worked");
            Label label3 = new Label(2, 0, "Number");
            Label label4 = new Label(3, 0, "Function");

            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
        }

        int rowBar = 1;
        int rowMusic = 1;

        for(User user : users.values()){

            int row;
            WritableSheet sheet;

            JobFunction jobFunction = user.getJobFunction();
            if(jobFunction == JobFunction.Bartender){
                row = rowBar;
                ++rowBar;
                sheet = callingList.getSheet(0);
            }
            else if(jobFunction == JobFunction.Light || jobFunction == JobFunction.Music){
                row = rowMusic;
                ++rowMusic;
                sheet = callingList.getSheet(1);
            }
            else{
                continue;
            }


            Label navn = new Label(0, row, user.getName());
            Label hours = new Label(1, row, Float.toString(user.getHoursWorked()));
            Label number = new Label(2, row, user.getPhone());
            Label function = new Label(3, row, user.getJobFunction().toString());

            sheet.addCell(navn);
            sheet.addCell(hours);
            sheet.addCell(number);
            sheet.addCell(function);
        }

        callingList.write();
        callingList.close();
    }

}
