package client.Model.Product;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Product {
  private  int id;
  private  String name;
  private  double amount;

    public Product(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    //constructor for JSON string
    public Product(String str) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject element = (JSONObject) jsonParser.parse(str);

            this.id = Integer.parseInt(String.valueOf(element.get("id")));
            this.name = String.valueOf(element.get("name"));
            this.amount = Double.valueOf(String.valueOf(element.get("amount")));
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
