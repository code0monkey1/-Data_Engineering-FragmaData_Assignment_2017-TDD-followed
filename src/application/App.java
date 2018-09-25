package application;

import controller.Statistics;
import view.DisplayStatistics;
import view.UserInterface;

public class App {
    private Statistics statistics;

    public App(Statistics statistics) {
        this.statistics = statistics;
    }

    public void start() {
        DisplayStatistics ds = new DisplayStatistics(statistics);
        UserInterface ui = new UserInterface(ds);
        ui.start();
    }
}
