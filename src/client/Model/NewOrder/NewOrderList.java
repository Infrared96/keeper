package client.Model.NewOrder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class NewOrderList {
    private static ArrayList<NewOrder> newOrders;

    public static ArrayList<NewOrder> parseNewOrders(String str) {
        ArrayList<NewOrder> newOrders = null;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(str);
            newOrders = new ArrayList<>();
            for(int i = 0; i < array.size(); i++) {
                JSONObject element = (JSONObject) array.get(i);
                System.out.println(element.toJSONString());
                newOrders.add(new NewOrder(element.toJSONString()));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newOrders;
    }
//    public static ArrayList<Order> getOrders() {
//        if(orders == null) {
//            orders = new ArrayList<>();
//        }
//        return orders;
//    }
//
//    public static ArrayList<Order> parseUserOrders(String str) {
//
//        return null;
//    }
//
//    public static double getSummOrders(ArrayList<Order> ol) {
//        return 0;
//    }
}
