package model.primary.movie;

public class RatingAndTime {

    private int rating;
    private String time;

    public int getRating() {
        return rating;
    }

    public String getTime() {
        return time;
    }

    public RatingAndTime(String rating, String time) {
        this.time = time.trim();
        this.rating = returnRating(rating);
    }


    private int returnRating(String rating) {
        int RATING = Integer.parseInt(rating);

        if (!validRating(RATING)) {
            throw new IllegalArgumentException("Rating invalid");
        }

        return RATING;

    }

    private boolean validRating(int RATING) {
        return RATING >= 1 && RATING <= 5;
    }

    @Override
    public String toString() {
        return "RatingAndTime{" +
                "rating=" + rating +
                ", time='" + time + '\'' +
                '}';
    }
}
