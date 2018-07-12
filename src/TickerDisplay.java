import javax.swing.*;

public class TickerDisplay implements View {
    TickerController controller;

    /**
     *
     */
    private JPanel panel1;
    private JButton startIntervallicButton;
    private JLabel timeLeft;
    private JSlider slider1;
    private IntervallicTicker ticker;

    TickerDisplay(IntervallicTicker ticker, TickerController controller) {
        this.controller = controller;
        this.ticker = ticker;
        slider1.setValue(60);
        displayTime(0,1,0);
        slider1.setMinimum(0);
        slider1.setMaximum(3600);
        JFrame frame = new JFrame("TickerDisplay");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        addStartListener();
        addSliderListener();

    }

    private void addSliderListener() {
        slider1.addChangeListener(e -> {
            IntervallicTicker tmpTicker = new IntervallicTicker(new DoNothing());
            tmpTicker.convertIntToTime(slider1.getValue());
            displayTime(tmpTicker.hours(), tmpTicker.minutes(), tmpTicker.seconds());
            ticker.hours = tmpTicker.hours;
            ticker.minutes = tmpTicker.minutes;
            ticker.seconds = tmpTicker.seconds;
        });
    }
    private void addStartListener() {
        startIntervallicButton.addActionListener(e -> {
            ticker.convertIntToTime(slider1.getValue());
            controller.startTicker(ticker.hours(), ticker.minutes(), ticker.seconds());
            slider1.setMaximum(slider1.getValue());
            slider1.setEnabled(false);
            startIntervallicButton.setEnabled(false);
        });
    }

    @Override
    public void displayTime(int hours, int minutes, int seconds) {
        timeLeft.setText(formatTime(hours) + ":" + formatTime(minutes) + ":" + formatTime(seconds));
    }

    @Override
    public void updateElement(int rawTime) {
        slider1.setValue(rawTime);
        System.out.println(rawTime);
    }

    private String formatTime(int time) {
        return time < 10 ? "0" + time : Integer.toString(time);
    }

    private TimeHelper massageInput() {
        String txt = timeLeft.getText();
        TimeHelper res = new TimeHelper();
        return new TimeHelper();
    }

    /**
     * Pojo for moving time around
     */
    private class TimeHelper {
        int hours;
        int minutes;
        int seconds;

        public TimeHelper(){};
        public TimeHelper(int hours, int minutes, int seconds) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }
    }


}
