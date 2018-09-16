package controller;


import model.helperObjects.MovieView;
import model.primary.customer.CustomerInfo;
import model.primary.movie.MovieInfo;
import model.primary.rating.RatingInfo;
import org.junit.Test;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

        List<MovieView> expected = new ArrayList<>();
        expected.add(new MovieView(4, 5));
        expected.add(new MovieView(7, 3));

        assertEquals(expected, statistics.getTopViewedMovies(2));
    }

    private RatingInfo getRatingInfoForQuestion1() {
        FileParser ratingParser = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatingsForMoviesNMostViewed.dat", "::");
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

    private List<MovieView> getExpectedTop2MovieList() {
        List<MovieView> expected = new ArrayList<>();
        expected.add(new MovieView(4, 5));
        expected.add(new MovieView(7, 3));
        return expected;
    }

    private List<MovieView> getExpectedTop4MovieList() {
        List<MovieView> expected = new ArrayList<>();
        expected.add(new MovieView(4, 5));
        expected.add(new MovieView(7, 3));
        expected.add(new MovieView(1, 2));
        return expected;
    }


    private FileParser getFileParser(String fileName, String parseToken) {
        FileParser fileParser = new FileParser(fileName, parseToken);
        return fileParser;
    }

    @Test
    public void mostViewedMovies_whenCountExceedsLimit() {

        CustomerInfo customerInfo = getCustomerInfo();
        RatingInfo ratingInfo = getRatingInfoForQuestion1();
        MovieInfo movieInfo = getMovieInfo();


        Statistics statistics = new Statistics(customerInfo, movieInfo, ratingInfo);
        List<MovieView> expected = new ArrayList<>();
        expected.add(new MovieView(4, 5));
        expected.add(new MovieView(7, 3));
        expected.add(new MovieView(1, 2));

        assertEquals(expected, statistics.getTopViewedMovies(10));

    }

    private RatingInfo getRatingInfo() {
        FileParser ratingParser = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMovies.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(ratingParser, 4);
        RatingInfo ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
        return ratingInfo;
    }


}