package client.Model.User;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class UserList {
    private static ArrayList<User> users;

    public static boolean addUser (User user) {
        if(user == null || user.getId() == 0 || user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }

        if(users == null) {
            getUsers();
        }
        users.add(user);
        return true;
    }
    //JSON for LogIn in system
    public static String forLoginJSON(String login, String password) {
        String jsonText = null;
        try {
            JSONObject obj = new JSONObject();
            StringWriter out = new StringWriter();

            obj.put("login", login);
            obj.put("password", password);

            obj.writeJSONString(out);
            jsonText = out.toString();
            System.out.println(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonText;
    }

    public static boolean removeUser(User user) {
        return users.remove(user);
    }

    public static User findUser(User user) {
        return (findUser(user.getId()) != null) ? findUser(user.getId()) : null;
    }

    public static User findUser(int id) {
        if (users != null) {
            for (User user : users) {
                if (user.getId() == id)
                    return user;
            }
        }
        return null;
    }

    public static User findUser(String login) {
        if (users != null) {
            for (User user : users) {
                if (user.getLogin().equalsIgnoreCase(login))
                    return user;
            }
        }
        return null;
    }

    public static ArrayList<User> getUsers(){
        if(users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

}
