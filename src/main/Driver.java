package main;

import view.View;

import java.time.Duration;

public class Driver {
    public static void main(String[] args) {
        //main.Controller controller = new main.Controller(new main.IntervalManager());
        Controller controller = new Controller(new IntervalManager());
    }
}
