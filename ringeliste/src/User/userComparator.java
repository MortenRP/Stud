package User;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by GameMonkey on 01-09-2015.
 */
public class UserComparator implements Comparator<User> {
    /*
    @Override
    public int compare(User o1, User o2) {
        float hours_1 = o1.getHoursWorked();
        float hours_2 = o2.getHoursWorked();

        if(hours_1 > hours_2){
            return 1;
        }
        else if(hours_1 < hours_2){
            return -1;
        }
        else{
            return 0;
        }

    }
    */

    @Override
    public int compare(User o1, User o2){
        LinkedList<Date> dates1 = o1.getDates();
        LinkedList<Date> dates2 = o2.getDates();


        // If the users are one equal in terms of shifts.
        if(dates1.size() == 0 && dates2.size() == 0){
            return 0;
        }
        else if(dates1.size() == 0){
            return -1;
        }
        else if(dates2.size() == 0){
            return 1;
        }
        else {
            return dates1.getLast().compareTo(dates2.getLast());
        }
    }
}
