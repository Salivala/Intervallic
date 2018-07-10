import javax.swing.*;

public class TickerDisplay {
    private JPanel panel1;
    private JSlider slider1;
    private JButton startIntervallicButton;

    TickerDisplay() {
        JFrame frame = new JFrame("TickerDisplay");
        frame.setContentPane(new TickerDisplay().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
