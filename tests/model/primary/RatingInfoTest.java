package model.primary;

import model.primary.rating.RatingInfo;
import org.junit.Before;
import org.junit.Test;
import util.FileParsing.FileParser;
import util.mapping.RatingsMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RatingInfoTest {
    private FileParser fileParser;

    @Before
    public void setUp() {
        fileParser = new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatingsForMoviesNMostViewed.dat", "::");
    }

    @Test
    public void movieViewCountMap_isValid() {


        RatingsMapper ratingsMapper = new RatingsMapper(this.fileParser, 4);
        RatingInfo ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());

        Map<Integer, Integer> expected = new HashMap<>();

        expected.put(4, 5);
        expected.put(7, 3);
        expected.put(1, 2);

//        System.out.println(expected);
//        System.out.println(ratingInfo.getMovieIdViewsMap());
        assertEquals(expected, ratingInfo.getMovieIdViewsMap());

    }

    @Test
    public void movieRatingMap_isValid() {
        Map<Integer, Integer> expectedIdRating = returnMovieRatingMapByParsingFile();


        RatingsMapper ratingsMapper = new RatingsMapper(this.fileParser, 4);
        RatingInfo ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());

//        System.out.println(ratingInfo.getMovieIdRatingsMap());
//        System.out.println(expectedIdRating);
        assertEquals(expectedIdRating, ratingInfo.getMovieIdRatingsMap());

    }

    private Map<Integer, Integer> returnMovieRatingMapByParsingFile() {
        FileParser ratingFileParser = this.fileParser;
        List<List<String>> lists = ratingFileParser.getRawList();
        Map<Integer, Integer> movieIdRating = new HashMap<>();

        for (List<String> list : lists) {
            int id = Integer.parseInt(list.get(1));
            int rating = Integer.parseInt(list.get(2));

            int previousRating = movieIdRating.getOrDefault(id, 0);
            movieIdRating.put(id, previousRating + rating);
        }
        return movieIdRating;
    }

    @Test
    public void movieViewCountLesserThanMovieRatingCount() {
        RatingsMapper ratingsMapper = new RatingsMapper(this.fileParser, 4);
        RatingInfo ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());

        int expectedViews = returnMovieViewMapByParsingFile().get(4);
        int expectedRating = returnMovieRatingMapByParsingFile().get(4);

        int resultViews = ratingInfo.getMovieIdViewsMap().get(4);
        int resultRatings = ratingInfo.getMovieIdRatingsMap().get(4);
        System.out.println(expectedViews+"::"+resultViews);
        System.out.println(expectedRating+"::"+resultRatings);
        assertEquals(expectedViews, resultViews);
        assertEquals(expectedRating, resultRatings);
    }

    private FileParser returnFileParser() {

        FileParser fileParser = new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatings.dat", "::");
        assertNotNull(fileParser);
        return fileParser;
    }

    private Map<Integer, Integer> returnMovieViewMapByParsingFile() {

        List<List<String>> entryLists = fileParser.getRawList();
        Map<Integer, Integer> movieIdViews = new HashMap<>();

        for (List<String> entry : entryLists) {
            if (entry.size() < 4) continue;
            int movieId = Integer.parseInt(entry.get(1));
            int count = movieIdViews.getOrDefault(movieId, 0);
            movieIdViews.put(movieId, count + 1);
        }
        assertNotNull(movieIdViews);
        return movieIdViews;
    }


}