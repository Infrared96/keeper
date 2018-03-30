package client.Model.Product;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class ProductList {
    private static ArrayList<Product> products;

    public static ArrayList<Product> getProducts() {
        if(products == null) {
            products = new ArrayList<>();
        }
        return products;
    }

    public static ArrayList<Product> getProductsId(String str) {
        ArrayList<Product> products = null;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(str);
            products = new ArrayList<>();
            for(int i = 0; i < array.size(); i++) {
                JSONObject element = (JSONObject) array.get(i);
                System.out.println(element.toJSONString());
//                int id = Integer.parseInt(String.valueOf(element.get("id")));
//                String name = String.valueOf(element.get("name"));
//                double amount = Double.valueOf(String.valueOf(element.get("amount")));
//                products.add(new Product(id, name, amount));
                products.add(new Product(element.toJSONString()));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static boolean addProduct (Product product) {
        if(product == null || product.getId() == 0 || product.getName().isEmpty()) {
            return false;
        }

        if(products == null) {
            getProducts();
        }
        products.add(product);
        return true;
    }

    public static boolean removeProduct(Product product) {
        return products.remove(product);
    }

    public static Product findProduct(Product product) {
        return (findProduct(product.getId()) != null) ? findProduct(product.getId()) : null;
    }

    public static Product findProduct(int id) {
        if (products != null) {
            for (Product product : products) {
                if (product.getId() == id)
                    return product;
            }
        }
        return null;
    }

    public static Product findProduct(String name) {
        if (products != null) {
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(name))
                    return product;
            }
        }
        return null;
    }

}
