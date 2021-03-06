package Writers;

import User.User;
import User.JobFunction;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by GameMonkey on 05-01-2016.
 */
public class xlsWriter {

    public final String fileName;
    public final List<User> users;

    private WritableWorkbook callingList;
    private static final Logger logger = Logger.getRootLogger();

    public xlsWriter(String fileName, List<User> users){

        this.fileName = fileName;
        this.users = users;

        try{
            initializeWorkbook();
            writeUsers();

            callingList.write();
            callingList.close();
        }
        catch (java.io.IOException e){
            logger.error("Error in xlsWriter by IOException.", e);
        }
        catch (jxl.write.WriteException e)
        {
            logger.error("Error in xlsWriter by WriteException", e);
        }

    }

    private void initializeWorkbook() throws java.io.IOException, jxl.write.WriteException{
        callingList = Workbook.createWorkbook(new File(fileName));

        callingList.createSheet("Bar", 0);
        callingList.createSheet("Music-light", 1);

        Date today = new Date();
        System.out.println(today);

        for(WritableSheet sheet : callingList.getSheets()){
            Label label1 = new Label(0, 0, "Name");
            Label label2 = new Label(1, 0, "Number");
            Label label3 = new Label(2, 0, "Function");
            Label label4 = new Label(3, 0, "Last Shift");
            Label label5 = new Label(4, 0, "Next shift");

            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);

            sheet.setColumnView(0, 30);
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 35);
            sheet.setColumnView(4, 35);
        }
    }

    private void writeUsers() throws jxl.write.WriteException{
        int rowBar = 1;
        int rowMusic = 1;

        for(User user : users){

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


            Label name = new Label(0, row, user.getName());
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

            sheet.addCell(name);
            sheet.addCell(number);
            sheet.addCell(function);

        }
    }
}
