/**
 * Created by GameMonkey on 03-08-2015.
 */

import java.io.File;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import jxl.*;
import jxl.read.biff.BiffException;

import User.User;

public class Schedule {
    public static void main(String[] args)throws BiffException, IOException {

        PhoneList phoneList = new PhoneList();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date shiftDate = null;

        //@ToDo Rewrite to be generic when a GUI is set up.
        File item = new File("heey.xls");
        Workbook workbook = Workbook.getWorkbook(item);
        Sheet sheet = workbook.getSheet(0);


        int rows = sheet.getRows();
        int num = 1;
        //loop over the all the rows in the excel
        while(num < rows)
        {
            String dateString = sheet.getCell(0,num).getContents();
            String name = sheet.getCell(3,num).getContents();
            String workArea = sheet.getCell(11,num).getContents();

            try
            {
                shiftDate = df.parse(dateString);
            }
            catch (ParseException ex )
            {
                System.out.println(ex.getMessage());
            }

            User user = new User(shiftDate  , name, workArea);
            phoneList.addUser(user);
            num++;
        }

        workbook.close();
        phoneList.createLists();
        phoneList.printLists();
    }
}