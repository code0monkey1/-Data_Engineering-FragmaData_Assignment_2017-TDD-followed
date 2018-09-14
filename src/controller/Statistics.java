package controller;

import model.primaryObjects.CustomerInfo;
import model.primaryObjects.MovieInfo;
import model.primaryObjects.RatingInfo;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;

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

    public void displayTopNMoviesWithCount(int N) {

    }


}
