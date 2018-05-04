package client.facade.RequestManager;

import client.facade.Facade;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class RequestManager {
    private Facade facadeAPI = null;
    private String serverUrl = null;
    private String[] postRequests = null;
    private String[] getRequests = null;

    public RequestManager(Facade facade) {
        this.facadeAPI = facade;

        this.serverUrl = "http://localhost/keeper/www";
        this.postRequests = new String[]{"CREATE_USER","CREATE_NEW_ORDERS", "CREATE_DISH", "CREATE_PRODUCT", "CREATE_ORDER", "UPDATE_USER", "UPDATE_DISH", "UPDATE_PRODUCT", "UPDATE_ORDER", "UPDATE_NEW_ORDERS", "UPDATE_ORDER_PRICE"};
        this.getRequests = new String[]{"GET_USERS", "GET_USER", "GET_USER_LOGIN",
                "GET_PRODUCT", "GET_PRODUCTS", "GET_PRODUCTS_ID",
                "GET_DISH", "GET_DISHES", "GET_DISH_ID",
                "GET_ORDER", "GET_ORDERS", "GET_ORDERS_USER", "GET_ORDER_ID",
                "GET_CATEGORYS",
                "GET_NEW_ORDERS_ID"};
    }

    public String sendRequest(String type, String data) {
        // sendRequest("LOGIN_USER", json);
        try {
            String method = Arrays.asList(this.getRequests).contains(type) ? "GET" : Arrays.asList(this.postRequests).contains(type) ? "POST" : null;
            if (method == null) {
                throw new Exception("Unknown command recieved: " + type + ".");
            }

            String rescontent = method == "GET" ? sendGetRequest(type, data) : sendPostRequest(type, data);
            return rescontent;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String sendGetRequest(String type, String data) {
        String ret = null;
        try{
            String docUrl = this.serverUrl.concat("/" + type.toLowerCase() + ".php?data=" + data);
            URL obj = new URL(docUrl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            System.out.println("\nSending 'GET' request to URL : " + docUrl);
            System.out.println("GET parameters : " + data);

            ret = getRequestResult(con);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

//    private Object parse(String line, String type)
//    {
//        try
//        {
//            JSONParser jsonParser = new JSONParser();
//            JSONArray pp = (JSONArray) jsonParser.parse(line);
//
//            for (int i = 0; i < pp.size(); i++)
//            {
//                //JSONObject element = (JSONObject) pp.get(i);
//
//                String id = String.valueOf(element.get("id"));
//                String fname = String.valueOf(element.get("fname"));
//                String lname = String.valueOf(element.get("lname"));
//                String age = String.valueOf(element.get("age"));
//
//                System.out.println(id + "," + fname + "," + lname + "," + age);
//            }
//            System.out.println();
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    private String sendPostRequest(String type, String data) {
        String ret = null;
        try {
            String docUrl = this.serverUrl.concat("/" + type.toLowerCase() + ".php");
            URL obj = new URL(docUrl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            String urlParams = "data=" + data;
            con.setDoOutput(true);
            // Sending params
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParams);
            wr.flush();
            wr.close();
            System.out.println("\nSending 'POST' request to URL : " + docUrl);
            System.out.println("Post parameters : " + urlParams);

            ret = getRequestResult(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private String getRequestResult(HttpURLConnection connection) {
        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String response;
            String ret = "";
            while ((response = reader.readLine()) != null) {
                ret+=response;
            }
            System.out.println(ret);
            reader.close();

            return ret.equals("") ? null : ret;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
