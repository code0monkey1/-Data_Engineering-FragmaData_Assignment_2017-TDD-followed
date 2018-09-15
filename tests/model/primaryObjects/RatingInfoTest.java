package model.primaryObjects;

import org.junit.Test;
import util.FileParsing.FileParser;
import util.mapping.RatingsMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RatingInfoTest {

    @Test
    public void movieViewCountMap_isValid() {

        FileParser fileParser = new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatingsForMoviesNMostViewed.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(fileParser, 4);
        RatingInfo ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());

        Map<Integer, Integer> expected = new HashMap<>();

        expected.put(4, 5);
        expected.put(7, 3);
        expected.put(1, 2);

        System.out.println(expected);
        System.out.println(ratingInfo.getMovieIdViewsMap());
        assertEquals(expected, ratingInfo.getMovieIdViewsMap());

    }

    @Test
    public void movieRatingMap_isValid() {
        Map<Integer, Integer> expectedIdRating = getMovieRatingMapByParsingFile();

        FileParser ratingFileParser = new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatings.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(ratingFileParser, 4);
        RatingInfo ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());

        System.out.println(ratingInfo.getMovieIdRatingsMap());
        System.out.println(expectedIdRating);
        assertEquals(expectedIdRating, ratingInfo.getMovieIdRatingsMap());

    }

    private Map<Integer, Integer> getMovieRatingMapByParsingFile() {
        FileParser ratingFileParser = new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockRatings.dat", "::");
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
}