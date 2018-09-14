package controller;


import org.junit.Before;
import org.junit.Test;
import tempHelperObjects.MovieViewCount;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    public Statistics statistics;

    @Before
    public void setUp() throws Exception {

        this.statistics = new Statistics(new MovieMapper(new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMovies.dat", "::"), 3),
                new CustomerMapper(new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockCustomers.dat", "::"), 5),
                new RatingsMapper(new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatings.dat", "::"), 4));


    }

    @Test
    public void movies_topNMostViewed() {
        //setup
        FileParser movieMap = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMovies.dat", "::");

        FileParser customergMap = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockCustomers.dat", "::");

        FileParser ratingMap = getFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMoviesNMostViewed.dat", "::");

        this.statistics = new Statistics(new MovieMapper(movieMap, 3),
                new CustomerMapper(customergMap, 5),
                new RatingsMapper(ratingMap, 4));

        List<MovieViewCount> mostViewed2 = statistics.getTopNMostViewedMovies(2);
        List<MovieViewCount> expectedTop2 = getExpectedTop2MovieList();
        assertEquals(expectedTop2, mostViewed2);


        List<MovieViewCount> mostViewed10 = statistics.getTopNMostViewedMovies(10);
        List<MovieViewCount> expectedTop4 = getExpectedTop4MovieList();

        assertEquals(expectedTop4, mostViewed10);
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


}