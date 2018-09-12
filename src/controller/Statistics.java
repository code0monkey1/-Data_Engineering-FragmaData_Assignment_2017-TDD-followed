package controller;

import model.Customer;
import model.Movie;
import model.RatingAndTime;
import util.FileParsing.CustomerFileParser;
import util.FileParsing.FileParser;
import util.FileParsing.MovieFileParser;
import util.FileParsing.RatingsFileParser;

import java.util.Map;

public final class Statistics {

    private final Map<Integer, Movie> idMovieMap;


    private final Map<Integer, Customer> idCustomerMap;
    private final Map<Integer, Map<Integer, RatingAndTime>> idCustomerIdMovieRatingAndTimeMap;

    public Statistics(FileParser movieFileParser,
                      FileParser customerFileParser,
                      FileParser ratingFileParser) {

        this.idCustomerMap = ((CustomerFileParser) customerFileParser).getIdCustomerMap();
        this.idMovieMap = ((MovieFileParser) movieFileParser).getIdMovieMap();
        this.idCustomerIdMovieRatingAndTimeMap = ((RatingsFileParser) ratingFileParser).getCustomerMovieRatingMap();
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
