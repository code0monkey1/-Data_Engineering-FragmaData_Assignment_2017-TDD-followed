package controller;

import model.helperObjects.MovieView;
import model.primary.rating.RatingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopViewedMovies {
    private int n;
    private RatingInfo ratingInfo;

    public TopViewedMovies(int N, RatingInfo ratingInfo) {
        n = N;
        this.ratingInfo=ratingInfo;
    }

    public List<MovieView> getMoviesList() {
        Map<Integer, Integer> movieViews = ratingInfo.getMovieIdViewsCountMap();

        PriorityQueue<MovieView> movieViewQueue = movieViewPriorityQueue(movieViews);

        List<MovieView> topViewedMovies = topViewedMovies(n, movieViewQueue);

        return topViewedMovies;
    }

    private PriorityQueue<MovieView> movieViewPriorityQueue(Map<Integer, Integer> movieIdViewCount) {

        PriorityQueue<MovieView> movieViewPriorityQueue = new PriorityQueue<>();
        for (int id : movieIdViewCount.keySet()) {
            int count = movieIdViewCount.get(id);

            MovieView movieView = new MovieView(id, count);
            movieViewPriorityQueue.add(movieView);
        }
        return movieViewPriorityQueue;
    }

    private List<MovieView> topViewedMovies(int N, PriorityQueue<MovieView> movieViewPriorityQueue) {

        int top = Math.min(movieViewPriorityQueue.size(), N);
        List<MovieView> topViewedMovies = new ArrayList<>();

        for (int i = 0; i < top; i++) {
            MovieView movieView = movieViewPriorityQueue.poll();
            topViewedMovies.add(movieView);
        }
        return topViewedMovies;
    }
}
