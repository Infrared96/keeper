package client.Model.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringWriter;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String type;

    User() {
    }

    User(int id, String login, String password, String name, String type) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    public User(String str) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject element = (JSONObject) jsonParser.parse(str);

            this.id = Integer.parseInt(String.valueOf(element.get("id")));
            this.login = String.valueOf(element.get("login"));
            this.password = String.valueOf(element.get("password"));
            this.name = String.valueOf(element.get("name"));
            this.type = String.valueOf(element.get("type"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //JSON for Order
    public String forOrderJSON() {
        String jsonText = null;
        try {
            JSONObject obj = new JSONObject();
            StringWriter out = new StringWriter();

            obj.put("id", this.getId());
            obj.put("type", this.getType());

            obj.writeJSONString(out);
            jsonText = out.toString();
            System.out.println(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
