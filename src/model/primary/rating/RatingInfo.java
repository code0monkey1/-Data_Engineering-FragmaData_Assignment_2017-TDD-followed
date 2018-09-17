package model.primary.rating;

import model.primary.customer.CustomerInfo;
import model.primary.customer.EAgeRange;
import model.primary.movie.RatingAndTime;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RatingInfo {


    private final Map<Integer, Map<Integer, RatingAndTime>> customerIdMovieIdRatingAndTimeMap;

    public RatingInfo(Map<Integer, Map<Integer, RatingAndTime>> customerIdMovieIdRatingAndTimeMap) {
        this.customerIdMovieIdRatingAndTimeMap = customerIdMovieIdRatingAndTimeMap;
    }


    public Map<Integer, Integer> getMovieIdViewsMap() {
        Map<Integer, Integer> movieIdViewCount = new HashMap<>();

        for (Map<Integer, RatingAndTime> movieIdRatingAndTimeMap : customerIdMovieIdRatingAndTimeMap.values()) {
            Set<Integer> movieIdSet = movieIdRatingAndTimeMap.keySet();

            for (Integer movieID : movieIdSet) {
                int count = movieIdViewCount.getOrDefault(movieID, 0);
                movieIdViewCount.put(movieID, count + 1);
            }
        }
        return movieIdViewCount;
    }

    public Map<Integer, Integer> getMovieIdRatingsMap() {
        Map<Integer, Integer> movieIdRatings = new HashMap<>();

        for (Map<Integer, RatingAndTime> movieIdRatingAndTimeMap : customerIdMovieIdRatingAndTimeMap.values()) {

            for (int movieId : movieIdRatingAndTimeMap.keySet()) {
                RatingAndTime ratingAndTime = movieIdRatingAndTimeMap.get(movieId);

                int movieRating = ratingAndTime.getRating();
                int existingRatings = movieIdRatings.getOrDefault(movieId, 0);

                movieIdRatings.put(movieId, existingRatings + movieRating);

            }
        }
        return movieIdRatings;
    }


    public Map<Integer, EnumMap<EAgeRange, Integer>> getMovieIdAgeRangeMap(CustomerInfo
                                                                                   customerInfo) {

        Map<Integer, EnumMap<EAgeRange, Integer>> movieIdAgeRangeMap = new HashMap<>();

        for (Integer customerID : customerIdMovieIdRatingAndTimeMap.keySet()) {

            EAgeRange ageRange = customerInfo.getAgeRange(customerID);

            Map<Integer, RatingAndTime> idRatingAndTimeMap = customerIdMovieIdRatingAndTimeMap.get(customerID);

            Set<Integer> movieIdSet = idRatingAndTimeMap.keySet();

            for (Integer movieID : movieIdSet) {

                EnumMap<EAgeRange, Integer> ageRangeCountMap = movieIdAgeRangeMap.getOrDefault(movieID, new EnumMap<>(EAgeRange.class));

                int presentAgeRangeCount = ageRangeCountMap.getOrDefault(ageRange, 0);

                ageRangeCountMap.put(ageRange, presentAgeRangeCount + 1);

                movieIdAgeRangeMap.put(movieID, ageRangeCountMap);
            }

        }
        return movieIdAgeRangeMap;
    }
}
