package controller;

import conditions.CountAtLeast;
import model.helperObjects.CustomerRating;
import model.helperObjects.MovieRating;
import model.helperObjects.MovieView;
import model.primary.customer.CustomerInfo;
import model.primary.customer.EAgeRange;
import model.primary.movie.MovieInfo;
import model.primary.rating.RatingInfo;
import util.math.Calculate;

import java.util.*;

public final class Statistics {
    private final MovieInfo movieInfo;
    private final CustomerInfo customerInfo;
    private final RatingInfo ratingInfo;

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public RatingInfo getRatingInfo() {
        return ratingInfo;
    }


    public Statistics(CustomerInfo customerInfo,
                      MovieInfo movieInfo,
                      RatingInfo ratingInfo) {

        this.movieInfo = movieInfo;
        this.customerInfo = customerInfo;
        this.ratingInfo = ratingInfo;
    }

    public List<MovieView> getTopViewedMovies(int N) {

        return new TopViewedMovies(N).getMoviesList();
    }

    public List<MovieRating> getTopRatedMovies(int N, int minViews) {

        return new TopRatedMovies(N, minViews).getMoviesList();
    }

    public Map<Integer, Map> getMovieViewershipAgeRangeCount(int N, int minViews) {

        return new TopRatedMoviesWithViewershipAgeCategorization(N, minViews).invoke();
    }

    public List<CustomerRating> getCustomersWithRatingSatisfyingMinViewCondition(int top, int minViewCount) {
        return new TopCritics(top, minViewCount).getCriticsList();

    }

    private void printRatedMovies(List<MovieRating> topRatedMovies) {

        for (MovieRating movie : topRatedMovies) {
            System.out.printf("Movie : %s  ::  Rating : %.2f:: Views : %d %n", movieInfo.getTitle(movie.getId()), movie.getRating(), movie.getViews());
        }
    }


