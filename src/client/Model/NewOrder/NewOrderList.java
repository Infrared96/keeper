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

    public static String parseString(ArrayList<NewOrder> orders) {
        JSONArray list = new JSONArray();
        for(int i = 0 ; i < orders.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("dish_id", orders.get(i).getDish_id());
            obj.put("order_id", orders.get(i).getOrder_id());
            obj.put("amount", orders.get(i).getAmount());
            list.add(obj);
        }
        return list.toJSONString();
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
