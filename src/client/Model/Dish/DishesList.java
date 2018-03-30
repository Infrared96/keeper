package client.Model.Dish;

import client.facade.Facade;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class DishesList {
    private static ArrayList<Dish> dishes;

    public static ArrayList<Dish> parseDishes(String str) {
        ArrayList<Dish> dishes = null;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(str);
            dishes = new ArrayList<>();
            for(int i = 0; i < array.size(); i++) {
                JSONObject element = (JSONObject) array.get(i);
                System.out.println(element.toJSONString());
                dishes.add(new Dish(element.toJSONString()));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dishes;
    }

    public static Dish parseDishe(String str) {
        Dish dishe = null;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(str);
            System.out.println(obj.toJSONString());
            dishe = new Dish(obj.toJSONString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dishe;
    }

//    public static ArrayList<Dish> getdishes() {
//        if(dishes == null) {
//            dishes = new ArrayList<>();
//        }
//        return dishes;
//    }
//
//    public static boolean addDish (Dish Dish) {
//        if(Dish == null || Dish.getId() == 0 || Dish.getName().isEmpty()) {
//            return false;
//        }
//
//        if(dishes == null) {
//            getdishes();
//        }
//        dishes.add(Dish);
//        return true;
//    }
//
//    public static boolean removeDish(Dish Dish) {
//        return dishes.remove(Dish);
//    }
//
//    public static Dish findDish(Dish dish) {
//        return (findDish(dish.getId()) != null) ? findDish(dish.getId()) : null;
//    }
//
//    public static Dish findDish(int id) {
//        if (dishes != null) {
//            for (Dish dish : dishes) {
//                if (dish.getId() == id)
//                    return dish;
//            }
//        }
//        return null;
//    }
//
//    public static Dish findDish(String name) {
//        if (dishes != null) {
//            for (Dish dish : dishes) {
//                if (dish.getName().equalsIgnoreCase(name))
//                    return dish;
//            }
//        }
//        return null;
//    }
}
