package controller;

import conditions.CountAtLeast;
import model.helperObjects.RatedMovie;
import model.primary.rating.RatingInfo;
import util.math.Calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopRatedMovies {
    private int top;
    private int minViews;
    private Map<Integer, Integer> idRatingsMap;
    private Map<Integer, Integer> idViewCountMap;
    private List<RatedMovie> ratedMovies;
    private PriorityQueue<RatedMovie> ratedMoviePriorityQueue;
    private List<RatedMovie> topRatedMovies;

    public TopRatedMovies(int top,
                          int minViews,
                          RatingInfo ratingInfo) {
        this.top = top;
        this.minViews = minViews;
        this.idRatingsMap = ratingInfo.getMovieIdRatingsMap();
        this.idViewCountMap = ratingInfo.getMovieIdViewsCountMap();
        this.ratedMovies = new ArrayList<>();
        this.ratedMoviePriorityQueue = new PriorityQueue<>();
        this.topRatedMovies = new ArrayList<>();
    }

    public List<RatedMovie> getMovieList() {

        ratedMovies = ratedMovies();

        PriorityQueue<RatedMovie> movieQueue = movieQueue(ratedMovies);

        List<RatedMovie> topRatedMovies = topRatedMovies(top, movieQueue);

        return topRatedMovies;
    }


    private List<RatedMovie> ratedMovies() {

        for (int id : idRatingsMap.keySet())
            addMovieToList(id);

        return ratedMovies;
    }

    private void addMovieToList(int id) {

        int views = idViewCountMap.get(id);
        CountAtLeast minViewCondition = new CountAtLeast(minViews, views);

        if (minViewCondition.isValid()) {
            RatedMovie movie = ratedMovie(id);
            ratedMovies.add(movie);
        }
    }

    private RatedMovie ratedMovie(int id) {
        int views = idViewCountMap.get(id);
        int rating = idRatingsMap.get(id);

        double avjRating = Calculate.average(rating, views);

        return new RatedMovie(id, avjRating, views);
    }

    private PriorityQueue<RatedMovie> movieQueue(List<RatedMovie> ratedMovies) {

        for (RatedMovie movie : ratedMovies)
            ratedMoviePriorityQueue.add(movie);

        return ratedMoviePriorityQueue;
    }

    private List<RatedMovie> topRatedMovies(int N,
                                            PriorityQueue<RatedMovie> ratedMovieQueue) {
        int top = Math.min(N, ratedMovieQueue.size());

        for (int i = 0; i < top; i++)
            topRatedMovies.add(ratedMovieQueue.poll());

        return topRatedMovies;
    }
}
