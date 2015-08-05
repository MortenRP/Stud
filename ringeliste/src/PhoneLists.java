import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import User.User;

/**
 * Created by GameMonkey on 05-08-2015.
 */
public class PhoneLists {

    private LinkedList<User> BeforeTodayList;
    private LinkedList<User> AfterTodayList;
    private Queue<User> queue;
    private Date TodayDate;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public PhoneLists() {
        BeforeTodayList = new LinkedList<User>();
        AfterTodayList = new LinkedList<User>();
        queue = new LinkedList<User>();
        TodayDate = new Date();
        System.out.println(TodayDate);
    }

    //Add user to queue
    public void addQueue(User user) {
        queue.add(user);
    }
    //Add user to linklist
    public void addList(User user) {
        loop:
        if (user.Date.after(TodayDate)) {
            if (AfterTodayList.size() < 1) {
                AfterTodayList.add(user);
            }
            else {
                for (int i = 0; i < AfterTodayList.size(); i++) {
                    if (user.Date.after(AfterTodayList.get(i).Date)) {
                        AfterTodayList.add(i, user);
                        break loop;
                    }
                }
                AfterTodayList.add(user);
            }
        }
        else{
            if (BeforeTodayList.size() < 1) {
                BeforeTodayList.add(user);
            }
            else {
                for (int i = 0; i < BeforeTodayList.size(); i++) {
                    if (user.Date.before(BeforeTodayList.get(i).Date)) {
                        BeforeTodayList.add(i, user);
                        break loop;
                    }
                }
                BeforeTodayList.addLast(user);
            }
        }
    }
    // Check if user are in queue and if date are the newest
    public void QueueCheck(User user) {

        boolean found = false;

        for (User kk : queue) {
            //Check if user is in queue
            if (kk.Name == user.Name) {
                found = true;
                //Check is the date is the newest
                if (user.Date.after(kk.Date)) {
                    kk.Date = user.Date;
                }
            }
        }

        if(found != true || queue.peek() == null) {
            addQueue(user);
        }
    }
    //Take user fra queue to linklist
    public void QueueLoop () {
        while (queue.peek() != null) {
            User hh = queue.poll();
            addList(hh);
            //System.out.println(hh.Name + " " + hh.Date);
        }
        System.out.println("Don");
    }
    //Print linklist to docment
    public void PrintList() {
        System.out.println("Før idag");
        for (int i = 0; i < BeforeTodayList.size(); i++) {
            System.out.println(BeforeTodayList.get(i).Name + " " + BeforeTodayList.get(i).Date);
        }
        System.out.println("Efter idag");
        for (int i = 0; i < AfterTodayList.size(); i++) {
            System.out.println(AfterTodayList.get(i).Name + " " + AfterTodayList.get(i).Date);
        }
    }
}
