import java.util.Date;
import java.util.LinkedList;
import java.util.HashMap;

import User.User;

/**
 * Created by GameMonkey on 05-08-2015.
 */
public class PhoneLists {

    private LinkedList<User> beforeTodayList;
    private LinkedList<User> afterTodayList;
    private HashMap<String, User> userByName;
    private Date todayDate;

    public PhoneLists() {
        beforeTodayList = new LinkedList<User>();
        afterTodayList = new LinkedList<User>();
        userByName = new HashMap<String, User>();
        todayDate = new Date();
    }

    //Adds a user to the dictionary or updates if the person is already included
    public void addUser(User user){
        if(userByName.get(user.name) != null){
            Date savedDate = userByName.get(user.name).date;
            if(user.date.after(savedDate)){
                userByName.put(user.name, user);
            }
        }
        else{
            userByName.put(user.name, user);
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

    //Print the two lists.
    //@ToDo rewrite to print to file.
    public void printLists() {
        System.out.println("Før idag");
        for(User user : beforeTodayList) {
            System.out.println(user.name + " " + user.date);
        }
        System.out.println("Efter idag");
        for(User user : afterTodayList) {
            System.out.println(user.name + " " + user.date);
        }

    }
}
