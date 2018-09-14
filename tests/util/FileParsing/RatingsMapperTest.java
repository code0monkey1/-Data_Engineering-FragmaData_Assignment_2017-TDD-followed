package util.FileParsing;


import model.RatingAndTime;
import org.junit.Before;
import org.junit.Test;
import util.mapping.RatingsMapper;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RatingsMapperTest {
    private RatingsMapper ratingsMapper;
    private Map<Integer, Map<Integer, RatingAndTime>> customerMoviesRatingAndTimeMap;

    @Before
    public void setUp() throws Exception {
        FileParser fileParser = new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatings.dat", "::");

        this.ratingsMapper = new RatingsMapper(fileParser, 4);
        this.customerMoviesRatingAndTimeMap = ratingsMapper.getCustomerMovieRatingMap();
    }

    @Test
    public void map_has2Entries() {
        assertEquals(2, customerMoviesRatingAndTimeMap.size());
    }


    @Test
    public void map_eachUserGaveAtLeast20MovieRatings() {
        int min = Integer.MAX_VALUE;

        for (Map<Integer, RatingAndTime> movieMap : ratingsMapper.getCustomerMovieRatingMap().values()) {
            min = Math.min(min, movieMap.size());
        }

        assert (min >= 20);

    }

    @Test
    public void map_129MoviesHaveBeenRatedByUserWithId_2() {
        Map<Integer, RatingAndTime> movieMap = ratingsMapper.getCustomerMovieRatingMap().get(2);
        assertEquals(129, movieMap.size());
    }

}