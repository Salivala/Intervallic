import javax.swing.*;

public class TickerDisplay implements Observer{
    private JPanel panel1;
    private JSlider slider1;
    private JButton startIntervallicButton;
    private JLabel timeLeft;
    JFrame frame = new JFrame("TickerDisplay");

    TickerDisplay() {
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void update(short hours, short minutes, short seconds) {
        timeLeft.setText(hours + ":" + minutes + ":" + seconds);
    }
}
