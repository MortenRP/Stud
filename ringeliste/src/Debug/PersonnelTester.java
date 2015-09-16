package Debug;

import User.Personnel;
import User.User;
import User.JobFunction;
import User.UserComparator;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by GameMonkey on 01-09-2015.
 */
public class PersonnelTester {
    public static void main(String args[]) throws WriteException, BiffException, IOException {
        File usersFile = new File("src//Debug//userinfo.xls");
        Workbook usersWorkbook = Workbook.getWorkbook(usersFile);
        Sheet usersSheet = usersWorkbook.getSheet(0);

        File shiftsFile = new File("src//Debug//test.xls");
        Workbook shiftsWorkbook = Workbook.getWorkbook(shiftsFile);
        Sheet shiftsSheet = shiftsWorkbook.getSheet(0);

        Personnel personnel = new Personnel(usersSheet, shiftsSheet);

        HashMap<Integer, User> users = personnel.getUsers();

        String name = "calling_list.xls";
        WritableWorkbook callingList = Workbook.createWorkbook(new File(name));
        callingList.createSheet("Bar", 0);
        callingList.createSheet("Music-light", 1);

        Date today = new Date();
        System.out.println(today);

        for(WritableSheet sheet : callingList.getSheets()){
            Label label1 = new Label(0, 0, "Name");
            Label label2 = new Label(1, 0, "Hours worked");
            Label label3 = new Label(2, 0, "Number");
            Label label4 = new Label(3, 0, "Function");
            Label label5 = new Label(4, 0, "Last Shift");
            Label label6 = new Label(5, 0, "Next shift");

            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
        }

        int rowBar = 1;
        int rowMusic = 1;

        List<User> userList = new ArrayList<>(users.values());
        Collections.sort(userList, new UserComparator());
        for(User user : userList){

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
            Label number = new Label(1, row, user.getPhone());
            Label function = new Label(2, row, user.getJobFunction().toString());

            Date lastShift = null;
            if(user.getTakenDates().size() != 0){
                lastShift = user.getTakenDates().getLast();
            }

            Date nextShift = null;
            if(user.getFutureDates().size() != 0){
                nextShift = user.getFutureDates().getFirst();
            }

            if(lastShift != null){
                Label date = new Label(3, row, lastShift.toString());
                sheet.addCell(date);
            }

            if(nextShift != null){
                Label date = new Label(4, row, nextShift.toString());
                sheet.addCell(date);
            }

            sheet.addCell(navn);
            sheet.addCell(number);
            sheet.addCell(function);

        }

        callingList.write();
        callingList.close();
    }

}
