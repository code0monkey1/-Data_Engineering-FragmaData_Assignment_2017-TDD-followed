package view;

import controller.Statistics;

import java.util.Scanner;

public class UserInterface {

    private Statistics statistics;
    private Scanner reader;

    public UserInterface(Statistics statistics) {

        this.statistics = statistics;
        this.reader = new Scanner(System.in);
    }


    public void start() {

        while (true) {
            displayOptions();

            int choice = choice();

            if (isTerminationCondition(choice)) {
                System.out.println("Program Exiting..... ");
                break;
            } else {
                executeOption(choice);
            }

        }
    }

    private boolean isTerminationCondition(int i) {
        return i == 0;
    }

    public void displayOptions() {

        topMoviesText();

        topRatedMoviesText();

        topRatedMoviesWithViewsText();

        topCriticsText();
    }

    private void topCriticsText() {
        System.out.println("To display top N critics (Customers " +
                " who have given the lowest Ratings) (Condition : " +
                "should have rated at least 40 movies) \nEnter : 4 \n");
    }

    private void topRatedMoviesWithViewsText() {
        System.out.println("To display top N Rated Movies (Condition : movies " +
                "should be rated/viewed by at least 40 users \n and " +
                "their viewership among Young , Young Adult and Adult" +
                " users \nEnter : 3\n");
    }

    private void topRatedMoviesText() {
        System.out.println("To display top N Rated Movies (Condition : movies " +
                "should be rated/viewed by at least 40 users \nEnter : 2\n");
    }

    private void topMoviesText() {
        System.out.println("\nTo display top N most viewed movies with their" +
                " movie names \n Enter : 1\n");
    }

    public void executeOption(int choice) {

        switch (choice) {
            case 1:
                mostViewedMovies();
                break;
            case 2:
                mostRatedMovies();
                break;
            case 3:
                topRatedMoviesWithAgeCategorisation();
                break;
            case 4:
                displayTopCritics();
                break;
            default:
                invalidOption();
        }
    }

    private void displayTopCritics() {

        System.out.println("Enter the top N number of critics you want to see :");

        int critics = choice();

        System.out.println("Enter the minimum movies they should have watched ");

        int minMovies = choice();

        statistics.displayTopCritics(critics, minMovies);
    }

    private int choice() {
        int choice = 0;
        String option = "";

        try {
            option = reader.nextLine();
            choice = Integer.parseInt(option);

        } catch (NumberFormatException e) {

            throw new NumberFormatException(String.format("The input : \"" +
                    " %s \" is not a number !!", option));
        }
        return choice;
    }

    private void invalidOption() {
        String warning = "";

        warning += "XXXXXXXXXXXXXXXXXXXXXXXX\n";
        warning += "Invalid option!! Enter valid option:\n";
        warning += "XXXXXXXXXXXXXXXXXXXXXXXX\n";

        System.out.println(warning);
    }

    private void topRatedMoviesWithAgeCategorisation() {
        System.out.println("Enter N top movie count :");
        int movies = choice();

        System.out.println("Enter M min view count : ");
        int views = choice();

        statistics.displayTopRatedMoviesWithAgeRange(movies, views);
    }

    private void mostViewedMovies() {
        int movies;
        System.out.println("To display top N most viewed movies" +
                " with their movie names , enter the number");

        movies = choice();

        statistics.displayTopViewedMovies(movies);
    }

    private void mostRatedMovies() {
        int movies;
        System.out.println("To display top N most rated movies " +
                "with their movie names , enter the number");
        movies = choice();

        System.out.println(" Enter the minimum acceptable view count :");

        int views = choice();

        statistics.displayTopRatedMovies(movies, views);
    }

}
