package client.Client.OpenOrder;

import client.Model.Dish.Dish;
import client.Model.NewOrder.NewOrder;
import client.Model.NewOrder.NewOrderList;
import client.Model.Order.Order;
import client.Model.Product.Product;
import client.facade.Facade;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewOldOrder extends JPanel {
    private NewOrderTable not;
    private NewOrderTable oot;
    private Facade facade;
    private ArrayList<NewOrder> arrayOrders;
    private ControlPanel controlPanel;

    private Order or;
    public NewOldOrder(Facade facade, Order or, ControlPanel controlPanel) {
        this.facade = facade;
        this.or = or;
        this.controlPanel = controlPanel;
        setLayout(new GridLayout(2,1));

        TableModelNewOrder tableNew = new TableModelNewOrder(this.arrayOrders, this.facade);
        not = new NewOrderTable(tableNew);
        facade.setTableModelNewOrder(tableNew);
        facade.setNewOrderTable(not);
        JScrollPane scrollPane = new JScrollPane(not);
/////////////////////////////////////////////////////////////////////
        ////PANEL WITH BUTTONS TABLE///////////////
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());
        newPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(1,2,10, 10));
        JButton bAdd = new JButton("Добавить");
        JButton bDel = new JButton("Удалить");

        buttons.add(bAdd);
        buttons.add(bDel);
        newPanel.add(buttons, BorderLayout.SOUTH);
        add(newPanel);
/////////////////////////////////////////////////////
        ArrayList<NewOrder> oldOrders = NewOrderList.parseNewOrders(facade.getMessageManager().getNewOrdersId("{\"order_id\":" + or.getId() + "}"));
        oot = new NewOrderTable(new TableModelNewOrder(oldOrders, this.facade));
        JScrollPane oldScrollPane = new JScrollPane(oot);
        add(oldScrollPane);

        bAdd.addActionListener(new addNewOrders());
        not.addMouseListener(new delNewOrders());
        bDel.addActionListener(new delButtonNewOrders());
    }

    private String getProducts(ArrayList<NewOrder> arrayOrders, ArrayList<Dish> dishes) {
        //Поиск всех продуктов для списания с базы
        Map products = new HashMap();
        for(int j = 0; j < arrayOrders.size();j++) {
            int dish_id = arrayOrders.get(j).getDish_id();
            double amount = arrayOrders.get(j).getAmount();
            for(int k = 0; k < dishes.size(); k++) {
                if(dishes.get(k).getId() == dish_id) {
                    String[] products_id = dishes.get(k).getProductList().split(",");
                    String[] products_amount = dishes.get(k).getAmount_products().split(",");
                    for(int l = 0; l < products_id.length; l++) {
                        if(products.get(products_id[l]) == null) {
                            products.put(products_id[l], Double.parseDouble(String.valueOf(products_amount[l])) * amount);
                        } else {
                            String amount_product = products.get(products_id[l]).toString();
                            products.put(products_id[l], (Double.parseDouble(String.valueOf(products_amount[l])) * amount  + Double.parseDouble(String.valueOf(amount_product))));
                        }
                    }
                }
            }
        }

        JSONObject json = new JSONObject();
        json.putAll( products );


        return json.toJSONString();
    }

    private class addNewOrders implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            arrayOrders = facade.getActualOrder();
            if(arrayOrders.size() > 0) {
                facade.setActualOrder(new ArrayList<>());
                facade.setTableModelNewOrder(new TableModelNewOrder(facade.getActualOrder(), facade));
                facade.setModel_NewOrderTable(facade.getTableModelNewOrder());

                //Для того чтоб можно было заново распечатать чек
                if(or.isPrint()) {
                    or.setPrint(facade, false);
                }

                getProducts(arrayOrders, facade.getDishes());

                ArrayList<NewOrder> oldOrders = NewOrderList.parseNewOrders(facade.getMessageManager().getNewOrdersId("{\"order_id\":" + or.getId() + "}"));
                ArrayList<NewOrder> updateOrders = oldOrders;
                for(int i = 0; i < updateOrders.size(); i++) {
                    for(int j = 0; j < arrayOrders.size(); j++) {
                        if(updateOrders.get(i).getDish_id() == arrayOrders.get(j).getDish_id()) {
                            updateOrders.get(i).setPrice(updateOrders.get(i).getPrice() + arrayOrders.get(j).getPrice());
                            updateOrders.get(i).setAmount(updateOrders.get(i).getAmount() + arrayOrders.get(j).getAmount());
                            arrayOrders.remove(j);
                            break;
                        }
                    }
                }

                facade.getMessageManager().updateNewOrders(NewOrderList.parseString(updateOrders));//обновление старых ордеров
                facade.getMessageManager().createNewOrders(NewOrderList.parseString(arrayOrders));//добавление новых ордеров
                updateOrders.addAll(arrayOrders);
                oot.setModel(new TableModelNewOrder(updateOrders, facade));
                or.setPrice(facade, NewOrderList.sumPriceOrder(updateOrders));
                controlPanel.getActionNewOrderPanel().updateSumm();
            }
        }
    }

    private class delButtonNewOrders implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int row = not.getSelectedRow();
            arrayOrders = facade.getActualOrder();
            NewOrder order = arrayOrders.get(row);
            arrayOrders.remove(row);
            facade.setActualOrder(arrayOrders);
            facade.setTableModelNewOrder(new TableModelNewOrder(arrayOrders, facade));
            facade.setModel_NewOrderTable(facade.getTableModelNewOrder());

        }
    }

    private class delNewOrders extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(e.getClickCount() == 2 && e.getModifiersEx() == 0) {
                int row = not.rowAtPoint(e.getPoint());
                arrayOrders = facade.getActualOrder();
                NewOrder order = arrayOrders.get(row);
                if(order.getAmount() > 1) {
                    order.setPrice( order.getPrice() - order.getPrice()/order.getAmount()); // минусуем 1 порцию
                    order.setAmount(order.getAmount() - 1);
                } else {
                    arrayOrders.remove(row);
                }
                facade.setActualOrder(arrayOrders);
                facade.setTableModelNewOrder(new TableModelNewOrder(arrayOrders, facade));
                facade.setModel_NewOrderTable(facade.getTableModelNewOrder());
            }
        }
    }
}
