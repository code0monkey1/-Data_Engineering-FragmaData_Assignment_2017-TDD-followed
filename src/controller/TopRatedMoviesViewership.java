package controller;

import model.helperObjects.MovieRating;
import model.primary.customer.AgeRange;
import model.primary.customer.CustomerInfo;
import model.primary.rating.RatingInfo;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopRatedMoviesViewership {
        private int n;
        private int minViews;
        private RatingInfo ratingInfo;
        private CustomerInfo customerInfo;

        public TopRatedMoviesViewership(int N, int minViews,
                                        RatingInfo ratingInfo,
                                        CustomerInfo customerInfo) {
            n = N;
            this.minViews = minViews;
            this.ratingInfo = ratingInfo;
            this.customerInfo=customerInfo;
        }

        public Map<Integer, Map> invoke() {
            Map<Integer, EnumMap<AgeRange, Integer>> movieIdCustomerAgeRangeMap = movieIdCustomerAgeRange(n, minViews);

            List<MovieRating> movieRatingList = new TopRatedMovies(n, minViews,ratingInfo).getMoviesList();
            Map<Integer, Map> movieIdAgeRangeCount = new HashMap<>();

            for (MovieRating movie : movieRatingList) {
                Map<Integer, Integer> ageCategoryCount = ageCategoryCountMap(movie, movieIdCustomerAgeRangeMap);

                movieIdAgeRangeCount.put(movie.getId(), ageCategoryCount);
            }

            return movieIdAgeRangeCount;
        }

        private Map<Integer, Integer> ageCategoryCountMap(MovieRating movie,
                                                          Map<Integer, EnumMap<AgeRange, Integer>> movieViewerAgeDistribution) {
            Map<Integer, Integer> ageCategoryCount = new HashMap<>();

            int movieID = movie.getId();
            int[] ageRange = new int[3];

            EnumMap<AgeRange, Integer> ageRanges = movieViewerAgeDistribution.get(movieID);

            ageRange[0] = ageRanges.getOrDefault(AgeRange.UNDER_EIGHTEEN, 0);
            ageRange[1] = ageRanges.getOrDefault(AgeRange.TWENTY_FIVE_TO_THIRTY_FOUR, 0);
            ageRange[2] = ageRanges.getOrDefault(AgeRange.FORTY_FIVE_TO_FORTY_NINE, 0);
            ageRange[2] = ageRange[2] + ageRanges.getOrDefault(AgeRange.FIFTY_TO_FIFTY_FIVE, 0);
            ageRange[2] = ageRange[2] + ageRanges.getOrDefault(AgeRange.FIFTY_SIX_AND_OVER, 0);


            ageCategoryCount.put(0, ageRange[0]);
            ageCategoryCount.put(1, ageRange[1]);
            ageCategoryCount.put(2, ageRange[2]);

            return ageCategoryCount;

        }

        private Map<Integer, EnumMap<AgeRange, Integer>> movieIdCustomerAgeRange(int N, int minViews) {

            Map<Integer, EnumMap<AgeRange, Integer>> movieIdAgeMap = new HashMap<>();

            // go through the list , getThe age range of person and put it in the map for movie
            List<MovieRating> movieRatingList = new TopRatedMovies(N, minViews,ratingInfo).getMoviesList();
            Map<Integer, EnumMap<AgeRange, Integer>> movieIdAgeRangeMap = ratingInfo.getMovieIdAgeRangeMap(customerInfo);

            // go through movie and for movie ID , fill the Emum Map
            for (MovieRating movie : movieRatingList) {
                movieIdAgeMap.put(movie.getId(), movieIdAgeRangeMap.get(movie.getId()));
            }

            return movieIdAgeMap;

        }
    }