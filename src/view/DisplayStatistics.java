package view;

import controller.Statistics;
import util.IO.IO;

public class DisplayStatistics {
    private Statistics statistics;

    public DisplayStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void invoke() {
        topMoviesText();

        topRatedMoviesText();

        topRatedMoviesWithViewsText();

        topCriticsText();
    }

    private void topMoviesText() {
        System.out.println("\nTo display top N most viewed movies with their" +
                " movie names \n Enter : 1\n");
    }

    private void topRatedMoviesText() {
        System.out.println("To display top N Rated Movies (Condition : movies " +
                "should be rated/viewed by at least 40 users \nEnter : 2\n");
    }

    private void topRatedMoviesWithViewsText() {
        System.out.println("To display top N Rated Movies (Condition : movies " +
                "should be rated/viewed by at least 40 users \n and " +
                "their viewership among Young , Young Adult and Adult" +
                " users \nEnter : 3\n");
    }

    private void topCriticsText() {
        System.out.println("To display top N critics (Customers " +
                " who have given the lowest Ratings) (Condition : " +
                "should have rated at least 40 movies) \nEnter : 4 \n");
    }

    public void invalidChoice() {
        String warning = "";

        warning += "XXXXXXXXXXXXXXXXXXXXXXXX\n";
        warning += "Invalid option!! Enter valid option:\n";
        warning += "XXXXXXXXXXXXXXXXXXXXXXXX\n";

        System.out.println(warning);
    }
    public void topCritics() {

        System.out.println("Enter the top N number of critics you want to see :");

        int critics = IO.inputInt();

        System.out.println("Enter the minimum movies they should have watched ");

        int minMovies = IO.inputInt();

        statistics.displayTopCritics(critics, minMovies);
    }

    public void topRatedMoviesWithAgeCategorisation() {
        System.out.println("Enter N top movie count :");
        int movies = IO.inputInt();

        System.out.println("Enter M min view count : ");
        int views = IO.inputInt();

        statistics.displayTopRatedMoviesWithAgeRange(movies, views);
    }
    public void topViewedMovies() {
        int movies;
        System.out.println("To display top N most viewed movies" +
                " with their movie names , enter the number");

        movies = IO.inputInt();

        statistics.displayTopViewedMovies(movies);
    }

    public void topRatedMovies() {
        int movies;
        System.out.println("To display top N most rated movies " +
                "with their movie names , enter the number");
        movies = IO.inputInt();

        System.out.println(" Enter the minimum acceptable view count :");

        int views = IO.inputInt();

        statistics.displayTopRatedMovies(movies, views);
    }

}
