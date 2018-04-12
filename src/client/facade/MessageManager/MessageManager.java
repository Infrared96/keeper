package client.facade.MessageManager;

import client.facade.RequestManager.RequestManager;

public class MessageManager {
    private RequestManager requestManager = null;

    public MessageManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public String createOrder (String str) {
        return requestManager.sendRequest("CREATE_ORDER", str);
    }

    public String getOrderId(String str) {
        return requestManager.sendRequest("GET_ORDER_ID", str);
    }

    public String getOrdersUser(String str) {
        return requestManager.sendRequest("GET_ORDERS_USER", str);
    }

    public String getProduct(String str) {
        return requestManager.sendRequest("GET_PRODUCT", str);
    }

    public String getProducts(String str) {
        return requestManager.sendRequest("GET_PRODUCTS", str);
    }

    public String getProductsId(String str) {
        return requestManager.sendRequest("GET_PRODUCTS_ID", str);
    }

    public String getDish(String str) {
        return requestManager.sendRequest("GET_DISH", str);
    }

    public String getDishes(String str) {
        return requestManager.sendRequest("GET_DISHES", str);
    }

    public String getDishId(String str) {
        return requestManager.sendRequest("GET_DISH_ID", str);
    }

    public String getUserLogin(String str) {
        return requestManager.sendRequest("GET_USER_LOGIN", str);
    }

    public String getNewOrderListFromTable(String str) {
        return requestManager.sendRequest("GET_NEW_ORDER_TABLE", str);
    }

    public String getNewOrdersId(String str) {
        return requestManager.sendRequest("GET_NEW_ORDERS_ID", str);
    }

    public String updateNewOrders(String str) {
        return requestManager.sendRequest("UPDATE_NEW_ORDERS", str);
    }

    public String getCategorys() {
        return requestManager.sendRequest("GET_CATEGORYS","");
    }

}
