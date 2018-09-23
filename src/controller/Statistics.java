package controller;

import model.helperObjects.CustomerRating;
import model.helperObjects.MovieRating;
import model.helperObjects.MovieView;
import model.primary.customer.CustomerInfo;
import model.primary.movie.MovieInfo;
import model.primary.rating.RatingInfo;

import java.util.List;
import java.util.Map;

public final class Statistics {
    private final MovieInfo movieInfo;
    private final CustomerInfo customerInfo;
    private final RatingInfo ratingInfo;


    public Statistics(CustomerInfo customerInfo,
                      MovieInfo movieInfo,
                      RatingInfo ratingInfo) {

        this.movieInfo = movieInfo;
        this.customerInfo = customerInfo;
        this.ratingInfo = ratingInfo;
    }

    public List<MovieView> getTopViewedMovies(int N) {

        return new TopViewedMovies(N, ratingInfo).getMoviesList();
    }

    public List<MovieRating> getTopRatedMovies(int N, int minViews) {

        return new TopRatedMovies(N, minViews, ratingInfo).getMoviesList();
    }

    public Map<Integer, Map> getMovieViewershipAgeRangeCount(int N, int minViews) {

        return new TopRatedMoviesViewership(N, minViews,ratingInfo,customerInfo).invoke();
    }

    public List<CustomerRating> getCustomersWithRatingSatisfyingMinViewCondition(int top, int minViewCount) {
        return new TopCritics(top, minViewCount, ratingInfo).getCriticsList();

    }

    private void printRatedMovies(List<MovieRating> topRatedMovies) {

        for (MovieRating movie : topRatedMovies) {
            System.out.printf("Movie : %s  ::  Rating : %.2f:: " +
                    "Views : %d %n", movieInfo.getTitle(movie.getId()), movie.getRating(), movie.getViews());
        }
    }


    private void printCustomerMovieRatings(List<CustomerRating> customerRatings) {

        for (CustomerRating customer : customerRatings) {
            System.out.printf("The customer id is %d %n", customer.getId());
            System.out.printf("The average rating given" +
                    " by the customer is %.2f %n", customer.getRating());
            System.out.printf("The movie view count of " +
                    "the given customer is %d %n %n", customer.getViews());
        }
    }

    public void displayTopCritics(int top, int views) {
        List<CustomerRating> customers = getCustomersWithRatingSatisfyingMinViewCondition(top, views);
        printCustomerMovieRatings(customers);
    }

    private void printRatedMoviesWithAgeCategory(List<MovieRating> topRatedMovies, Map<Integer, Map> movieIdAgeRangeMap) {

        for (MovieRating movie : topRatedMovies) {
            System.out.printf("Movie : %s  ::  Rating : %.2f:: Views : %d %n", movieInfo.getTitle(movie.getId()), movie.getRating(), movie.getViews());
            Map<Integer, Map> movieIdMap = movieIdAgeRangeMap.get(movie.getId());
            System.out.printf("Number of young viewers : %d %n" +
                            "Number of adult viewers : %d %n" +
                            "Number of old viewers : %d %n %n", movieIdMap.get(0),
                    movieIdMap.get(1),
                    movieIdMap.get(2));
        }
    }

    public void displayTopRatedMoviesWithAgeRange(int top, int min) {
        List<MovieRating> movieRatings = getTopRatedMovies(top, min);
        Map<Integer, Map> movieIdAgeRangeMap = getMovieViewershipAgeRangeCount(top, min);
        printRatedMoviesWithAgeCategory(movieRatings, movieIdAgeRangeMap);
    }

    public void displayTopRatedMovies(int top, int min) {
        List<MovieRating> movieRatings = getTopRatedMovies(top, min);
        printRatedMovies(movieRatings);
    }

    public void displayTopViewedMovies(int top) {

        List<MovieView> viewedMovies = getTopViewedMovies(top);
        displayTopNMostViewedMovies(viewedMovies);

    }


    public void displayTopNMostViewedMovies(List<MovieView> topViewedMovies) {

        for (MovieView movie : topViewedMovies) {
            String movieTitle = this.movieInfo.getTitle(movie.getMovieID());
            int viewCount = movie.getCount();
            System.out.printf("Movie : %s :: Views : %d %n ", movieTitle, viewCount);
        }
    }



}
