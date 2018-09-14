package controller;


import model.primaryObjects.CustomerInfo;
import model.primaryObjects.MovieInfo;
import model.primaryObjects.RatingInfo;
import org.junit.Test;
import helperObjects.MovieViewCount;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StatisticsTest {


    @Test
    public void movies_topNMostViewed() {
        //setup
        MovieInfo movieInfo = getMovieInfo();
        CustomerInfo customerInfo = getCustomerInfo();
        RatingInfo ratingInfo = getRatingInfoForQuestion1();


        Statistics statistics = new Statistics(customerInfo,
                movieInfo,
                ratingInfo);

        assertFalse(true);
    }

    private RatingInfo getRatingInfoForQuestion1() {
        FileParser ratingParser = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMoviesNMostViewed.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(ratingParser, 4);


        return new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
    }

    private CustomerInfo getCustomerInfo() {
        FileParser customerParser = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockCustomers.dat", "::");
        CustomerMapper customerMapper = new CustomerMapper(customerParser, 5);
        return new CustomerInfo(customerMapper.getIdCustomerMap());
    }

    private MovieInfo getMovieInfo() {
        FileParser movieParser = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMovies.dat", "::");
        MovieMapper movieMapper = new MovieMapper(movieParser, 3);
        return new MovieInfo(movieMapper.getIdMovieMap());
    }

    private List<MovieViewCount> getExpectedTop2MovieList() {
        List<MovieViewCount> expected = new ArrayList<>();
        expected.add(new MovieViewCount(4, 5));
        expected.add(new MovieViewCount(7, 3));
        return expected;
    }

    private List<MovieViewCount> getExpectedTop4MovieList() {
        List<MovieViewCount> expected = new ArrayList<>();
        expected.add(new MovieViewCount(4, 5));
        expected.add(new MovieViewCount(7, 3));
        expected.add(new MovieViewCount(1, 2));
        return expected;
    }


    private FileParser getFileParser(String fileName, String parseToken) {
        FileParser fileParser = new FileParser(fileName, parseToken);
        return fileParser;
    }

    @Test
    public void topNMovies_countCorrectWhenNisGreaterThanAvailableMovesWatched() {

        CustomerInfo customerInfo = getCustomerInfo();
        RatingInfo ratingInfo = getRatingInfo();
        MovieInfo movieInfo = getMovieInfo();


        Statistics statistics = new Statistics(customerInfo, movieInfo, ratingInfo);


        int expected = 3;
        int count = 0;
        assertEquals(expected, count);

    }

    private RatingInfo getRatingInfo() {
        FileParser ratingParser = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMovies.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(ratingParser, 4);


        return new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
    }


}