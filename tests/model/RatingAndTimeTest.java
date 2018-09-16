package model;


import model.primary.movie.RatingAndTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingAndTimeTest {

    @Test
    public void rating_isCorrect() {
        RatingAndTime ratingAndTime = new RatingAndTime("2", "34343");
        assertEquals(2, ratingAndTime.getRating());

        ratingAndTime = new RatingAndTime("5", "343423");
        assertEquals(5, ratingAndTime.getRating());
    }

    @Test
    public void time_isCorrect() {
        RatingAndTime ratingAndTime = new RatingAndTime("3", "343423");
        assertEquals("343423", ratingAndTime.getTime());

    }

    @Test(expected = IllegalArgumentException.class)
    public void rating_isIncorrect() {
        RatingAndTime ratingAndTime = new RatingAndTime("6", "343423");
    }

}