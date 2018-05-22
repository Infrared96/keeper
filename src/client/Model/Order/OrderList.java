package client.Model.Order;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderList {
    private static ArrayList<Order> orders;

    public static ArrayList<Order> getOrders() {
        if(orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }


    public static String forCreateOrder(int table, int count, int id) {
        String jsonText = null;
        try {
            JSONObject obj = new JSONObject();
            StringWriter out = new StringWriter();

            obj.put("user_id", id);
            obj.put("table_num", table);
            obj.put("count", count);

            obj.writeJSONString(out);
            jsonText = out.toString();
            System.out.println(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonText;
    }

    public static ArrayList<Order> parseUserOrders(String str) {
        ArrayList<Order> orders = null;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(str);
            orders = new ArrayList<>();
            for(int i = 0; i < array.size(); i++) {
                JSONObject element = (JSONObject) array.get(i);
                System.out.println(element.toJSONString());
                orders.add(new Order(element.toJSONString()));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static Order parseOneOrder(String str) {
        Order order = null;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(str);
            System.out.println(obj.toJSONString());
            order = new Order(obj.toJSONString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return order;
    }


//    public static boolean addOrder (Order order) {
//        if(order == null || order.getId() == 0 || order.getName().isEmpty()) {
//            return false;
//        }
//
//        if(orders == null) {
//            getdishes();
//        }
//        orders.add(order);
//        return true;
//    }
//
//    public static boolean removeOrder(Order order) {
//        return orders.remove(order);
//    }
//
//    public static Order findOrder(Order order) {
//        return (findDish(order.getId()) != null) ? findDish(order.getId()) : null;
//    }
//
//    public static Order findDish(int id) {
//        if (orders != null) {
//            for (Order order : orders) {
//                if (order.getId() == id)
//                    return order;
//            }
//        }
//        return null;
//    }
//
//    public static Order findOrder(String name) {
//        if (orders != null) {
//            for (Order order : orders) {
//                if (order.getName().equalsIgnoreCase(name))
//                    return order;
//            }
//        }
//        return null;
//    }
}
