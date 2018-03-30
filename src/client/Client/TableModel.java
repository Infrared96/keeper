package client.Client;

import client.Model.Order.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class TableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Order> ol = null;

     public TableModel(ArrayList<Order> ol) {
        this.ol = ol;
    }

    @Override
    public int getRowCount() {
        return ol.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Order or = ol.get(rowIndex);
        String ret = null;
        switch(columnIndex)
        {
            case 0: ret = or.getId()+""; break;
            case 1: ret = or.getTable_num()+""; break;
            case 2: ret = or.getCount()+"";break;
            case 3: ret = or.getPrice()+""; break;
        }

        return ret;
    }

    public String getColumnName(int column)
    {
        String[] name = {"№","Стол","Количество людей", "Сумма заказa(грн)"};
        return name[column];
    }
}
