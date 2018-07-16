package main;

import interval.Interval;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

public class Controller {
    IntervalManager manager;
    View view;
    ActionListener addInterval, startInterval;

    public Controller(IntervalManager manager) {
        this.manager = manager;
        this.view = new View(this);
    }

    public void addInterval(Interval interval) {
        manager.addInterval(interval);
    }

    public void startAll() {
        manager.startAll();
    }
}
