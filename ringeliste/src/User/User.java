package User;

import java.util.Date;


/**
 * Created by GameMonkey on 03-08-2015.
 */
// @ToDo Create unique ID for all volunteers. Can be found in the Excel (*.xls) file but should in the end be kept in a
// data base.
public class User {

    private JobFunction jobFunction = null;
    private String name = null;
    private int userID;
    private String phone;
    private float hoursWorked = 0;
    //Public since everyone cen get and set it.
    public Date date = null;

    public User (Date date, String name, int userID, JobFunction jobFunction){
        this.date = date;
        this.name = name;
        this.userID = userID;
        this.jobFunction = jobFunction;
    }

    public User(String name, int userID, JobFunction jobFunction){
        this.name = name;
        this.userID = userID;
        this.jobFunction = jobFunction;
    }

    public User(String name, int userID, JobFunction jobFunction, String phone){
        this.name = name;
        this.userID = userID;
        this.jobFunction = jobFunction;
        this.phone = phone;
    }

    public JobFunction getJobFunction(){
        return jobFunction;
    }

    public String getName(){
        return name;
    }

    public int getID(){
        return userID;
    }

    public float getHoursWorked(){
        return hoursWorked;
    }

    public String getPhone(){
        return phone;
    }

    public void addShift(float hours){
        hoursWorked += hours;
    }

}

