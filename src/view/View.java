package view;

import interval.Interval;
import main.Controller;
import main.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;

public class View {
    JFrame frame = new JFrame("Intervallic");
    JPanel panel = new JPanel();
    JPanel setPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JComboBox<String> hours, minutes, seconds;
    JList<String> times;
    JButton add, start;
    String[] numbers = new String[60];
    Controller controller;
    int k = 0;


    public View(Controller controller) {
        this.controller = controller;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = String.valueOf(i);
        }
        times = new JList<>(new DefaultListModel<>());
        configPanes();
        centerPanel.add(times);
        initComponents();
        ((DefaultListModel) times.getModel()).add(k++, "|      Intervallic Timers    |");
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
             time.index = k;
            ((DefaultListModel) times.getModel()).add(k++, timeItem);
            time.addTickListener(() -> {
                ((DefaultListModel<String>) times.getModel()).set(time.index,
                        getProgress(time.getDuration(), time.getStartingDuration()));
            });
            controller.addInterval(time);
            frame.setLocation(frame.getX(), frame.getY() - 17); // set ylocation to the item add offset
            frame.pack(); // repack
        });
    }

    public void addStartActionListener() {
        start.addActionListener(e -> {
            controller.startAll();
        });
    }

    void addTimer() {

    }

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
