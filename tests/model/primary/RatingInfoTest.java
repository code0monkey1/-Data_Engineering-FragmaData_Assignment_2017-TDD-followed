package model.primary;

import model.primary.customer.CustomerInfo;
import model.primary.customer.AgeRange;
import model.primary.rating.RatingInfo;
import org.junit.Before;
import org.junit.Test;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.RatingsMapper;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RatingInfoTest {
    private FileParser fileParser;
    private RatingInfo ratingInfo;

    @Before
    public void setUp() {
        fileParser = new FileParser("mockRatingsForMoviesNMostViewed.dat", "::");

        RatingsMapper ratingsMapper = new RatingsMapper(fileParser, 4);
        ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
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
//        System.out.println(ratingInfo.getMovieIdViewsCountMap());
        assertEquals(expected, ratingInfo.getMovieIdViewsCountMap());

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

        int resultViews = ratingInfo.getMovieIdViewsCountMap().get(4);
        int resultRatings = ratingInfo.getMovieIdRatingsMap().get(4);
        System.out.println(expectedViews + "::" + resultViews);
        System.out.println(expectedRating + "::" + resultRatings);
        assertEquals(expectedViews, resultViews);
        assertEquals(expectedRating, resultRatings);
    }

    private FileParser returnFileParser() {

        FileParser fileParser = new FileParser("mockRatings.dat", "::");
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

    @Test
    public void userIdRatingMap_isValid() {
        Map<Integer, Long> expectedIdRatings = new HashMap<>();
        List<List<String>> lists = fileParser.getRawList();

        for (List<String> entry : lists) {
            expectedIdRatings.put(Integer.parseInt(entry.get(0)), Long.parseLong(entry.get(2)));
        }

        assertEquals(expectedIdRatings, ratingInfo.getCustomerIdRatingMap());

    }

    @Test
    public void rangeEnumMap_isValid() {

        CustomerInfo customerInfo = new CustomerInfo(new CustomerMapper(new FileParser("users.dat", "::"), 5).getIdCustomerMap());

        Map<Integer, EnumMap<AgeRange, Integer>> movieIDAgeRange = new HashMap<>();

        List<List<String>> entries = this.fileParser.getRawList();

        for (List<String> entry : entries) {

            int userID = Integer.parseInt(entry.get(0));
            int movieID = Integer.parseInt(entry.get(1));

            AgeRange userAgeRange = customerInfo.getAgeRange(userID);

            EnumMap<AgeRange, Integer> ageRangeMap = returnAgeRangeMap(movieIDAgeRange, movieID);

            int presentAgeRangeCount = returnPresentAgeRangeCount(userAgeRange, ageRangeMap);

            ageRangeMap.put(userAgeRange, presentAgeRangeCount + 1);
            movieIDAgeRange.put(movieID, ageRangeMap);

        }
        assertEquals(movieIDAgeRange, ratingInfo.getMovieIdAgeRangeMap(customerInfo));
    }

    private int returnPresentAgeRangeCount(AgeRange userAgeRange, EnumMap<AgeRange, Integer> ageRangeMap) {
        return ageRangeMap.getOrDefault(userAgeRange, 0);
    }

    private EnumMap<AgeRange, Integer> returnAgeRangeMap(Map<Integer, EnumMap<AgeRange, Integer>> movieIDAgeRange, int movieID) {
        return movieIDAgeRange.getOrDefault(movieID, new EnumMap<>(AgeRange.class));
    }

    @Test
    public void customerIdViewersCountMap_isValid() {
        Map<Integer, Integer> customerIdViewerShip = new HashMap<>();

         for(int i=1;i<=10;i++){
             customerIdViewerShip.put(i,1);
         }

         assertEquals(customerIdViewerShip,ratingInfo.getCustomerIdMoviesSeenCountMap());
    }


}