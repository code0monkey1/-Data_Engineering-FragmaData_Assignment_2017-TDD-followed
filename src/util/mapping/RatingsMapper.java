package util.mapping;

import conditions.AllConditions;
import conditions.Condition;
import conditions.CustomerID;
import conditions.MovieID;
import model.primary.movie.RatingAndTime;
import util.FileParsing.FileParser;
import wrappers.RatingsMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//UserID::MovieID::Rating::Timestamp
//
//        UserIDs range between 1 and 6040
//        MovieIDs range between 1 and 3952
//        Ratings are made on a 5-star scale (whole-star ratings only)
//        Timestamp is represented in seconds since the epoch as returned by time(2)
//        Each user has at least 20 ratings

public final class RatingsMapper {
    private RatingsMap customerIDMovieIDRatingAndTimeMap;

    public RatingsMapper(FileParser fileParser, int fields) {

        customerIDMovieIDRatingAndTimeMap = returnRatingsMap(fileParser, fields);

    }


    private RatingsMap returnRatingsMap(FileParser fileParser, int fields) {

        List<List<String>> rawEntriesList = fileParser.getRawList();
        RatingsMap tempCustomerMovieRatingMap = new RatingsMap();

        for (List<String> rawEntry : rawEntriesList) {

            if (!hasValidFields(rawEntry, fields)) continue;

            String customerId = rawEntry.get(0);
            String movieId = rawEntry.get(1);


            int CUSTOMER_ID = Integer.parseInt(customerId);
            int MOVIE_ID = Integer.parseInt(movieId);

            Condition customerIdAndMovieIdValue = new AllConditions(new CustomerID(CUSTOMER_ID), new MovieID(MOVIE_ID));

            if (!customerIdAndMovieIdValue.isValid())
                throw new IllegalArgumentException("Customer ID or Movie ID Illegal");

            //find out if map entry for user exists , if not assign a new hashmap

            Map<Integer, RatingAndTime> movieIdRatingTimeMap =tempCustomerMovieRatingMap.getOrDefault(CUSTOMER_ID, new HashMap<>());

            String rating = rawEntry.get(2);
            String timeStamp = rawEntry.get(3);

            RatingAndTime ratingAndTime = returnRatingAndTime(rating, timeStamp);

            //if it does just enter the movie entry ( override it if the user has already rated this movie)
            movieIdRatingTimeMap.put(MOVIE_ID, ratingAndTime);

            //finally assign the movieId Rating and Time map to customerID map

            tempCustomerMovieRatingMap.put(CUSTOMER_ID, movieIdRatingTimeMap);

        }


        return tempCustomerMovieRatingMap;
    }

    private boolean hasValidFields(List<String> rawEntry, int fields) {
        return rawEntry.size() == fields;
    }

    private RatingAndTime returnRatingAndTime(String rating, String time) {
        RatingAndTime ratingAndTime = new RatingAndTime(rating, time);

        return ratingAndTime;
    }

    public RatingsMap getCustomerIDMovieIDRatingAndTimeMap() {
        return customerIDMovieIDRatingAndTimeMap;
    }


//    public static void main(String[] args) {
//        RatingsMapper ratingsFileParser = new RatingsMapper("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatings.dat", "::", 4);
//    }


}
