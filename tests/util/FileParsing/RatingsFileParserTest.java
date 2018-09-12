package util.FileParsing;


import model.RatingAndTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RatingsFileParserTest {
    private RatingsFileParser ratingsFileParser;
    private Map<Integer, Map<Integer, RatingAndTime>> customerMoviesRatingAndTimeMap;

    @Before
    public void setUp() throws Exception {
        this.ratingsFileParser = new RatingsFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatings.dat", "::", 4);
        this.customerMoviesRatingAndTimeMap = ratingsFileParser.getCustomerMovieRatingMap();
    }

    @Test
    public void map_has2Entries() {
        assertEquals(2, customerMoviesRatingAndTimeMap.size());
    }

    @Test
    public void rawList_has182Entries() {
        assertEquals(182, ratingsFileParser.rawEntriesList.size());
    }

    @Test
    public void map_eachUserGaveAtLeast20MovieRatings() {
        int min = Integer.MAX_VALUE;

        for (Map<Integer, RatingAndTime> movieMap : ratingsFileParser.getCustomerMovieRatingMap().values()) {
            min = Math.min(min, movieMap.size());
        }

        assert (min >= 20);

    }

    @Test
    public void map_129MoviesHaveBeenRatedByUserWithId_2() {
        Map<Integer, RatingAndTime> movieMap = ratingsFileParser.getCustomerMovieRatingMap().get(2);
        assertEquals(129, movieMap.size());
    }

}