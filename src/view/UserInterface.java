package view;

import controller.Statistics;
import util.IO.IO;

public class UserInterface {

    private Statistics statistics;

    public UserInterface(Statistics statistics) {

        this.statistics = statistics;

    }


    public void start() {

        while (true) {

            displayChoices();

            int choice = IO.inputInt();

            if (isTerminationChoice(choice)) {
                System.out.println("Program Exiting..... ");
                break;
            } else
                executeChoice(choice);

        }
    }

    public void displayChoices() {

        new Display().invoke();
    }

    private boolean isTerminationChoice(int i) {
        return i == 0;
    }


    public void executeChoice(int choice) {

        switch (choice) {
            case 1:
                statistics.topViewedMovies();
                break;
            case 2:
                statistics.topRatedMovies();
                break;
            case 3:
                statistics.topRatedMoviesWithAgeCategorisation();
                break;
            case 4:
                statistics.topCritics();
                break;
            default:
                statistics.invalidChoice();
        }
    }


}
