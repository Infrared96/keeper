package client.Model.Category;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Category {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(String str) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject element = (JSONObject) jsonParser.parse(str);

            this.id = Integer.parseInt(String.valueOf(element.get("id")));
            this.name = String.valueOf(element.get("name"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
