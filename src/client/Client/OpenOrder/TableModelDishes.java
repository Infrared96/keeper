package client.Client.OpenOrder;

import client.Model.Dish.Dish;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModelDishes extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Dish> dishes = null;

    TableModelDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public int getRowCount() {
        return dishes.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Dish dish = dishes.get(rowIndex);
        String ret = null;
        switch(columnIndex)
        {
            case 0: ret = dish.getName()+""; break;
            case 1: ret = dish.getAmount()+""; break;
            case 2: ret = dish.getPrice()+"";break;
        }

        return ret;
    }

    public String getColumnName(int column)
    {
        String[] name = {"Название","Вес(г/мл)","Цена(грн)"};
        return name[column];
    }
}
