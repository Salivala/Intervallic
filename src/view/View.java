package view;

import interval.Interval;
import main.Controller;
import main.MathUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.time.Duration;

public class View implements Displayable{
    private final int NEWTICKEROFFSET = 17;
    private JFrame frame = new JFrame("Intervallic");
    private JPanel panel = new JPanel();
    private JPanel setPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JComboBox<String> hours, minutes, seconds;
    private JList<String> times;
    private JButton add, start;
    private String[] numbers = new String[60];
    private Controller controller;
    private int timerCount = 0;


    public View(Controller controller) {
        this.controller = controller;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = String.valueOf(i);
        }
        times = new JList<>(new DefaultListModel<>());
        configPanes();
        centerPanel.add(times);
        initComponents();
        ((DefaultListModel) times.getModel()).add(timerCount++, "|      Intervallic Timers    |");
        addAddActionListener();
        addStartActionListener();
        configFrame();

    }

    void initComponents() {
        hours = new JComboBox<>(new DefaultComboBoxModel<String>(numbers));
        minutes = new JComboBox<>(new DefaultComboBoxModel<String>(numbers));
        seconds = new JComboBox<>(new DefaultComboBoxModel<String>(numbers));
        add = new JButton("add");
        start = new JButton("start timers");
        setPanel.add(hours);
        setPanel.add(minutes);
        setPanel.add(seconds);
        setPanel.add(add);
        setPanel.add(start);
    }

    public void configFrame() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void configPanes() {
        panel.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        centerPanel.setLayout(new FlowLayout());
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(setPanel, BorderLayout.SOUTH);
        setPanel.setLayout(new FlowLayout());
    }

    public void addAddActionListener() {
        add.addActionListener(e -> {
            Interval time = new Interval(
                    Duration.ofHours(Integer.parseInt((String) hours.getSelectedItem()))
                    .plusMinutes(Integer.parseInt((String) minutes.getSelectedItem()))
                    .plusSeconds(1 + Integer.parseInt((String) seconds.getSelectedItem())));

             String timeItem = getProgress(time.getStartingDuration(), time.getStartingDuration());
             controller.addIntervalThread(time, timerCount);
            ((DefaultListModel) times.getModel()).add(timerCount++, timeItem);
            /*
            time.addTickListener(() -> {
                ((DefaultListModel<String>) times.getModel()).set(time.index,
                        getProgress(time.getDuration(), time.getStartingDuration()));
            });
            */
            //controller.addInterval(time);
            frame.setLocation(frame.getX(), frame.getY() - NEWTICKEROFFSET); // set ylocation to the item add offset
            frame.pack(); // repack
        });
    }

    private void addStartActionListener() {
        start.addActionListener(e -> {
            controller.startIntervals();
            //controller.startAll();
        });
    }

    /**
     *
     * @param interval
     * @param index
     */
    public void updateTimer(Interval interval, int index) {
        ((DefaultListModel) times.getModel()).set(
                index, getProgress(interval.getDuration(), interval.getStartingDuration())
        );
    }

    /**
     * Given two durations, returns a string representing a progress bar of the percent current
     * is of total
     * @param current
     * @param total
     * @return
     */
    public String getProgress(Duration current, Duration total) {
        final int totalPossibleBars = 20;
        double percentFromTotal = MathUtils.getPercentOf(current.toSeconds(), total.toSeconds());
        int barsToRemove = (int) (.2 * (percentFromTotal));
        String progress = "||||||||||||||||||||";
        for (int i = 0; i < totalPossibleBars - barsToRemove; i++) {
            progress = progress.substring(0, progress.length() - 1);
        }
        return formatTime((int) current.toHours()) + ":" +
                formatTime(current.toMinutesPart()) + ":" +
                formatTime(current.toSecondsPart()) + "       " +
                progress;
    }

    private String formatTime(int time) {
        return time < 10 ? "0" + time : Integer.toString(time);
    }
}
