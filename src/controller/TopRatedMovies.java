package controller;

import conditions.CountAtLeast;
import model.helperObjects.MovieRating;
import model.primary.rating.RatingInfo;
import util.math.Calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopRatedMovies {
    private int n;
    private int minViews;
    private RatingInfo ratingInfo;

    public TopRatedMovies(int N, int minViews, RatingInfo ratingInfo) {
        n = N;
        this.minViews = minViews;
        this.ratingInfo = ratingInfo;
    }

    public List<MovieRating> getMoviesList() {
        Map<Integer, Integer> idRating = ratingInfo.getMovieIdRatingsMap();

        Map<Integer, Integer> idView = ratingInfo.getMovieIdViewsCountMap();

        List<MovieRating> movieRatings = ratedMovies(minViews, idRating, idView);

        PriorityQueue<MovieRating> movieRatingQueue = ratedMovieQueue(movieRatings);

        List<MovieRating> topRatedMovies = topRatedMoviesWhichFulfillCondition(n, movieRatingQueue);

        return topRatedMovies;
    }


    private List<MovieRating> ratedMovies(int minViews,
                                          Map<Integer, Integer> idRating,
                                          Map<Integer, Integer> idView) {

        List<MovieRating> movieRatings = new ArrayList<>();

        for (int id : idRating.keySet()) {
            int views = idView.get(id);
            int rating = idRating.get(id);
            CountAtLeast minViewCondition = new CountAtLeast(minViews, views);

            if (minViewCondition.isValid()) {
                MovieRating movie = validMovieObject(id, views, rating);
                movieRatings.add(movie);
            }
        }
        return movieRatings;
    }

    private MovieRating validMovieObject(int id, int views, int rating) {
        double avjRating = Calculate.average(rating, views);
        return new MovieRating(id, avjRating, views);
    }

    private PriorityQueue<MovieRating> ratedMovieQueue(List<MovieRating> movieRatings) {
        PriorityQueue<MovieRating> movieRatingPriorityQueue = new PriorityQueue<>();

        for (MovieRating movie : movieRatings) {
            movieRatingPriorityQueue.add(movie);
        }

        return movieRatingPriorityQueue;
    }

    private List<MovieRating> topRatedMoviesWhichFulfillCondition(int N,
                                                                  PriorityQueue<MovieRating> movieRatingQueue) {
        List<MovieRating> topMoviesAsPerRating = new ArrayList<>();
        int top = Math.min(N, movieRatingQueue.size());
        for (int i = 0; i < top; i++) {
            topMoviesAsPerRating.add(movieRatingQueue.poll());
        }
        return topMoviesAsPerRating;
    }
}
