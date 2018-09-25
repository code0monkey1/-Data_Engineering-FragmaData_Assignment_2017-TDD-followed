package controller;

import model.helperObjects.ViewedMovie;
import model.primary.rating.RatingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopViewedMovies {
    private int n;
    private RatingInfo ratingInfo;


    public TopViewedMovies(int n, RatingInfo ratingInfo) {
        this.n = n;
        this.ratingInfo = ratingInfo;
    }

    public List<ViewedMovie> getMoviesList() {
        Map<Integer, Integer> movieViews = ratingInfo.getMovieIdViewsCountMap();

        PriorityQueue<ViewedMovie> viewedMovieQueue = movieViewPriorityQueue(movieViews);

        List<ViewedMovie> topViewedMovies = topViewedMovies(n, viewedMovieQueue);

        return topViewedMovies;
    }

    private PriorityQueue<ViewedMovie> movieViewPriorityQueue(Map<Integer, Integer> movieIdViewCount) {

        PriorityQueue<ViewedMovie> viewedMoviePriorityQueue = new PriorityQueue<>();
        for (int id : movieIdViewCount.keySet()) {
            int count = movieIdViewCount.get(id);

            ViewedMovie viewedMovie = new ViewedMovie(id, count);
            viewedMoviePriorityQueue.add(viewedMovie);
        }
        return viewedMoviePriorityQueue;
    }

    private List<ViewedMovie> topViewedMovies(int N, PriorityQueue<ViewedMovie> viewedMoviePriorityQueue) {

        int top = Math.min(viewedMoviePriorityQueue.size(), N);
        List<ViewedMovie> topViewedMovies = new ArrayList<>();

        for (int i = 0; i < top; i++) {
            ViewedMovie viewedMovie = viewedMoviePriorityQueue.poll();
            topViewedMovies.add(viewedMovie);
        }
        return topViewedMovies;
    }
}
