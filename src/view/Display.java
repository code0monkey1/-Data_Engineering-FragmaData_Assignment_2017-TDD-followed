package view;

class Display {

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
}
