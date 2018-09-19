package model.helperObjects;

public class CustomerRating implements Comparable<CustomerRating> {


    private double rating;
    private int id;
    private int views;

    public CustomerRating(int id, double rating, int views) {
        this.id = id;
        this.rating = rating;
        this.views = views;
    }

    @Override
    public int compareTo(CustomerRating o) {
        return Double.compare(this.rating, o.rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerRating that = (CustomerRating) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public int getViews() {
        return views;
    }

    @Override
    public String toString() {
        return "CustomerRating{" +
                "rating=" + rating +
                ", id=" + id +
                ", views=" + views +
                '}';
    }
}
