package controller;

import conditions.MovieSeenByAtLeast;
import model.helperObjects.MovieRating;
import model.helperObjects.MovieView;
import model.primary.customer.CustomerInfo;
import model.primary.movie.MovieInfo;
import model.primary.rating.RatingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
        // 1. get a map <id,viewCount> from the ratingsInfo class
        Map<Integer, Integer> movieIdViewCount = ratingInfo.getMovieIdViewsMap();
        // 2. feed the id and movie count variables into an MovieView
        // object
        // 3. feed all the MovieView objects into a priority queue
        PriorityQueue<MovieView> movieViewPriorityQueue = returnMovieViewQueue(movieIdViewCount);
        // 4. poll the objects from the top
        // 5. get List Of top polled objects and finally display
        List<MovieView> topViewedMovies = returnTopViewedMovies(N, movieViewPriorityQueue);
        // --> Get the movie name by using the MovieInfo class to get name from ID
        return topViewedMovies;
    }

    public List<MovieRating> getTopRatedMovies(int N, int minViews) {


        //1. get a map <movieId,ratingCount> from MovieInfo
        Map<Integer, Integer> idRating = this.ratingInfo.getMovieIdRatingsMap();
        //2. get a map of <id, viewCount> from movieInfo
        Map<Integer, Integer> idView = this.ratingInfo.getMovieIdViewsMap();
        //3. scan the ratingmap one by one by one for the (contition)
        //4. Make MovieRating objects and put it into a priority queue
        List<MovieRating> movieRatings = returnRatedMovies(minViews, idRating, idView);
        //condition : the movie must have been viewed by at least 40 users
        PriorityQueue<MovieRating> movieRatingQueue = returnRatedMovieQueue(movieRatings);
        //5.poll the objects and put it into a list , finally display the list
        List<MovieRating> topRatedMovies = returnTopRatedMoviesWhichFulfillCondition(N, movieRatingQueue);

        return topRatedMovies;
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


    private PriorityQueue<MovieRating> returnRatedMovieQueue(List<MovieRating> movieRatings) {
        PriorityQueue<MovieRating> movieRatingPriorityQueue = new PriorityQueue<>();

        for (MovieRating movie : movieRatings) {
            movieRatingPriorityQueue.add(movie);
        }

        return movieRatingPriorityQueue;
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

    private double returnAverageRating(int rating, int views) {
        return (1.0 * rating) / views;
    }

    public void displayTopNMostViewedMovies(List<MovieView> topViewedMovies) {

        for (MovieView movie : topViewedMovies) {
            String movieTitle = this.movieInfo.getTitle(movie.getMovieID());
            int viewCount = movie.getCount();
            System.out.printf("Movie : %s :: Views : %d %n ", movieTitle, viewCount);
        }
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

    private PriorityQueue<MovieView> returnMovieViewQueue(Map<Integer, Integer> movieIdViewCount) {

        PriorityQueue<MovieView> movieViewPriorityQueue = new PriorityQueue<>();
        for (int id : movieIdViewCount.keySet()) {
            int count = movieIdViewCount.get(id);

            MovieView movieView = new MovieView(id, count);
            movieViewPriorityQueue.add(movieView);
        }
        return movieViewPriorityQueue;
    }


}
