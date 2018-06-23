package client.Model.Dish;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Dish {
    private int id;
    private String name;
    private double price;
    private int category;
    //private ArrayList<Product> productList;
    private String productList;
    private String amount_products;
    private double amount;

    public Dish(int id, String name, double price, int category, String productList, String amount_products, double amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.productList = productList;
        this.amount_products = amount_products;
        this.amount = amount;
    }

    // create Dish fron JSON string
    public Dish(String str) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject element = (JSONObject) jsonParser.parse(str);

            this.id = Integer.parseInt(String.valueOf(element.get("id")));
            this.name = String.valueOf(element.get("name"));
            this.price = Double.valueOf(String.valueOf(element.get("price")));
            this.category = Integer.valueOf(String.valueOf(element.get("category_id")));
           // this.productList = ProductList.getProductsId(facade.getMessageManager().getDishesId(String.valueOf(element.get("products_id"))));
            this.productList = String.valueOf(element.get("products_id"));
            this.amount_products = String.valueOf(element.get("amount_products"));
            this.amount = Double.valueOf(String.valueOf(element.get("amouns")));

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getAmount_products() {
        return amount_products;
    }

    public void setAmount_products(String amount_products) {
        this.amount_products = amount_products;
    }
}
