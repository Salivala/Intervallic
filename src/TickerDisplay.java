import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class TickerDisplay implements View{
    private JPanel panel1;
    private JSlider slider1;
    private JButton startIntervallicButton;
    private JLabel timeLeft;
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
        timeLeft.setText("0" + n.toString());
    }

    @Override
    public TimeContainer getTime() {
        return strToTimeContainer();
    }

    private TimeContainer strToTimeContainer() {
        //short hours = Short.parseShort(timeLeft.getText().substring(0, timeLeft.getText().indexOf(':')));
        short hours, minutes, seconds;
        hours = Short.parseShort(timeLeft.getText().substring(0, 2));
        minutes = Short.parseShort(timeLeft.getText().substring(3, 5));
        seconds = Short.parseShort(timeLeft.getText().substring(6, 8));
        return new TimeContainer(hours,minutes,seconds);
    }
}
