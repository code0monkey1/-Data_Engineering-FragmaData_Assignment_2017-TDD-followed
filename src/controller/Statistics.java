package controller;

import model.Customer;
import model.Movie;
import model.RatingAndTime;
import tempHelperObjects.MovieViewCount;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;

import java.util.*;

public final class Statistics {

    private final Map<Integer, Movie> idMovieMap;
    private final Map<Integer, Customer> idCustomerMap;
    private final Map<Integer, Map<Integer, RatingAndTime>> idCustomerIdMovieRatingAndTimeMap;

    public Statistics(final MovieMapper movieMapper,
                      final CustomerMapper customerMapper,
                      final RatingsMapper ratingsMapper) {

        this.idCustomerMap = customerMapper.getIdCustomerMap();
        this.idMovieMap = movieMapper.getIdMovieMap();
        this.idCustomerIdMovieRatingAndTimeMap = ratingsMapper.getCustomerMovieRatingMap();
    }

    public List<MovieViewCount> getTopNMostViewedMovies(int topN) {

        //first got through the rating map and for each customer ID , see the ID of movie he's watched
        Map<Integer, Map<Integer, RatingAndTime>> idCustomerIdMovieRatingAndTimeMap = this.idCustomerIdMovieRatingAndTimeMap;

        Map<Integer, Integer> movieIdViewCount = getMovieIdViewCountMap(idCustomerIdMovieRatingAndTimeMap);

        //crate SortMovieObjects With Title and count and set the equals criteria to count

        //put the SortMovieObjects in a priority queue
        PriorityQueue<MovieViewCount> movieViewCountPriorityQueue = getMovieViewCountPriorityQueue(movieIdViewCount);

        List<MovieViewCount> movieIdViewCounts = getTopNMoviesList(topN, movieViewCountPriorityQueue);


        //finally return the list of movie titles

        return movieIdViewCounts;
    }

    private List<MovieViewCount> getTopNMoviesList(int topN, PriorityQueue<MovieViewCount> movieViewCountPriorityQueue) {
        int top = Math.min(movieViewCountPriorityQueue.size(), topN);

        List<MovieViewCount> movies = new ArrayList<>();

        // take the objects of the top N movies and put it into a list
        for (int i = 0; i < top; i++) {
            MovieViewCount movieViewCount = movieViewCountPriorityQueue.poll();

            movies.add(movieViewCount);
        }
        return movies;
    }

    private PriorityQueue<MovieViewCount> getMovieViewCountPriorityQueue(Map<Integer, Integer> movieIdViewCount) {

        PriorityQueue<MovieViewCount> movieViewCountPriorityQueue = new PriorityQueue<>();

        for (int movieID : movieIdViewCount.keySet()) {

            MovieViewCount movieViewCount = getMovieViewCountObject(movieIdViewCount, movieID);

            movieViewCountPriorityQueue.add(movieViewCount);
        }
        return movieViewCountPriorityQueue;
    }

    private MovieViewCount getMovieViewCountObject(Map<Integer, Integer> movieIdViewCount, int movieID) {
        int count = movieIdViewCount.get(movieID);
        return new MovieViewCount(movieID, count);
    }

    private Map<Integer, Integer> getMovieIdViewCountMap(Map<Integer, Map<Integer, RatingAndTime>> idCustomerIdMovieRatingAndTimeMap) {

        Map<Integer, Integer> movieCountMap = new HashMap<>();

        for (Map<Integer, RatingAndTime> movieIdRatings : idCustomerIdMovieRatingAndTimeMap.values()) {

            Set<Integer> movieIds = movieIdRatings.keySet();

            for (int movieId : movieIds) {
                //increment the movie count every time you encounter a movie id in a customer map
                int count = movieCountMap.getOrDefault(movieId, 0);
                movieCountMap.put(movieId, count + 1);
            }
        }
        return movieCountMap;
    }

    private String getMovieTitle(Map<Integer, Movie> idMovieMap, int id) {
        Movie movie = this.idMovieMap.getOrDefault(id, null);

        if (movie == null) {
            return "movie not found";
        } else return movie.getTitle();
    }

    public void displayTopNMoviesWithCount(int N, Map<Integer, Movie> idMovieMap) {
        List<MovieViewCount> movieViewCountList = getTopNMostViewedMovies(N);
        int top = Math.min(movieViewCountList.size(), N);

        System.out.printf("The top : %d Movies and their Viewership is as follows : %n%n ", top);

        for (MovieViewCount movieViewCount : movieViewCountList) {
            String title = getMovieTitle(idMovieMap, movieViewCount.getMovieID());
            int count = movieViewCount.getCount();

            System.out.printf("Movie Title : %s :: Count : %d %n%n", title, count);
        }
    }


    public Map<Integer, Movie> getIdMovieMap() {
        return idMovieMap;
    }

    public Map<Integer, Customer> getIdCustomerMap() {
        return idCustomerMap;
    }

    public Map<Integer, Map<Integer, RatingAndTime>> getIdCustomerIdMovieRatingAndTimeMap() {
        return idCustomerIdMovieRatingAndTimeMap;
    }

}
