package view;

import util.IO.IO;

public class UserInterface {


    private DisplayStatistics displayStatistics;

    public UserInterface(DisplayStatistics displayStatistics) {

        this.displayStatistics = displayStatistics;
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

        displayStatistics.invoke();
    }

    private boolean isTerminationChoice(int i) {
        return i == 0;
    }


    public void executeChoice(int choice) {

        switch (choice) {
            case 1:
                displayStatistics.topViewedMovies();
                break;
            case 2:
                displayStatistics.topRatedMovies();
                break;
            case 3:
                displayStatistics.topRatedMoviesWithAgeCategorisation();
                break;
            case 4:
                displayStatistics.topCritics();
                break;
            default:
                displayStatistics.invalidChoice();
        }
    }


}
