package controller;

import model.helperObjects.Critic;
import model.helperObjects.RatedMovie;
import model.helperObjects.ViewedMovie;
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

    public List<ViewedMovie> getTopViewedMovies(int N) {

        return new TopViewedMovies(N, ratingInfo).getMoviesList();
    }

    public List<RatedMovie> getTopRatedMovies(int N, int minViews) {

        return new TopRatedMovies(N, minViews, ratingInfo).getMovieList();
    }

    public Map<Integer, Map> getTopRatedMoviesViewership(int N, int minViews) {

        return new TopRatedMoviesViewership(N, minViews, ratingInfo, customerInfo).invoke();
    }

    public List<Critic> getTopCritics(int top, int minViewCount) {

        return new TopCritics(top, minViewCount, ratingInfo).getCritics();

    }

    public void displayTopRatedMoviesWithAgeRange(int top, int min) {

        List<RatedMovie> ratedMovies = getTopRatedMovies(top, min);

        Map<Integer, Map> movieIdAgeRangeMap = getTopRatedMoviesViewership(top, min);

        printRatedMoviesWithAgeCategory(ratedMovies, movieIdAgeRangeMap);
    }

    public void displayTopRatedMovies(int top, int min) {
        List<RatedMovie> ratedMovies = getTopRatedMovies(top, min);
        movieInfo.printRatedMovies(ratedMovies);
    }

    public void displayTopViewedMovies(int top) {

        List<ViewedMovie> viewedMovies = getTopViewedMovies(top);

        for (ViewedMovie movie : viewedMovies) {

            String movieTitle = this.movieInfo.getTitle(movie.getMovieID());

            int viewCount = movie.getCount();

            System.out.printf("Movie : %s :: Views : %d %n ", movieTitle, viewCount);
        }

    }

    public void displayTopCritics(int top, int views) {

        List<Critic> customers = getTopCritics(top, views);

        ratingInfo.printCustomerMovieRatings(customers);
    }

    private void printRatedMoviesWithAgeCategory(List<RatedMovie> topRatedMovies,
                                                 Map<Integer, Map> movieIdAgeRangeMap) {

        for (RatedMovie movie : topRatedMovies)
            movieRatingDescriptionString(movieIdAgeRangeMap, movie);

    }

    private void movieRatingDescriptionString(Map<Integer, Map> movieIdAgeRangeMap,
                                              RatedMovie movie) {

        System.out.printf("Movie : %s  ::  Rating : %.2f:: Views : %d %n",
                movieInfo.getTitle(movie.getId()),
                movie.getRating(),
                movie.getViews());

        Map<Integer, Map> movieIdMap = movieIdAgeRangeMap.get(movie.getId());

        viewershipDescription(movieIdMap);
    }

    private void viewershipDescription(Map<Integer, Map> movieIdMap) {

        System.out.printf("Number of young viewers : %d %n" +
                        "Number of adult viewers : %d %n" +
                        "Number of old viewers : %d %n %n",
                movieIdMap.get(0),
                movieIdMap.get(1),
                movieIdMap.get(2));
    }


}
