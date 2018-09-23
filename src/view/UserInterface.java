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

            int choice = returnChoice();

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

        System.out.println("\nTo display top N most viewed movies with their movie names \nEnter : 1\n");

        System.out.println("To display top N Rated Movies (Condition : movies should be rated/viewed by at least 40 users \nEnter : 2\n");

        System.out.println("To display top N Rated Movies (Condition : movies should be rated/viewed by at least 40 users \n and their their viewership among Young , Young Adult and Adult users \nEnter : 3\n");

        System.out.println("To display top N critics (Customers  who have given the lowest Ratings) (Condition : should have rated at least 40 movies) \nEnter : 4 \n");

    }

    public void executeOption(int option) {

        switch (option) {
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

        int critics = returnChoice();

        System.out.println("Enter the minimum movies they should have watched ");

        int minMovies = returnChoice();

        statistics.displayTopCritics(critics, minMovies);
    }

    private int returnChoice() {
        int n = 0;
        String option = "";

        try {
            option = reader.nextLine();
            n = Integer.parseInt(option);

        } catch (NumberFormatException e) {

            throw new NumberFormatException(String.format("The input : \"" +
                    " %s \" is not a number !!", option));
        }
        return n;
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
        int movies = returnChoice();

        System.out.println("Enter M min view count : ");
        int views = returnChoice();

        statistics.displayTopRatedMoviesWithAgeRange(movies, views);
    }

    private void mostViewedMovies() {
        int movies;
        System.out.println("To display top N most viewed movies with their movie names , enter the number");

        movies = returnChoice();

        statistics.displayTopViewedMovies(movies);
    }

    private void mostRatedMovies() {
        int movies;
        System.out.println("To display top N most rated movies with their movie names , enter the number");
        movies = returnChoice();

        System.out.println(" Enter the minimum acceptable view count :");

        int views = returnChoice();

        statistics.displayTopRatedMovies(movies, views);
    }

}
