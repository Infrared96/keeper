package client.Model.NewOrder;

import client.Model.Dish.Dish;
import client.Model.Order.Order;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class NewOrder {
    private int id;
    private int dish_id;
    private int order_id;
    private double amount;
    private boolean sell;

    public NewOrder(int id, int dish_id, int order_id, double amount, boolean sell) {
        this.id = id;
        this.dish_id = dish_id;
        this.order_id = order_id;
        this.amount = amount;
        this.sell = sell;
    }

    public NewOrder(Dish dish, Order order, int amount) {
        this.dish_id = dish.getId();
        this.order_id = order.getId();
        this.amount = amount;
        this.sell = false;
    }

    public NewOrder(String str) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject element = (JSONObject) jsonParser.parse(str);

            this.id = Integer.parseInt(String.valueOf(element.get("id")));
            this.dish_id = Integer.parseInt(String.valueOf(element.get("dish_id")));
            this.order_id = Integer.parseInt(String.valueOf(element.get("order_id")));
            this.amount = Double.parseDouble(String.valueOf(element.get("amount")));
            this.sell = Boolean.parseBoolean(String.valueOf(element.get("sell")));

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

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isSell() {
        return sell;
    }

    public void setSell(boolean sell) {
        this.sell = sell;
    }
}
