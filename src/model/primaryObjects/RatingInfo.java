package model.primaryObjects;

import java.util.Map;

public class RatingInfo {

    private final Map<Integer, Map<Integer, RatingAndTime>> customerIdMovieIdRatingAndTimeMap;

    public RatingInfo(Map<Integer, Map<Integer, RatingAndTime>> customerIdMovieIdRatingAndTimeMap) {
        this.customerIdMovieIdRatingAndTimeMap = customerIdMovieIdRatingAndTimeMap;
    }



}
