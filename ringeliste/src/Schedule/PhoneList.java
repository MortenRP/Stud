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
    private HashMap<String, User> userByName;
    private Date todayDate;

    public PhoneList() {
        beforeTodayList = new LinkedList<User>();
        afterTodayList = new LinkedList<User>();
        userByName = new HashMap<String, User>();
        todayDate = new Date();
    }

    //Adds a user to the dictionary or updates if the person is already included
    public void addUser(User user){
        if(userByName.get(user.getName()) != null){
            Date savedDate = userByName.get(user.getName()).date;
            if(user.date.after(savedDate)){
                userByName.put(user.getName(), user);
            }
        }
        else{
            userByName.put(user.getName(), user);
        }
    }

    //Creates the two lists of users from the users in the dictionary
    public void createLists(){
        for(String name : userByName.keySet()){
            User user = userByName.get(name);
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
