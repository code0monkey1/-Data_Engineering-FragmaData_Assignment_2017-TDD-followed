package view;

import controller.Statistics;
import model.Movie;

import java.util.Map;
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
            String option = reader.nextLine();
            option = option.trim();

            if (stop(option)) {
                System.out.println("Program Exiting..... ");
                break;
            } else {
                executeOption(option);
            }

        }
    }

    private boolean stop(String option) {
        return option.equals("0");
    }

    public void displayOptions() {
        System.out.println("\nTo display top N most viewed movies with their movie names \nEnter : 1\n");
        System.out.println("To display top N Rated Movies (Condition : movies should be rated/viewed by at least 40 users \nEnter : 2\n");
        System.out.println("To display top N Rated Movies (Condition : movies should be rated/viewed by at least 40 users \n and their their viewership among Young , Young Adult and Adult users \nEnter : 3\n");
        System.out.println("To display top N critics (Customers  who have given the lowest Ratings) (Condition : should have rated at least 40 movies) \nEnter : 4 \n");

    }

    public void executeOption(String option) {

        switch (option) {
            case "1":
                System.out.println("To display top N most viewed movies with their movie names ,%n enter the N number ");
                int n = Integer.parseInt(reader.nextLine());
                Map<Integer, Movie> idMovieMap = statistics.getIdMovieMap();

                statistics.displayTopNMoviesWithCount(n, idMovieMap);
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;

        }
    }

}