    private void printCustomerMovieRatings(List<CustomerRating> customerRatings) {

        for (CustomerRating customer : customerRatings) {
            System.out.printf("The customer id is %d %n", customer.getId());
            System.out.printf("The average rating given by the customer is %.2f %n", customer.getRating());
            System.out.printf("The movie view count of the given customer is %d %n %n", customer.getViews());
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

    public void displayTopRatedMoviesWithAgeRangeCategorization(int top, int min) {
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


    private class TopRatedMovies {
        private int n;
        private int minViews;

        public TopRatedMovies(int N, int minViews) {
            n = N;
            this.minViews = minViews;
        }

        public List<MovieRating> getMoviesList() {
            Map<Integer, Integer> idRating = Statistics.this.ratingInfo.getMovieIdRatingsMap();

            Map<Integer, Integer> idView = Statistics.this.ratingInfo.getMovieIdViewsCountMap();

            List<MovieRating> movieRatings = returnRatedMovies(minViews, idRating, idView);

            PriorityQueue<MovieRating> movieRatingQueue = returnRatedMovieQueue(movieRatings);

            List<MovieRating> topRatedMovies = returnTopRatedMoviesWhichFulfillCondition(n, movieRatingQueue);

            return topRatedMovies;
        }


        private List<MovieRating> returnRatedMovies(int minViews,
                                                    Map<Integer, Integer> idRating,
                                                    Map<Integer, Integer> idView) {

            List<MovieRating> movieRatings = new ArrayList<>();

            for (int id : idRating.keySet()) {
                int views = idView.get(id);
                int rating = idRating.get(id);
                CountAtLeast minViewCondition = new CountAtLeast(minViews, views);

                if (minViewCondition.isValid()) {
                    MovieRating movie = returnValidMovieObject(id, views, rating);
                    movieRatings.add(movie);
                }
            }
            return movieRatings;
        }

        private MovieRating returnValidMovieObject(int id, int views, int rating) {
            double avjRating = Calculate.returnAverage(rating, views);
            return new MovieRating(id, avjRating, views);
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

        public List<MovieView> getMoviesList() {
            Map<Integer, Integer> movieViews = ratingInfo.getMovieIdViewsCountMap();

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


    private class TopRatedMoviesWithViewershipAgeCategorization {
        private int n;
        private int minViews;

        public TopRatedMoviesWithViewershipAgeCategorization(int N, int minViews) {
            n = N;
            this.minViews = minViews;
        }

        public Map<Integer, Map> invoke() {
            Map<Integer, EnumMap<EAgeRange, Integer>> movieIdCustomerAgeRangeMap = returnMovieIdCustomerAgeRange(n, minViews);

            List<MovieRating> movieRatingList = getTopRatedMovies(n, minViews);
            Map<Integer, Map> movieIdAgeRangeCount = new HashMap<>();

            for (MovieRating movie : movieRatingList) {
                Map<Integer, Integer> ageCategoryCount = returnAgeCategoryCountMap(movie, movieIdCustomerAgeRangeMap);

                movieIdAgeRangeCount.put(movie.getId(), ageCategoryCount);
            }

            return movieIdAgeRangeCount;
        }

        private Map<Integer, Integer> returnAgeCategoryCountMap(MovieRating movie,
                                                                Map<Integer, EnumMap<EAgeRange, Integer>> movieViewerAgeDistribution) {
            Map<Integer, Integer> ageCategoryCount = new HashMap<>();

            int movieID = movie.getId();
            int[] ageRange = new int[3];

            EnumMap<EAgeRange, Integer> ageRanges = movieViewerAgeDistribution.get(movieID);

            ageRange[0] = ageRanges.getOrDefault(EAgeRange.UNDER_EIGHTEEN, 0);
            ageRange[1] = ageRanges.getOrDefault(EAgeRange.TWENTY_FIVE_TO_THIRTY_FOUR, 0);
            ageRange[2] = ageRanges.getOrDefault(EAgeRange.FORTY_FIVE_TO_FORTY_NINE, 0);
            ageRange[2] = ageRange[2] + ageRanges.getOrDefault(EAgeRange.FIFTY_TO_FIFTY_FIVE, 0);
            ageRange[2] = ageRange[2] + ageRanges.getOrDefault(EAgeRange.FIFTY_SIX_AND_OVER, 0);


            ageCategoryCount.put(0, ageRange[0]);
            ageCategoryCount.put(1, ageRange[1]);
            ageCategoryCount.put(2, ageRange[2]);

            return ageCategoryCount;

        }

        private Map<Integer, EnumMap<EAgeRange, Integer>> returnMovieIdCustomerAgeRange(int N, int minViews) {

            Map<Integer, EnumMap<EAgeRange, Integer>> movieIdAgeMap = new HashMap<>();

            // go through the list , getThe age range of person and put it in the map for movie
            List<MovieRating> movieRatingList = getTopRatedMovies(N, minViews);
            Map<Integer, EnumMap<EAgeRange, Integer>> movieIdAgeRangeMap = ratingInfo.getMovieIdAgeRangeMap(customerInfo);

            // go through movie and for movie ID , fill the Emum Map
            for (MovieRating movie : movieRatingList) {
                movieIdAgeMap.put(movie.getId(), movieIdAgeRangeMap.get(movie.getId()));
            }

            return movieIdAgeMap;

        }
    }

    public class TopCritics {
        private int top;
        private int minViewCount;


        public TopCritics(int top, int minViewCount) {
            this.top = top;
            this.minViewCount = minViewCount;

        }

        public List<CustomerRating> getCriticsList() {

            Map<Integer, Long> customerIdRatingMap = ratingInfo.getCustomerIdRatingMap();

            Map<Integer, Integer> customerIdViewershipMap = ratingInfo.getCustomerIdMoviesSeenCountMap();

            PriorityQueue<CustomerRating> customerRatingsQueue = returnCustomerRatingObjectsQueueAfterFulfillingViewCountCriteria(minViewCount, customerIdRatingMap, customerIdViewershipMap);

            List<CustomerRating> customers = returnCustomerRatingObjectList(top, customerRatingsQueue);


            return customers;
        }


        private PriorityQueue<CustomerRating> returnCustomerRatingObjectsQueueAfterFulfillingViewCountCriteria(int minViewCount,
                                                                                                               Map<Integer, Long> customerIdRatingMap,
                                                                                                               Map<Integer, Integer> customerIdViewershipMap) {

            PriorityQueue<CustomerRating> customerRatingsQueue = new PriorityQueue<>();

            List<Integer> customerIds = ratingInfo.getAllCustomerIds();

            for (int customerId : customerIds) {

                long customerRatingCount = customerIdRatingMap.get(customerId);
                int customerViewershipCount = customerIdViewershipMap.get(customerId);

                CountAtLeast count = new CountAtLeast(minViewCount, customerViewershipCount);
                if (count.isValid()) {
                    double average = Calculate.returnAverage(customerRatingCount, customerViewershipCount);
                    customerRatingsQueue.add(new CustomerRating(customerId, average, customerViewershipCount));
                }

            }

            return customerRatingsQueue;
        }

        private List<CustomerRating> returnCustomerRatingObjectList(int top, PriorityQueue<CustomerRating> customerRatingsQueue) {
            int count = Math.min(top, customerRatingsQueue.size());

            List<CustomerRating> customers = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                customers.add(customerRatingsQueue.poll());
            }
            return customers;
        }


    }

}
