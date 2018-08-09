package view;

import interval.Interval;
import main.Controller;
import main.MathUtils;
import xmlswingpagefactory.XmlSwingPage;
import xmlswingpagefactory.XmlSwingPageFactory;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XmlGeneratedView implements Displayable{
    private XmlSwingPageFactory factory;
    private XmlSwingPage xsp;
    private JComboBox<String> hours, minutes, seconds;
    private JList<String> timerList;
    private DefaultListModel<String> listModel;
    private Controller controller;
    private int timerCount = 0;
    public XmlGeneratedView(Controller controller) {
        this.controller = controller;
        factory = new XmlSwingPageFactory(Paths.get("./src", "view", "view.xml"));
        xsp = factory.newXmlSwingPage();
        hours = xsp.getComboBox("hours");
        minutes = xsp.getComboBox("minutes");
        seconds = xsp.getComboBox("seconds");
        timerList = xsp.getList("timerlist");
        setComboBoxes(); // this
        listModel = new DefaultListModel<>();
        timerList.setModel(listModel);
        xsp.getButton("add").addActionListener(onAddClick());
        xsp.getButton("start").addActionListener(onStartClick());
    }

    private ActionListener onAddClick() {
        return (e -> {
            Interval time = new Interval(
                    Duration.ofHours(Integer.parseInt((String) hours.getSelectedItem()))
                    .plusMinutes(Integer.parseInt((String) minutes.getSelectedItem()))
                    .plusSeconds(1 + Integer.parseInt((String) seconds.getSelectedItem())));
            String timeItem = getProgress(time.getStartingDuration(), time.getStartingDuration());
            controller.addIntervalThread(time, timerCount);
            listModel.add(timerCount++, timeItem);
        });
    }

    private ActionListener onStartClick() {
        return e -> controller.startIntervals();
    }

    private void setComboBoxes() {
        List<String> list = Stream.iterate(BigInteger.ZERO, (integer) ->
                integer.add(BigInteger.ONE)).limit(60).map(BigInteger::toString).collect(Collectors.toList());
        DefaultComboBoxModel<String> hourList = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> minuteList = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> secondList = new DefaultComboBoxModel<>();
        list.forEach(hourList::addElement);
        list.forEach(secondList::addElement);
        list.forEach(minuteList::addElement);
        hours.setModel(hourList);
        minutes.setModel(minuteList);
        seconds.setModel(secondList);
    }

    @SuppressWarnings("all")
    private String getProgress(Duration current, Duration total) {
        final int totalPossibleBars = 30;
        double percentFromTotal = MathUtils.getPercentOf(current.toSeconds(), total.toSeconds());
        int barsToRemove = (int) (.2 * (percentFromTotal));
        String progress = "||||||||||||||||||||||||||||||";
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

    @Override
    public void updateTimer(Interval interval, int index) {
        listModel.set(
                index, getProgress(interval.getDuration(), interval.getStartingDuration())
        );
    }
}
