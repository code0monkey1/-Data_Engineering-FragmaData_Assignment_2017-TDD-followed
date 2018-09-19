package controller;


import model.helperObjects.CustomerRating;
import model.helperObjects.MovieRating;
import model.helperObjects.MovieView;
import model.primary.customer.CustomerInfo;
import model.primary.movie.MovieInfo;
import model.primary.rating.RatingInfo;
import org.junit.Before;
import org.junit.Test;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StatisticsTest {
    private Statistics statistics;

    @Before
    public void setUp() {
        MovieInfo movieInfo = getMovieInfo();
        CustomerInfo customerInfo = getCustomerInfo();
        RatingInfo ratingInfo = getRatingInfoForQuestion1();


        this.statistics = new Statistics(customerInfo,
                movieInfo,
                ratingInfo);
    }

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
        FileParser ratingParser = getFileParser("mockRatingsForMoviesNMostViewed.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(ratingParser, 4);


        return new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
    }

    private CustomerInfo getCustomerInfo() {
        FileParser customerParser = getFileParser("mockCustomers.dat", "::");
        CustomerMapper customerMapper = new CustomerMapper(customerParser, 5);
        return new CustomerInfo(customerMapper.getIdCustomerMap());
    }

    private MovieInfo getMovieInfo() {
        FileParser movieParser = getFileParser("mockMovies.dat", "::");
        MovieMapper movieMapper = new MovieMapper(movieParser, 3);
        return new MovieInfo(movieMapper.getIdMovieMap());
    }

//    private List<MovieView> getExpectedTop2MovieList() {
//        List<MovieView> expected = new ArrayList<>();
//        expected.add(new MovieView(4, 5));
//        expected.add(new MovieView(7, 3));
//        return expected;
//    }
//
//    private List<MovieView> getExpectedTop4MovieList() {
//        List<MovieView> expected = new ArrayList<>();
//        expected.add(new MovieView(4, 5));
//        expected.add(new MovieView(7, 3));
//        expected.add(new MovieView(1, 2));
//        return expected;
//    }


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

//    private RatingInfo getRatingInfo() {
//        FileParser ratingParser = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMovies.dat", "::");
//        RatingsMapper ratingsMapper = new RatingsMapper(ratingParser, 4);
//        RatingInfo ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
//        return ratingInfo;
//    }

    @Test
    public void ratingList_top3RatedMoviesWithMin2Views() {
        List<MovieRating> expected = new ArrayList<>();
        expected.add(new MovieRating(7, 13.0 / 3, 3));
        expected.add(new MovieRating(1, 8.0 / 2, 2));
        expected.add(new MovieRating(4, 20.0 / 5, 5));


        assertEquals(expected, statistics.getTopRatedMovies(3, 2));
    }

    @Test
    public void topRatedViewerShipCount_top3MostViewedWithMin2Views() {
        Map<Integer, HashMap> expected = new HashMap<>();

        HashMap<Integer, Integer> firstInternalMap = new HashMap<>();
        firstInternalMap.put(0, 0);
        firstInternalMap.put(1, 2);
        firstInternalMap.put(2, 0);
        expected.put(7, firstInternalMap);

        HashMap<Integer, Integer> secondInternalMap = new HashMap<>();
        secondInternalMap.put(0, 1);
        secondInternalMap.put(1, 0);
        secondInternalMap.put(2, 1);
        expected.put(1, secondInternalMap);

        HashMap<Integer, Integer> thirdInternalMap = new HashMap<>();
        thirdInternalMap.put(0, 0);
        thirdInternalMap.put(1, 2);
        thirdInternalMap.put(2, 2);
        expected.put(4, thirdInternalMap);

        Set<Integer> keys = expected.keySet();
        assertThat(expected, hasKey(1));

        assertThat(expected, is(statistics.getMovieViewershipAgeRangeCount(3, 2)));

//        assertEquals(expected, statistics.getMovieViewershipAgeRangeCount(3, 2));
    }

    @Test
    public void top10critics_GivingMinimumRatingAndHavingMinimum1View() {
        List<CustomerRating> customers = new ArrayList<>();
        customers.add(new CustomerRating(2, 3.0, 1));
        customers.add(new CustomerRating(3, 3.0, 1));
        customers.add(new CustomerRating(6, 3.0, 1));
        customers.add(new CustomerRating(4, 4.0, 1));
        customers.add(new CustomerRating(9, 4.0, 1));
        customers.add(new CustomerRating(10, 4.0, 1));
        customers.add(new CustomerRating(7, 5.0, 1));
        customers.add(new CustomerRating(8, 5.0, 1));
        customers.add(new CustomerRating(1, 5.0, 1));
        customers.add(new CustomerRating(5, 5.0, 1));

        assertEquals(customers, statistics.getCustomersWithRatingSatisfyingMinViewCondition(10, 1));


    }


}