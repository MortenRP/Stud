package Schedule;

import java.util.Date;
import java.util.LinkedList;
import java.util.HashMap;

import User.User;

/**
 * Created by GameMonkey on 05-08-2015.
 */
public class PhoneList {

    private LinkedList<User> beforeTodayList;
    private LinkedList<User> afterTodayList;
    private HashMap<Integer, User> userByID;
    private Date todayDate;

    public PhoneList() {
        beforeTodayList = new LinkedList<User>();
        afterTodayList = new LinkedList<User>();
        userByID = new HashMap<Integer, User>();
        todayDate = new Date();
    }

    //Adds a user to the dictionary or updates if the person is already included
    public void addUser(User user){
        if(userByID.get(user.getID()) != null){
            Date savedDate = userByID.get(user.getID()).date;
            if(user.date.after(savedDate)){
                userByID.put(user.getID(), user);
            }
        }
        else{
            userByID.put(user.getID(), user);
        }
    }

    //Creates the two lists of users from the users in the dictionary
    public void createLists(){
        for(int id : userByID.keySet()){
            User user = userByID.get(id);
            LinkedList<User> list;

            if(user.date.before(todayDate)){
                list = beforeTodayList;
            }
            else {
                list = afterTodayList;
            }

            boolean userAdded = false;
            int i = 0;

            //@ToDo See if there is a way to call "before" or "after" depending on list type.
            if(user.date.before(todayDate)){
                while (userAdded == false && i < list.size()) {
                    if (user.date.before(list.get(i).date)) {
                        list.add(i, user);
                        userAdded = true;
                    }
                    ++i;
                }
            }
            else {
                while (userAdded == false && i < list.size()) {
                    if (user.date.after(list.get(i).date)) {
                        list.add(i, user);
                        userAdded = true;
                    }
                    ++i;
                }
            }
            if (!userAdded) {
                list.add(user);
            }

        }
    }

}
