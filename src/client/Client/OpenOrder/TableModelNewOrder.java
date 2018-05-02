package client.Client.OpenOrder;

import client.Model.Dish.Dish;
import client.Model.Dish.DishesList;
import client.Model.NewOrder.NewOrder;
import client.facade.Facade;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModelNewOrder extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ArrayList<NewOrder> ol = null;
    private Facade facade;

    public TableModelNewOrder(ArrayList<NewOrder> ol, Facade facade) {
        this.facade = facade;
        this.ol = ol;
    }

    @Override
    public int getRowCount() {
        int ret = 0;
        if(ol != null) {
            ret = ol.size();
        }
        return ret;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        NewOrder or = ol.get(rowIndex);
        Dish dish = DishesList.parseDishe(facade.getMessageManager().getDishId("{\"id\":" + or.getDish_id() + "}"));
        String ret = null;
        switch(columnIndex)
        {
            case 0: ret = or.getId() + ""; break;
            case 1: ret = dish.getName(); break;
            case 2: ret = dish.getAmount()+"";break;
            case 3: ret = or.getAmount()+""; break;
           // case 4: ret = (or.getAmount()*dish.getPrice())+""; break;
            case 4: ret = or.getPrice()+""; break;
        }
        return ret;
    }

    public String getColumnName(int column)
    {
        String[] name = {"№","Название","Порция", "Количество", "Цена(грн)"};
        return name[column];
    }
}
