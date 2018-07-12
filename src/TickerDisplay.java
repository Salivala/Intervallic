import jdk.nashorn.api.tree.Tree;
import jdk.nashorn.api.tree.TreeVisitor;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class TickerDisplay implements View{
    private JPanel panel1;
    private JButton startIntervallicButton;
    private JLabel timeLeft;
    private JTextField inputTime;
    JFrame frame = new JFrame("TickerDisplay");

    TickerDisplay(ActionListener action) {
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        startIntervallicButton.addActionListener(action);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void setTime(TimeContainer n) {
        timeLeft.setText(n.toString());
    }

    @Override
    public TimeContainer getTime() {
        return strToTimeContainer();
    }

    private TimeContainer strToTimeContainer() {
        //short hours = Short.parseShort(timeLeft.getText().substring(0, timeLeft.getText().indexOf(':')));
        short hours, minutes, seconds;
        hours = Short.parseShort(inputTime.getText().substring(0, 2));
        minutes = Short.parseShort(inputTime.getText().substring(3, 5));
        seconds = Short.parseShort(inputTime.getText().substring(6, 8));
        return new TimeContainer(hours,minutes,seconds);
    }
}
