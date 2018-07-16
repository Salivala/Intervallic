package main;

import interval.*;

import java.util.ArrayList;

public class IntervalManager {
    private ArrayList<Interval> intervals;

    public IntervalManager() {
        intervals = new ArrayList<>();
    }
    public Interval getInterval(int index) {
        return intervals.get(index);
    }
    public void addInterval(Interval newInterval) {
        intervals.add(newInterval);
    }

    public void startAll() {
        for (Interval interval : intervals) {
            if (!interval.isAlive()) {
                interval.start();
            }
        }
    }
}
