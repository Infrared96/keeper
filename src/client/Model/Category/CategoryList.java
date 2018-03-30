package client.Model.Category;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class CategoryList {

    public static ArrayList<Category> parseCategorys(String str) {
        ArrayList<Category> orders = null;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(str);
            orders = new ArrayList<>();
            for(int i = 0; i < array.size(); i++) {
                JSONObject element = (JSONObject) array.get(i);
                System.out.println(element.toJSONString());
                orders.add(new Category(element.toJSONString()));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
