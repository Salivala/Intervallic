package main;


import interval.Interval;
import javafx.concurrent.Worker;
import view.Displayable;
import view.View;

import java.time.Duration;
import java.util.ArrayList;

public class Controller {
    Runnable updateAction;
    ArrayList<Thread> intervalThreads;
    Displayable view;
    Controller() {
        view = new View(this);
        intervalThreads = new ArrayList<>(100);
    }

    public void addIntervalThread(Interval interval, int index) {
        intervalThreads.add(
                new Thread(initializeIntervalThreadCode(interval, index))
        );
    }


    /**
     * Produce a new Runnable that is tailored towards a specific interval and index
     * @param interval
     * @param index
     * @return
     */
    private Runnable initializeIntervalThreadCode(Interval interval, int index) {
        return () -> {
            while (!Thread.currentThread().isInterrupted()) {
                view.updateTimer(interval, index);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                interval.tick();
            }
        };
    }


    public void startIntervals() {
        for (Thread thread : intervalThreads) {
            if (thread.getState() == Thread.State.NEW) {
                thread.start();
            }
        }
    }
}
