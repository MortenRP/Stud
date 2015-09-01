package User;

import java.util.Comparator;

/**
 * Created by GameMonkey on 01-09-2015.
 */
public class userComparator implements Comparator<User> {
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
}
