package client.Client;

import client.Model.Order.Order;
import client.Model.Order.OrderList;
import client.Model.User.User;
import client.facade.Facade;

import java.io.*;
import java.util.ArrayList;


public class Client
{
    private Facade facade;
    private User user = null;
    private ArrayList<Order> orders = null;
    private double totalSum = 0.0;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public User getUser() {
        return this.user;
    }

    public void setOrders( ArrayList<Order> orders) {
        this.orders = orders;
    }

    public double getTotalSum() {
        if (orders.size() == 0) {
            totalSum = 0.0;
        } else if(totalSum == 0.0) {
            setTotalSum();
        }
        return totalSum;
    }

    public Client(Facade facade, User user) throws  IOException {
        this.facade = facade;
        this.user = user;
        this.orders = initOrders();
    }

    private void setTotalSum() {
        this.totalSum = 0;
        for(int i = 0; i < orders.size(); i++) {
            this.totalSum+=orders.get(i).getPrice();
        }
    }

    private ArrayList<Order> initOrders() {
        ArrayList<Order> orders = null;
        String ordersJSON = facade.getMessageManager().getOrdersUser("{\"user_id\":" + this.user.getId()+"}");
        if (ordersJSON != null) {
            orders = OrderList.parseUserOrders(ordersJSON);
        }
        return orders;
    }


//    @Override
//    public void run() {
//        try {
//            String findUser = url.concat("/findUser.php");
//            URL  obj = new URL(findUser);
//            con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
//            String urlParams = "login=" + login
//                    + "&password=" + password;
//            // Sending POST request
//            con.setDoOutput(true);
//            // Sending params
//            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.writeBytes(urlParams);
//            wr.flush();
//            wr.close();
//            System.out.println("\nSending 'POST' request to URL : " + findUser);
//            System.out.println("Post parameters : " + urlParams);
//            waitForResponce = true;
//
//            if (waitForResponce)
//            {
//                // Getting response stream
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(con.getInputStream()));
//
//                // Waiting and parsing response
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                        System.out.println(inputLine);
//                        System.out.println(inputLine);
//                       // parse(inputLine);
//                }
//
//                in.close();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void run()
//    {
//        try (Scanner sc = new Scanner(System.in))
//        {
//            String str = "";
//            while(true)
//            {
//                str = sc.nextLine();
//                // Connection URL
//                String url = "http://localhost/keeper/www";
//
//                // Dedication of the connection to servlet "Server"
//                URL obj = new URL(url);
//                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//                // Parsing user's input
//                String[] request_data = str.split(":");
//                switch(request_data[0])
//                {
//                    case "read":
//                        con.setRequestMethod("GET");
//                        System.out.println("\nSending 'GET' request to URL : " + url);
//                        // Set flag on ready state
//                        waitForResponce = true;
//                        break;
//
//                    case "create":
//                    case "update":
//                    case "delete":
//                        con.setRequestMethod("POST");
//                        con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
//                        String[] person = request_data[1].split(",");
//                        String urlParams = "id=" + person[0]
//                                + "&fname=" + person[1]
//                                + "&lname=" + person[2]
//                                + "&age=" + person[3]
//                                + "&method=" + request_data[0];
//                        // Sending POST request
//                        con.setDoOutput(true);
//                        // Sending params
//                        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//                        wr.writeBytes(urlParams);
//                        wr.flush();
//                        wr.close();
//                        System.out.println("\nSending 'POST' request to URL : " + url);
//                        System.out.println("Post parameters : " + urlParams);
//                        // Set flag on ready state
//                        waitForResponce = true;
//                        break;
//                    default:
//                        // Set flag on not ready state
//                        waitForResponce = false;
//                        break;
//                }
//
//                // Waiting for response (if necessary)
//                if (waitForResponce)
//                {
//                    // Getting response stream
//                    BufferedReader in = new BufferedReader(
//                            new InputStreamReader(con.getInputStream()));
//                    String response = "";
//
//                    // Waiting and parsing response
//                    String inputLine;
//                    while ((inputLine = in.readLine()) != null)
//                    {
//                        if (inputLine.equals("Success"))
//                            System.out.println(inputLine);
//                        else
//                            parse(inputLine);
//                    }
//
//                    in.close();
//                }
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

}