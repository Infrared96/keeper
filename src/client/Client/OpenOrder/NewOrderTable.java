package client.Client.OpenOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class NewOrderTable extends JTable {
   // private Facade facade;
    public NewOrderTable(TableModelNewOrder dm) {
        super(dm);
       // this.facade = facade;

        this.getTableHeader().setPreferredSize(new Dimension(0, 16));
        this.rowHeight = 16;

        this.getTableHeader().setFont(new Font(this.getName(), Font.BOLD, 12));
        this.setFont(new Font(this.getName(), Font.PLAIN, 12));

        //Выравнивание по центру текста
        this.setDefaultRenderer(this.getColumnClass(1), new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return this;
            }

        });
    }
}

