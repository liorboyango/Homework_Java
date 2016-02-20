package homework.Utils;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Calendar;

//sets the tasks table's deadline cell color according to if the deadline expired/today/future
public class TasksTableRenderer extends DefaultTableCellRenderer {

    private final Color Red = new Color(255, 101, 101);
    private final Color Blue = new Color(102, 204, 255);
    private final Color Orange = new Color(255, 153, 102);

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(Blue);  //default color for future deadline
        if (column == 2) {
            String taskDate = table.getValueAt(row, column).toString();     //get task deadline date to String
            Date today = Calendar.getInstance().getTime();  // get today's date and time
            String datePattern = "dd/MM/yyyy";
            SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
            String todayString = dateFormatter.format(today);   //convert today's date and time to String in order to compare without time - only date
            try {
                if (dateFormatter.parse(todayString).equals(dateFormatter.parse(taskDate))) {   //if task deadline is today
                    c.setBackground(Orange);
                } else if (dateFormatter.parse(todayString).after(dateFormatter.parse(taskDate))) { //if task deadline has expired
                    c.setBackground(Red);
                }

            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return c;
    }

}
