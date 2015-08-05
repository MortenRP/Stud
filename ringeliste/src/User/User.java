package User;

import java.util.Date;



/**
 * Created by GameMonkey on 03-08-2015.
 */
// @ToDo Create unique ID for all volunteers. Can be found in the Excel (*.xls) file but should in the end be kept in a
// data base.
public class User {


    public Date date = null;
    public String name = null;
    public String jobFunction = null;

    public User (Date date, String name, String jobFunction){
        this.date = date;
        this.name = name;
        this.jobFunction = jobFunction;
    }

}

