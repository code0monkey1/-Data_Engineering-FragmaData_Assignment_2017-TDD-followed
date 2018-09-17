package controller;

import conditions.MovieSeenByAtLeast;
import model.helperObjects.MovieRating;
import model.helperObjects.MovieView;
import model.primary.customer.CustomerInfo;
import model.primary.customer.EAgeRange;
import model.primary.movie.MovieInfo;
import model.primary.rating.RatingInfo;

import java.util.*;

public final class Statistics {

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public RatingInfo getRatingInfo() {
        return ratingInfo;
    }

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

        return new TopViewedMovies(N).invoke();
    }

    public List<MovieRating> getTopRatedMovies(int N, int minViews) {

        return new TopRatedMovies(N, minViews).invoke();
    }


    public Map<Integer, EnumMap<EAgeRange, Integer>> getMovieIdCustomerAgeRangeMap() {

        Map<Integer, EnumMap<EAgeRange, Integer>> movieIdAgeMap = new HashMap<>();

        // go through the list , getThe age range of person and put it in the map for movie
        // return map

        RatingInfo ratingInfo = this.ratingInfo;

        return movieIdAgeMap;
    }

    private void printRatedMovies(List<MovieRating> topRatedMovies) {

        for (MovieRating movie : topRatedMovies) {
            System.out.printf("Movie : %s  ::  Rating : %.2f:: Views : %d %n", movieInfo.getTitle(movie.getId()), movie.getRating(), movie.getViews());
        }
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


    private class TopRatedMovies {
        private int n;
        private int minViews;

        public TopRatedMovies(int N, int minViews) {
            n = N;
            this.minViews = minViews;
        }

        public List<MovieRating> invoke() {
            Map<Integer, Integer> idRating = Statistics.this.ratingInfo.getMovieIdRatingsMap();

            Map<Integer, Integer> idView = Statistics.this.ratingInfo.getMovieIdViewsMap();

            List<MovieRating> movieRatings = returnRatedMovies(minViews, idRating, idView);

            PriorityQueue<MovieRating> movieRatingQueue = returnRatedMovieQueue(movieRatings);

            List<MovieRating> topRatedMovies = returnTopRatedMoviesWhichFulfillCondition(n, movieRatingQueue);

            return topRatedMovies;
        }

        private double returnAverageRating(int rating, int views) {
            return (1.0 * rating) / views;
        }

        private List<MovieRating> returnRatedMovies(int minViews,
                                                    Map<Integer, Integer> idRating,
                                                    Map<Integer, Integer> idView) {

            List<MovieRating> movieRatings = new ArrayList<>();

            for (int id : idRating.keySet()) {
                int views = idView.get(id);
                int rating = idRating.get(id);
                MovieSeenByAtLeast minViewCondition = new MovieSeenByAtLeast(minViews, views);

                if (minViewCondition.isValid()) {
                    double avjRating = returnAverageRating(rating, views);
                    MovieRating movie = new MovieRating(id, avjRating, views);
                    movieRatings.add(movie);
                }
            }
            return movieRatings;
        }

        private PriorityQueue<MovieRating> returnRatedMovieQueue(List<MovieRating> movieRatings) {
            PriorityQueue<MovieRating> movieRatingPriorityQueue = new PriorityQueue<>();

            for (MovieRating movie : movieRatings) {
                movieRatingPriorityQueue.add(movie);
            }

            return movieRatingPriorityQueue;
        }

        private List<MovieRating> returnTopRatedMoviesWhichFulfillCondition(int N,
                                                                            PriorityQueue<MovieRating> movieRatingQueue) {
            List<MovieRating> topMoviesAsPerRating = new ArrayList<>();
            int top = Math.min(N, movieRatingQueue.size());
            for (int i = 0; i < top; i++) {
                topMoviesAsPerRating.add(movieRatingQueue.poll());
            }
            return topMoviesAsPerRating;
        }
    }

    private class TopViewedMovies {
        private int n;

        public TopViewedMovies(int N) {
            n = N;
        }

        public List<MovieView> invoke() {
            Map<Integer, Integer> movieViews = ratingInfo.getMovieIdViewsMap();

            PriorityQueue<MovieView> movieViewQueue = returnMovieViewPriorityQueue(movieViews);

            List<MovieView> topViewedMovies = returnTopViewedMovies(n, movieViewQueue);

            return topViewedMovies;
        }

        private PriorityQueue<MovieView> returnMovieViewPriorityQueue(Map<Integer, Integer> movieIdViewCount) {

            PriorityQueue<MovieView> movieViewPriorityQueue = new PriorityQueue<>();
            for (int id : movieIdViewCount.keySet()) {
                int count = movieIdViewCount.get(id);

                MovieView movieView = new MovieView(id, count);
                movieViewPriorityQueue.add(movieView);
            }
            return movieViewPriorityQueue;
        }

        private List<MovieView> returnTopViewedMovies(int N, PriorityQueue<MovieView> movieViewPriorityQueue) {

            int top = Math.min(movieViewPriorityQueue.size(), N);
            List<MovieView> topViewedMovies = new ArrayList<>();

            for (int i = 0; i < top; i++) {
                MovieView movieView = movieViewPriorityQueue.poll();
                topViewedMovies.add(movieView);
            }
            return topViewedMovies;
        }
    }


}
