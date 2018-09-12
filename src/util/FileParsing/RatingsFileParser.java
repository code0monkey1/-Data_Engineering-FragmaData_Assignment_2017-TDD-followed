package util.FileParsing;

import conditions.AllConditions;
import conditions.Condition;
import conditions.CustomerIdValid;
import conditions.MovieIdValid;
import model.RatingAndTime;

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

public final class RatingsFileParser extends FileParser {
    private int fields;
    private Map<Integer, Map<Integer, RatingAndTime>> customerMovieRatingMap;

    public RatingsFileParser(String fileName, String parseToken, int fields) {
        super(fileName, parseToken);
        this.fields = fields;
        customerMovieRatingMap = returnCustomerMovieRatingMap();
    }

    private Map<Integer, Map<Integer, RatingAndTime>> returnCustomerMovieRatingMap() {

        List<List<String>> rawEntriesList = this.getRawEntriesList();
        Map<Integer, Map<Integer, RatingAndTime>> tempCustomerMovieRatingMap = new HashMap<>();
        for (List<String> rawEntry : rawEntriesList) {

            //extract info of each entry

            String customerId = rawEntry.get(0);
            String movieId = rawEntry.get(1);


            int CUSTOMER_ID = Integer.parseInt(customerId);
            int MOVIE_ID = Integer.parseInt(movieId);

            Condition condition = new AllConditions(new CustomerIdValid(CUSTOMER_ID), new MovieIdValid(MOVIE_ID));

            if (!condition.valid())
                throw new IllegalArgumentException("Customer ID or Movie ID Illegal");

            //find out if map entry for user exists , if not assign a new hashmap

            Map<Integer, RatingAndTime> movieIdRatingTimeMap = customerMovieRatingMap.getOrDefault(CUSTOMER_ID, new HashMap<>());

            String rating = rawEntry.get(2);
            String timeStamp = rawEntry.get(3);

            RatingAndTime ratingAndTime = returnRatingAndTime(rating, timeStamp);

            //if it does just enter the movie entry ( override it if the user has already rated this movie)
            movieIdRatingTimeMap.put(MOVIE_ID, ratingAndTime);

            //finally assign the movieIdRatingand Time map to customerID map

            this.customerMovieRatingMap.put(CUSTOMER_ID, movieIdRatingTimeMap);

        }


        return tempCustomerMovieRatingMap;
    }

    private RatingAndTime returnRatingAndTime(String rating, String time) {
        RatingAndTime ratingAndTime = new RatingAndTime(rating, time);

        return ratingAndTime;
    }


}
