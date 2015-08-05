/**
 * Created by GameMonkey on 03-08-2015.
 */

import java.io.File;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import jxl.*;
import jxl.read.biff.BiffException;

import User.User;

public class Schedule {
    public static void main(String[] args)throws BiffException, IOException {

        PhoneLists phoneLists = new PhoneLists();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date userDate = null;

        File item = new File("heey.xls");
        Workbook workbook = Workbook.getWorkbook(item);
        Sheet sheet = workbook.getSheet(0);


        int rows = sheet.getRows();
        int num = 1;
        //loop over the all the rows in the exal
        while(num < rows)
        {
            Cell a1 = sheet.getCell(0,num);
            Cell a2 = sheet.getCell(3,num);
            Cell a3 = sheet.getCell(11,num);

            String dateString = a1.getContents();
            String name = a2.getContents();
            String workAeare = a3.getContents();

            try
            {
                userDate = df.parse(dateString);
                //System.out.println("Date: " + d1);
                System.out.println("Date in dd-MM-yyyy format is: "+df.format(userDate));
            }
            catch (Exception ex )
            {
                System.out.println(ex.getMessage());
            }

            User user = new User(userDate, name, workAeare);
            phoneLists.QueueCheck(user);
            num++;
        }

        workbook.close();
        phoneLists.QueueLoop();
        phoneLists.PrintList();
    }
}