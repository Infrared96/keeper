package client.Model.Order;

import client.facade.Facade;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Order {
    private int id;
    private int user_id;
    private int table_num;
    private int count;
    private double price;
    private boolean close;
    private boolean print;
    private boolean cash;
    private String date_open;
    private String date_close;

    public Order(int id, int user_id, int table_num, int count, double price) {
        this.id = id;
        this.user_id = user_id;
        this.table_num = table_num;
        this.count = count;
        this.price = price;
    }
    //constructorfor JSON string
    public Order(String str) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject element = (JSONObject) jsonParser.parse(str);

            this.id = Integer.parseInt(String.valueOf(element.get("id")));
            this.user_id = Integer.parseInt(String.valueOf(element.get("user_id")));
            this.table_num = Integer.parseInt(String.valueOf(element.get("table_num")));
            this.count = Integer.parseInt(String.valueOf(element.get("count")));
            this.price = Double.parseDouble(String.valueOf(element.get("price")));
            this.close = Integer.parseInt(String.valueOf(element.get("close"))) == 1 ? true : false;
            this.print = Integer.parseInt(String.valueOf(element.get("print"))) == 1 ? true : false;
            this.cash = Integer.parseInt(String.valueOf(element.get("cash"))) == 1 ? true : false;
            this.date_open = String.valueOf(element.get("date_open"));
            this.date_close = String.valueOf(element.get("date_close"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //func for close order and send in DB
    public void closeOrder(Facade facade, boolean cash) {
        this.cash = cash;
        this.close = true;
        facade.getMessageManager().setCloseOrder("{\"order_id\":" + this.id + ",\"cash\":" + this.cash + ",\"close\":" + this.close + "}");
    }

    public double getPrice(int id) {
        return price;
    }

    //func for set price and send in DB
    public void setPrice(Facade facade, double price) {
        this.price = price;
        facade.getMessageManager().updateOrderPrice("{\"order_id\":" + this.id + ",\"price\":" + price + "}");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTable_num() {
        return table_num;
    }

    public void setTable_num(int table_num) {
        this.table_num = table_num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public void setPrint(Facade facade, boolean print) {
        this.print = print;
        facade.getMessageManager().updateOrderPrint("{\"order_id\":" + this.id + ",\"print\":" + (print ? 1 : 0) + "}");
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public String getDate_open() {
        return date_open;
    }

    public void setDate_open(String date_open) {
        this.date_open = date_open;
    }

    public String getDate_close() {
        return date_close;
    }

    public void setDate_close(String date_close) {
        this.date_close = date_close;
    }

    //    private int id;
//    private String name;
//    private ArrayList<NewOrder> order;
//    private double amount;
//
//    public Order(int id, String name, ArrayList<NewOrder> order, double amount) {
//        this.id = id;
//        this.name = name;
//        this.order = order;
//        this.amount = amount;
//    }
//
//    public Order(String str) {
//        try {
//            JSONParser jsonParser = new JSONParser();
//            JSONObject element = (JSONObject) jsonParser.parse(str);
//
//            this.id = Integer.parseInt(String.valueOf(element.get("id")));
//            this.name = String.valueOf(element.get("name"));
//           // this.password = String.valueOf(element.get("password"));
//           // this.type = String.valueOf(element.get("type"));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public ArrayList<NewOrder> getDishes() {
//        return order;
//    }
//
//    public void setDishes(ArrayList<NewOrder> order) {
//        this.order = order;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
}
