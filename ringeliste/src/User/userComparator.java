package User;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by GameMonkey on 01-09-2015.
 */
public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2){
        LinkedList<Date> takenDates1 = o1.getTakenDates();
        LinkedList<Date> takenDates2 = o2.getTakenDates();

        LinkedList<Date> futureDates1 = o1.getFutureDates();
        LinkedList<Date> futureDates2 = o2.getFutureDates();

        // If the users are one equal in terms of shifts.
        if(takenDates1.size() == 0 && futureDates1.size() == 0){
            if(takenDates2.size() == 0 && futureDates2.size() == 0){
                return 0;
            }
            else{
                return -1;
            }
        }
        else if(takenDates1.size() != 0 && futureDates1.size() == 0){
            if(futureDates2.size() != 0){
                return -1;
            }
            else if(takenDates2.size() != 0){
                return takenDates1.getLast().compareTo(takenDates2.getLast());
            }
            else{
                return 1;
            }
        }
        else if(futureDates2.size() == 0) {
            return 1;
        }
        else if(futureDates1.getFirst().before(futureDates2.getFirst())) {
            return 1;
        }
        else if(futureDates1.getFirst().after(futureDates2.getFirst())) {
            return -1;
        }
        else{
            return 0;
        }
    }
}
